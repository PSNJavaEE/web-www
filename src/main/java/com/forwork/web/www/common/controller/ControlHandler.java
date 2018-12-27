package com.forwork.web.www.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControlHandler {
    void execute(HttpServletRequest var1, HttpServletResponse var2) throws Exception;
}
