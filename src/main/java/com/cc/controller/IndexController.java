package com.cc.controller;

import com.cc.model.entity.Contents;
import com.cc.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : cc
 * @date : 2018-09-17  15:52
 */
@Controller
public class IndexController {

    @PostMapping(value = "github-webhook")
    @ResponseBody
    public  String  webHook(){
        return "success";
    }

    @Autowired
    private ContentService contentService;

    @GetMapping(value = "/")
    public String index(HttpServletRequest request,
                        @RequestParam(defaultValue = "1") int page,
                        ModelMap modelMap) {

        modelMap.addAttribute("articles", contentService.findAllByAuthorIdAndType(1, "post", PageRequest.of(page-1, 9)));
        return "themes/default/index.html";
    }

    @GetMapping(value = "archives")
    public String archives(ModelMap modelMap){
        modelMap.addAttribute("articles", contentService.findAllByAuthorId(1, PageRequest.of(0, 30)));
        return "themes/default/archives.html";
    }

    @GetMapping(value = "search/{keyword}")
    public String search(@PathVariable String keyword,
                         @RequestParam(defaultValue = "1") int page,
                         ModelMap modelMap){
        Page<Contents> result = contentService.findByKeyword("%" + keyword + "%", PageRequest.of(page-1, 9));
        modelMap.addAttribute("articles", result);
        return "themes/default/archives.html";
    }

}
