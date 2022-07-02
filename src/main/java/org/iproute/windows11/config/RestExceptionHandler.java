package org.iproute.windows11.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RestExceptionHandler
 *
 * @author zhuzhenjie
 * @since 2022/7/1
 */
@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        log.error("Exception", e);
        return "404";
    }
}
