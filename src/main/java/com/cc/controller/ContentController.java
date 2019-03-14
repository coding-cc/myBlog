package com.cc.controller;

import com.cc.model.ResultVO;
import com.cc.model.entity.Comments;
import com.cc.model.entity.Contents;
import com.cc.service.CommentsService;
import com.cc.service.ContentService;
import com.cc.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author : cc
 * @date : 2018-09-18  16:00
 */
@Controller
@Slf4j
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping(value = "article/{cid}")
    @Transactional
    public String findOne(@PathVariable(value = "cid") Integer cid,
                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "6") Integer size,
                          HttpServletRequest request, ModelMap modelMap) {
        String ip = request.getRemoteAddr();
        Contents c = contentService.findById(cid);
        Contents dto = new Contents();
        BeanUtils.copyProperties(c, dto);
        modelMap.addAttribute("article", dto);
        mdToHTML(dto);
        modelMap.addAttribute("articles", contentService.findAllByAuthorIdAndType(1, "post"));
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("comments", commentsService.findAllByArticleId(cid, PageRequest.of(page - 1, size)));
        String token;
        try {
            token = redisTemplate.opsForValue().get("HIT_" + cid + ":" + ip);
        } catch (Exception e){
            log.error("[redis连接异常], {}", e.getMessage());
            return "themes/default/post.html";
        }
        //获取今天还剩多少秒
        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
        if (StringUtils.isEmpty(token)) {
            Integer hits = c.getHits();
            c.setHits(hits + 1);
            redisTemplate.opsForValue().set("HIT_"+ cid + ":" + ip, "exist", seconds, TimeUnit.SECONDS);
            contentService.save(c);
        }
        return "themes/default/post.html";
    }

    private void mdToHTML(Contents c) {

        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser     = Parser.builder().extensions(extensions).build();
        Node document   = parser.parse(c.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new LinkAttributeProvider())
                .extensions(extensions).build();

        String content = renderer.render(document);
        c.setContent(content);
    }

    class LinkAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }


    @PostMapping(value = "/comment")
    @ResponseBody
    public ResultVO doComment(Comments comments, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        String token;
        try {
            token = redisTemplate.opsForValue().get("COMMENT_" + ip);
        } catch (Exception e){
            return ResultUtils.error(500, "系统繁忙, 请稍后重试");
        }
        if (StringUtils.isEmpty(token)) {
            comments.setIp(ip);
            comments.setCreated(new Date());
            comments.setOwnerId(11);
            comments.setAuthor("youke");
            comments.setAuthorId(1);
            comments.setStatus("no_audit");
            commentsService.save(comments);
            redisTemplate.opsForValue().set("COMMENT_" + ip, "exist", 60, TimeUnit.SECONDS);
            return ResultUtils.success();
        }
        return ResultUtils.error(500, "评论过于频繁, 请稍后重试");
    }

    @GetMapping(value = "/tag/{name}")
    public String tag(@PathVariable(value = "name") String name,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        ModelMap modelMap){
        Page<Contents> contentsPage = contentService.pageByTag(name, PageRequest.of(page - 1, 6));
        modelMap.addAttribute("articles", contentsPage);
        modelMap.addAttribute("type", "标签");
        modelMap.addAttribute("keyword", name);

        return "themes/default/page-category.html";
    }

    @GetMapping(value = "/category/{name}")
    public String category(@PathVariable(value = "name") String name,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 ModelMap modelMap){
        Page<Contents> contentsPage = contentService.pageByCategory(name, PageRequest.of(page - 1, 6));
        modelMap.addAttribute("articles", contentsPage);
        modelMap.addAttribute("type", "类别");
        modelMap.addAttribute("keyword", name);
        return "themes/default/page-category.html";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "themes/default/about.html";
    }

}
