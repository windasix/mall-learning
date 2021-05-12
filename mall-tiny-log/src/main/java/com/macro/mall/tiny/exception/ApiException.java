package com.macro.mall.tiny.exception;

import com.macro.mall.tiny.common.api.IErrorCode;

/**
 * @Author shixingjie
 * @Date 2021/5/12 9:32
 */
public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super((errorCode.getMessage()));
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

}
