package com.macro.mall.tiny.handler;

import com.macro.mall.tiny.common.api.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author shixingjie
 * @Date 2021/5/11 17:15
 */

@ControllerAdvice
public class GlobalExceptionHandler2 {

//    @ResponseBody
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public R handleValidException(MethodArgumentNotValidException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        String message = null;
//        if (bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if (fieldError != null) {
//                message = fieldError.getField()+fieldError.getDefaultMessage();
//            }
//        }
//        return CommonResult.failed(message);
//    }
//
//    @ResponseBody
//    @ExceptionHandler(value = BindException.class)
//    public R handleValidException(BindException e) {
//        BindingResult bindingResult = e.getBindingResult();
//        String message = null;
//        if (bindingResult.hasErrors()) {
//            FieldError fieldError = bindingResult.getFieldError();
//            if (fieldError != null) {
//                message = fieldError.getField()+fieldError.getDefaultMessage();
//            }
//        }
//        return Response.failed(message);
//    }


}
