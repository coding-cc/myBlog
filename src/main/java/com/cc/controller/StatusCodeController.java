package com.cc.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : cc
 * @date : 2018-11-27  15:02
 */
@Controller
public class StatusCodeController implements ErrorController {

    @RequestMapping(value = "/error")
    public String error(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (404 == code){
            return "comm/error_404.html";
        }
        return "comm/error_500.html";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
