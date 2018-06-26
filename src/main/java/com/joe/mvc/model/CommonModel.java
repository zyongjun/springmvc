package com.joe.mvc.model;

public class CommonModel {
    private static final int CODE_SUCCESS = 200;
    private static final int CODE_FAIL = 500;
    private static final String MSG_FAIL = "服务器出错了";
    private static final String MSG_SUCCESS = "请求成功";
    private int code;
    private Object data;
    private String msg;

    public void setSuccess() {
        setCode(CODE_SUCCESS);
        setMsg(MSG_SUCCESS);
    }

    public void setFail() {
        setCode(CODE_FAIL);
        setMsg(MSG_FAIL);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
