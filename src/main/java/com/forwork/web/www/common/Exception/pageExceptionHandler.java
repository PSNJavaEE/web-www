package com.forwork.web.www.common.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class pageExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /*@ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public R noHandlerFoundException(org.springframework.web.servlet.NoHandlerFoundException e) {
        logger.error(e.getMessage(), e);
        return R.error(404, "没找找到页面");
    }*/

    @ResponseBody
    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public String handlerException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notExist");
        map.put("message",e.getMessage());
        return "redirect:/error";
    }
}
