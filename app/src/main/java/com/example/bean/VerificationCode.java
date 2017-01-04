package com.example.bean;

/**
 * @author : 张鸿鹏
 * @date : 2016/12/30.
 */

public class VerificationCode {


    /**
     * status : error
     * data : {"code":"603","message":"无效验证码请重新发送"}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 603
         * message : 无效验证码请重新发送
        */

        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
