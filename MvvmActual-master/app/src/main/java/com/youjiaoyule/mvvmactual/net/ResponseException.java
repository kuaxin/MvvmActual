package com.youjiaoyule.mvvmactual.net;

/**
 * 描述：自定义API请求异常
 * Created by fzJiang on 2019-04-23
 */
public class ResponseException extends RuntimeException {

    private static final long serialVersionUID = 5990046398217380606L;
    private String msg;// 请求描述

    public ResponseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
