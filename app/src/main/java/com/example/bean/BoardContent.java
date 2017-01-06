package com.example.bean;

import java.util.List;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/4.
 */

public class BoardContent {



    private int code;
    private boolean success;
    private int height;
    private int width;
    private Object message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nice : 1
         * replycreateTime : null
         * replyContent : null
         * audit : 2
         * forumType : 11
         * topTime : 1476858055000
         * top : 0
         * recommend : 0
         * emotionStage1 : 0
         * emotionStage2 : 1
         * emotionStage3 : 0
         * emotionStage4 : 0
         * emotionStage5 : 0
         * emotionStage6 : 0
         * replyTimes : 5
         * indexImg :
         * userId : 6309
         * createTime : 1476858055000
         * img :
         * yulin : 87937
         * click : 0
         * imgs : []
         * url : http://www.xiaoningle.com/a2a/h/forum/o/9609?a=1483531257478
         * phone : 安卓手机
         * title : 男票啪前啪后两个样，是他太累还是不爱我？
         * headImg : http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F/236.jpg
         * frList : null
         * readOnly : 0
         * userName : 林中有片海
         * anonymous : 0
         * id : 9609
         * content : 男票每次啪啪啪前各种粘人，我各种要求都屁颠屁颠的去做，啪啪啪完就说好累啊，然后倒头就睡，我说你陪我说会儿话呗，但是他完全不搭理我。男人啪啪啪完真的有那么累么？
         * status : 1
         */

        private int nice;
        private Object replycreateTime;
        private Object replyContent;
        private int audit;
        private int forumType;
        private long topTime;
        private int top;
        private int recommend;
        private int emotionStage1;
        private int emotionStage2;
        private int emotionStage3;
        private int emotionStage4;
        private int emotionStage5;
        private int emotionStage6;
        private int replyTimes;
        private String indexImg;
        private int userId;
        private long createTime;
        private String img;
        private String yulin;
        private int click;
        private String url;
        private String phone;
        private String title;
        private String headImg;
        private Object frList;
        private int readOnly;
        private String userName;
        private int anonymous;
        private int id;
        private String content;
        private int status;
        private List<?> imgs;

        public int getNice() {
            return nice;
        }

        public void setNice(int nice) {
            this.nice = nice;
        }

        public Object getReplycreateTime() {
            return replycreateTime;
        }

        public void setReplycreateTime(Object replycreateTime) {
            this.replycreateTime = replycreateTime;
        }

        public Object getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(Object replyContent) {
            this.replyContent = replyContent;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public int getForumType() {
            return forumType;
        }

        public void setForumType(int forumType) {
            this.forumType = forumType;
        }

        public long getTopTime() {
            return topTime;
        }

        public void setTopTime(long topTime) {
            this.topTime = topTime;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public int getEmotionStage1() {
            return emotionStage1;
        }

        public void setEmotionStage1(int emotionStage1) {
            this.emotionStage1 = emotionStage1;
        }

        public int getEmotionStage2() {
            return emotionStage2;
        }

        public void setEmotionStage2(int emotionStage2) {
            this.emotionStage2 = emotionStage2;
        }

        public int getEmotionStage3() {
            return emotionStage3;
        }

        public void setEmotionStage3(int emotionStage3) {
            this.emotionStage3 = emotionStage3;
        }

        public int getEmotionStage4() {
            return emotionStage4;
        }

        public void setEmotionStage4(int emotionStage4) {
            this.emotionStage4 = emotionStage4;
        }

        public int getEmotionStage5() {
            return emotionStage5;
        }

        public void setEmotionStage5(int emotionStage5) {
            this.emotionStage5 = emotionStage5;
        }

        public int getEmotionStage6() {
            return emotionStage6;
        }

        public void setEmotionStage6(int emotionStage6) {
            this.emotionStage6 = emotionStage6;
        }

        public int getReplyTimes() {
            return replyTimes;
        }

        public void setReplyTimes(int replyTimes) {
            this.replyTimes = replyTimes;
        }

        public String getIndexImg() {
            return indexImg;
        }

        public void setIndexImg(String indexImg) {
            this.indexImg = indexImg;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public int getClick() {
            return click;
        }

        public void setClick(int click) {
            this.click = click;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public Object getFrList() {
            return frList;
        }

        public void setFrList(Object frList) {
            this.frList = frList;
        }

        public int getReadOnly() {
            return readOnly;
        }

        public void setReadOnly(int readOnly) {
            this.readOnly = readOnly;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(int anonymous) {
            this.anonymous = anonymous;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<?> getImgs() {
            return imgs;
        }

        public void setImgs(List<?> imgs) {
            this.imgs = imgs;
        }
    }
}
