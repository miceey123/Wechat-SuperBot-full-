package com.cn.supers.exception;


import com.cn.supers.msg.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * The type Global interceptor.
 *
 * @author bdth
 * @email 2074055628 @qq.om
 */
@RestControllerAdvice
@SuppressWarnings("all")
@Slf4j
public class GlobalInterceptor {


    /**
     * Exception handler result.
     *
     * @param e the e
     * @return the result
     * @author bdth
     * @email 2074055628 @qq.om
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            final String message = e.getMessage();
            final List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            return Result.error(allErrors.get(0).getDefaultMessage());
        }
        log.error("system has a fatal error!!!!");
        return Result.error(e.getMessage());
    }


}
