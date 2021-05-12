package com.macro.mall.tiny.exception;

import com.macro.mall.tiny.common.api.IErrorCode;

/**
 * @Author shixingjie
 * @Date 2021/5/12 9:38
 */
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }

}
