package com.cc.aspect;

import com.cc.annotation.SysLog;
import com.cc.model.ResultVO;
import com.cc.model.entity.Logs;
import com.cc.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author : cc
 * @date : 2018-12-06  14:28
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("execution(public * com.cc.controller.admin.AdminController.*(..))")
    public void expression(){}

    @Before("expression()")
    public void doBefore(){

    }

    @After("expression()")
    public void doAfter(){
        return;
    }

    @AfterReturning(value = "expression()", returning = "rvt")
    public void afterReturning(JoinPoint joinPoint, Object rvt){
        if (rvt instanceof ResultVO){
            ResultVO resultVO = (ResultVO)rvt;
            if (200 == resultVO.getCode()){
                ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                String ip = request.getRemoteAddr();
                MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
                Method method = methodSignature.getMethod();
                SysLog annotation = method.getAnnotation(SysLog.class);
                if(null == annotation){
                    return;
                } else {
                    String value = annotation.value();
                    Logs logs = new Logs(value, "", ip, 1);
                    logService.save(logs);
                }
            }
        }

    }

}
