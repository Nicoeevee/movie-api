package com.mycompany.movieapi.interceptor;

import com.mycompany.movieapi.rest.dto.MessageResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || "".equals(msg)) {
            msg = "服务器出错";
        }
        return new MessageResponse(msg);
    }
}
