package com.jack90john.ribbon_server.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Designer: jack
 * Date: 2018/11/5
 * Version: 1.0.0
 */

@Data
public class ApiResult<T> implements Serializable {
    /**
     * 错误码，对应{@link ErrorCode}，表示一种错误类型
     * 如果是成功，则code为200
     */
    private int code;
    /**
     * 对错误的具体解释
     */
    private String message;
    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private T value;

    public ApiResult() {
    }

    public ApiResult(ErrorCode errorCode, T value) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
        this.value = value;
    }

}
