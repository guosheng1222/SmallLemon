package com.example.bean;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/1.
 */

public class RegisterMessage {

    /**
     * status : ok
     * data : {"userId":"10673","name":"godboy","phone":"18500704988","image_url":"http://114.112.104.151:8203/pictures/headimg/userhead/head_w.png"}
     */

    private String status;
    private DataBean data;

    @Override
    public String toString() {
        return "RegisterMessage{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

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
        @Override
        public String toString() {
            return "DataBean{" +
                    "userId='" + userId + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", image_url='" + image_url + '\'' +
                    ", code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }

        /**
         * userId : 10673
         * name : godboy
         * phone : 18500704988
         * image_url : http://114.112.104.151:8203/pictures/headimg/userhead/head_w.png
         */

        private String userId;
        private String name;
        private String phone;
        private String image_url;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
