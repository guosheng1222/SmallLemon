package com.example.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * @author :   郗琛
 * @date :   2017/1/4
 */
public class LoginBean {
    /**
     * code : 1
     * height : 0
     * success : true
     * width : 0
     * data : {"gender":1,"fansCount":0,"attentionCount":0,"tel":null,"occupation":"","workDescript":"","birthday":"2016.12.30","age":0,"constellation":"摩羯座","img":"http://yulin-img.oss-cn-shenzhen.aliyuncs.com/head%2Fhead-bear-.png","yulin":"97480","emotionStage":0,"is":"","labelsList":[],"userName":"97480"}
     * message : ZW1JYXHK2MC0O6SAT33R
     */

    private int code;
    private int height;
    private boolean success;
    private int width;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Table(name = "loginBean")      //为表创建NAME
    public static class DataBean {
        /**
         * gender : 1
         * fansCount : 0
         * attentionCount : 0
         * tel : null
         * occupation :
         * workDescript :
         * birthday : 2016.12.30
         * age : 0
         * constellation : 摩羯座
         * img : http://yulin-img.oss-cn-shenzhen.aliyuncs.com/head%2Fhead-bear-.png
         * yulin : 97480
         * emotionStage : 0
         * is :
         * labelsList : []
         * userName : 97480
         */
        @Column(name = "YU_LIN", isId = true, property = "NOT NULL")
        private String yulin;
        @Column(name = "IS_LOGIN")
        private boolean isLogin;
        @Column(name = "GENDER")
        private int gender;
        @Column(name = "FANS_COUNT")
        private int fansCount;
        @Column(name = "ATTENTION_COUNT")
        private int attentionCount;
        @Column(name = "TEL")
        private Object tel;
        @Column(name = "OCCUPATION")
        private String occupation;
        @Column(name = "WORK_DESCRIPT")
        private String workDescript;
        @Column(name = "BIRTHDAY")
        private String birthday;
        @Column(name = "AGE")
        private int age;
        @Column(name = "CONSTELLATION")
        private String constellation;
        @Column(name = "IMG")
        private String img;
        @Column(name = "EMOTION_STAGE")
        private int emotionStage;
        @Column(name = "IS")
        private String is;
        @Column(name = "USER_NAME")
        private String userName;
        private List<?> labelsList;


        public DataBean() {
        }


        public boolean isLogin() {
            return isLogin;
        }

        public void setLogin(boolean login) {
            isLogin = login;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public int getAttentionCount() {
            return attentionCount;
        }

        public void setAttentionCount(int attentionCount) {
            this.attentionCount = attentionCount;
        }

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getWorkDescript() {
            return workDescript;
        }

        public void setWorkDescript(String workDescript) {
            this.workDescript = workDescript;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getYulin() {
            return yulin;
        }

        public void setYulin(String yulin) {
            this.yulin = yulin;
        }

        public int getEmotionStage() {
            return emotionStage;
        }

        public void setEmotionStage(int emotionStage) {
            this.emotionStage = emotionStage;
        }

        public String getIs() {
            return is;
        }

        public void setIs(String is) {
            this.is = is;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<?> getLabelsList() {
            return labelsList;
        }

        public void setLabelsList(List<?> labelsList) {
            this.labelsList = labelsList;
        }
    }


    /**
     * code : -1
     * height : 0
     * width : 0
     * success : false
     * data : null
     * message : 帐号或者密码有误，请重新登录!
     *//*

    private int code;
    private int height;
    private int width;
    private boolean success;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
    }*/
}
