package com.jack90john.ribbon_server.result;

/**
 * Description:
 * Designer: jack
 * Date: 2018/11/5
 * Version: 1.0.0
 */
public enum  ErrorCode {
    SUCCESS(200,"成功"),
    NO_PERMISSION(211,"权限不足"),
    SERVER_ERROR(10000,"服务器异常"),
    AUTH_ERROR(10001,"认证失败"),
    PARAMS_ERROR(10002,"参数错误"),
    JSON_PARSE_ERROR(10003,"Json解析错误"),
    TIME_OUT(10004,"服务器返回超时"),
    ILLEAGAL_STRING(15001,"非法字符串"),
    UNKNOW_ERROR(16000,"未知错误");


    private int code;
    private String msg;

    ErrorCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
