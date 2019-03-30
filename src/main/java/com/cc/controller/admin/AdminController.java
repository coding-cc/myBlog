package com.cc.controller.admin;

import com.cc.annotation.SysLog;
import com.cc.exception.BlogException;
import com.cc.model.ResultVO;
import com.cc.model.entity.*;
import com.cc.model.form.UserForm;
import com.cc.service.*;
import com.cc.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : cc
 * @date : 2018-09-19  21:50
 */
@Controller
@RequestMapping("cc")
public class AdminController {

    private final LogService logService;
    private final ContentService contentService;
    private final CommentsService commentsService;
    private final AttachService attachService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final MetaService metaService;
    private final RelationshipsService relationshipsService;


    public AdminController(LogService logService,
                           ContentService contentService,
                           CommentsService commentsService,
                           AttachService attachService,
                           UserService userService,
                           CategoryService categoryService,
                           MetaService metaService, RelationshipsService relationshipsService) {
        this.logService = logService;
        this.contentService = contentService;
        this.commentsService = commentsService;
        this.attachService = attachService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.metaService = metaService;
        this.relationshipsService = relationshipsService;
    }

    @GetMapping(value = "logs")
    @ResponseBody
    public ResultVO log() {
        Page<Logs> eightLogs = logService.findPreFiveByUid(1, PageRequest.of(0, 8, new Sort(Sort.Direction.DESC, "created")));
        return ResultUtils.success(eightLogs);
    }

    @GetMapping(value = "articles")
    public String articles() {
        return "admin/articles.html";
    }

    @GetMapping(value = "pages")
    public String pages() {
        return "admin/pages.html";
    }

//    @GetMapping(value = "articles/{authId}")
//    @ResponseBody
//    public String articles(@PathVariable(value = "authId") Integer id){
//        List<Contents> allContents = contentService.findAllByAuthorId(id);
//        return new Gson().toJson(allContents);
//    }


    @GetMapping(value = "articles/{authId}")
    @ResponseBody
    public ResultVO articles(@PathVariable(value = "authId") Integer id,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size) {
        Page<Contents> allContents = contentService.findAllByAuthorIdAndType(id, "post", PageRequest.of(page - 1, size));
        return ResultUtils.success(allContents);
    }

    @GetMapping(value = "page/{authId}")
    @ResponseBody
    public ResultVO link(@PathVariable(value = "authId") Integer id,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer size) {
        Page<Contents> allLinks = contentService.findAllByAuthorIdAndType(id, "page", PageRequest.of(page - 1, size));
        return ResultUtils.success(allLinks);
    }


    @GetMapping(value = "admin")
    public String index(ModelMap modelMap) {
        //TODO
        //最近5篇文章
        Page<Contents> contentsPage = contentService.findAllByAuthorIdAndType(1, "post", PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "created")));
        List<Attach> attachList = attachService.findAllByAuthid(1);
        modelMap.addAttribute("articleList", contentsPage.getContent());
        modelMap.addAttribute("count", contentService.findAllByAuthorIdAndType(1, "post").size()
        );
        modelMap.addAttribute("attachList", attachList);
        return "admin/index.html";
    }

    @GetMapping(value = "comments/{authId}")
    @ResponseBody
    public ResultVO comments(@PathVariable(value = "authId") Integer id,
                             @RequestParam(defaultValue = "1") Integer page) {
        //TODO 评论数大于 size Bug 此处id可从session中取 也可前台传
        Page<Comments> commentsPage = commentsService.findAllByAuId(1, PageRequest.of(page - 1, 999));
        return ResultUtils.success(commentsPage);
    }


    @GetMapping(value = "article/edit/{cid}")
    public String editArticle(@PathVariable(value = "cid") Integer cid) {
        return "admin/article/edit.html";
    }

    @GetMapping(value = "article/get/{cid}")
    @ResponseBody
    public ResultVO getArticle(@PathVariable(value = "cid") Integer cid) {
        Contents contents = contentService.findById(cid);
        return ResultUtils.success(contents);
    }

    @GetMapping(value = "page/edit/{cid}")
    public String editPage(@PathVariable(value = "cid") Integer cid) {
        return "admin/page/edit.html";
    }

    @GetMapping(value = "page/get/{cid}")
    @ResponseBody
    public ResultVO getPage(@PathVariable(value = "cid") Integer cid) {
        Contents contents = contentService.findById(cid);
        return ResultUtils.success(contents);
    }


//    @PostMapping(value = "article/edit/{cid}")
//    public ResultVO editArticle(@PathVariable(value = "cid") Integer cid){
//        contentService.
//        return ResultUtils.success();
//    }

    @PostMapping(value = "page/delete")
    @ResponseBody
    @SysLog(value = "删除页面")
    public ResultVO deletePage(@RequestParam(value = "cid") Integer cid) {
        contentService.deleteOne(cid);
        return ResultUtils.success();
    }

    @PostMapping(value = "comment/delete")
    @ResponseBody
    @SysLog(value = "删除评论")
    public ResultVO deleteComment(@RequestParam(value = "coid") Integer coid) {
        commentsService.deleteOne(coid);
        return ResultUtils.success();
    }

    @PostMapping(value = "article/publish")
    @ResponseBody
    @SysLog(value = "发布文章")
    @Transactional
    public ResultVO add(@RequestBody Contents content) {
        String tag = content.getTags();

        //初始化数据
        content.setHits(0);
        content.setAuthorId(1);
        content.setCommentsNum(0);
        content.setModified(content.getCreated());
        Contents result = contentService.save(content);
        if(tag.contains(",")){
            String[] tags = tag.split(",");
            for (String t:tags
                 ) {
                saveRelationships(t, result.getCid());
            }
        } else {
            saveRelationships(tag, result.getCid());
        }

        return ResultUtils.success(result.getCid());
    }
    private void saveRelationships(String tag, Integer cid){
        Metas meta = metaService.findByName(tag);
        if(null==meta){
            Metas metas = new Metas();
            metas.setName(tag);
            metas.setType("tag");
            Relationships relationships = new Relationships();
            relationships.setMid(metaService.save(metas).getMid());
            relationships.setCid(cid);
            relationshipsService.save(relationships);
        } else {
            Relationships relationships = new Relationships();
            relationships.setMid(meta.getMid());
            relationships.setCid(cid);
            relationshipsService.save(relationships);
        }
    }

    @PostMapping(value = "content/update")
    @ResponseBody
    @SysLog(value = "修改文章")
    public ResultVO updateContent(@RequestBody Contents content) {
        content.setModified(new Date());
        Contents result = contentService.save(content);
        return ResultUtils.success(result.getCid());
    }

    @GetMapping(value = "article/new")
    public String articleNew() {
        return "admin/article/new.html";
    }

    @GetMapping(value = "page/new")
    public String pageNew() {
        return "admin/page/new.html";
    }


    @RequestMapping(value = "loginUI")
    public String logUI() {
        return "admin/login.html";
    }

    @PostMapping(value = "login")
    @SysLog(value = "管理员登录")
    @ResponseBody
    public ResultVO login(Users users) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(), users.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return ResultUtils.error(500, "用户名或密码错误");
        }
        return ResultUtils.success();
    }

    @GetMapping(value = "comment/status")
    @ResponseBody
    public String commentsStatus() {
        return null;
    }

    @GetMapping(value = "attaches/{authId}")
    @ResponseBody
    public ResultVO attaches(@PathVariable(value = "authId") Integer id, @RequestParam(defaultValue = "1") Integer page) {
        Page<Attach> allContents = attachService.findAllByAuthid(id, PageRequest.of(page-1, 6));
        return ResultUtils.success(allContents);
    }

    @GetMapping(value = "attaches")
    public String attaches() {
        return "admin/attaches.html";
    }

    @GetMapping(value = "comments")
    public String comments() {
        return "admin/comments.html";
    }

    @GetMapping(value = "modify")
    public String modifyPwd(ModelMap modelMap) {
        modelMap.addAttribute("loginUser", SecurityUtils.getSubject().getPrincipal());
        return "admin/profile.html";
    }

    @PostMapping(value = "/update/password")
    @ResponseBody
    @SysLog(value = "修改密码")
    public ResultVO changePwd(UserForm userForm) {
        Users u = (Users) SecurityUtils.getSubject().getPrincipal();
        if (!DigestUtils.md5DigestAsHex(userForm.getOldPassword().getBytes()).equals(u.getPassword())) {
            return ResultUtils.error(500, "密码不正确");
        }
        u.setPassword(DigestUtils.md5DigestAsHex(userForm.getPassword().getBytes()));
        userService.save(u);
        return ResultUtils.success();
    }

    @PostMapping(value = "/update/user")
    @ResponseBody
    @SysLog(value = "修改个人信息")
    public ResultVO changeUser(Users users) {
        userService.save(users);
        return ResultUtils.success();
    }

    @GetMapping(value = "/logout")
    @ResponseBody
    public ResultVO changeUser() {
        SecurityUtils.getSubject().logout();
        return ResultUtils.success();
    }

    @PostMapping(value = "comment/status")
    @ResponseBody
    @SysLog(value = "评论审核通过")
    public ResultVO approve(Integer coid) {
        Optional optional = commentsService.findById(coid);
        if (optional.isPresent()) {
            Comments comments = (Comments) optional.get();
            comments.setStatus("approved");
            Contents contents = contentService.findById(comments.getCid());
            contents.setCommentsNum(contents.getCommentsNum() + 1);
            contentService.save(contents);
            commentsService.save(comments);
            return ResultUtils.success();
        } else {
            throw new BlogException("审核通过失败");
        }
    }

    @GetMapping(value = "/category")
    public String category() {
        return "admin/categories.html";
    }

    @GetMapping(value = "/categories")
    @ResponseBody
    public String categories() {
        return null;
    }

    @GetMapping(value = "metas")
    @ResponseBody
    public ResultVO tags(String type) {
        //TODO java8 实现
        List<Metas> metas = categoryService.findAllByType(type);
        if ("category".equals(type)) {
            for (Metas temp : metas) {
                int i = contentService.countByCategory(temp.getName());
                temp.setCount(i);
            }
        }
        if ("tag".equals(type)) {
            for (Metas temp : metas) {
                int i = contentService.countByTag(temp.getName());
                temp.setCount(i);
            }
        }
//        metas.stream().

        return ResultUtils.success(metas);
    }

    @PostMapping(value = "category/save")
    @ResponseBody
    public ResultVO saveMeta(Metas metas){
        String name = metas.getName();
        boolean exist = metaService.exist(name);
        if (!exist){
            metas.setType("category");
            metaService.save(metas);
            return ResultUtils.success();
        }
        return ResultUtils.error(500, "该标签已存在");
    }

    @PostMapping(value = "attach/delete/{id}")
    @ResponseBody
    public ResultVO delAttach(@PathVariable(value = "id") Integer id) {
        attachService.deleteOne(id);
        return ResultUtils.success();
    }
    @PostMapping(value = "/uploadShapeIcon")
    @ResponseBody
    public String uploadShapeIcon(MultipartFile file) throws Exception {

        if (file == null) {
            return "failed";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString().replaceAll("-","") + suffixName;
        String separator = File.separator;
        File path = new File(ResourceUtils.getURL("").getPath());
        File upDir = new File(path, "static" + separator + "upload");

        if (!upDir.exists()) {
            upDir.mkdirs();
        }

        File tar = new File(upDir + separator + fileName);
        file.transferTo(tar);

        Attach attach = new Attach();
        attach.setFname(fileName);
        attach.setAuthorId(1);
        attach.setFtype("image");
        attach.setUrl("/static/upload/"+fileName);
        attach.setCreated(new Date());
        attachService.save(attach);
        return "succeed";
    }
}
