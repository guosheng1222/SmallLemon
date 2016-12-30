package com.example.bean;

/**
 * @author : 张鸿鹏
 * @date : 2016/12/30.
 */

public class LoginMessage {

    /**
     * code : -1
     * height : 0
     * success : false
     * width : 0
     * data : null
     * message : 帐号或者密码有误，请重新登录!
     */

    private int code;
    private int height;
    private boolean success;
    private int width;
    private Object data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
