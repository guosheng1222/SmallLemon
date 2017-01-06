package com.example.bean;

import java.util.List;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/4.
 */

public class BoardTopData {


    private int code;
    private int height;
    private boolean success;
    private int width;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * anonymous : 1
         * audit : 1
         * click : 1
         * content : 我跟男朋友吵架每次都很凶，上一次我打了他之后他还手了，还掐我脖子，虽然现在和好了，但一直介怀他打我这件事，希望网友们骂醒我，我真不知道该怎么做了。
         * createTime : 1482979159000
         * emotionStage1 : 0
         * emotionStage2 : 1
         * emotionStage3 : 0
         * emotionStage4 : 0
         * emotionStage5 : 0
         * emotionStage6 : 0
         * forumType : 10
         * headImg : http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/456.jpg
         * id : 10460
         * img : http://img1.yulin520.com/news/YW0JRNRLKIK0OQ3UZIY9.jpg#464_658
         * imgs : []
         * indexImg :
         * nice : 620
         * phone : 安卓手机
         * readOnly : 0
         * recommend : 1
         * replyTimes : 7
         * status : 1
         * title : 男朋友家暴该怎么做
         * top : 1
         * topTime : 1483415477000
         * url : http://www.xiaoningle.com/a2a/h/forum/o/10460?a=1483525858584
         * userId : 7748
         * userName : 良啊辰辰
         * yulin : 93355
         */

        private int anonymous;
        private int audit;
        private int click;
        private String content;
        private long createTime;
        private int emotionStage1;
        private int emotionStage2;
        private int emotionStage3;
        private int emotionStage4;
        private int emotionStage5;
        private int emotionStage6;
        private int forumType;
        private String headImg;
        private int id;
        private String img;
        private String indexImg;
        private int nice;
        private String phone;
        private int readOnly;
        private int recommend;
        private int replyTimes;
        private int status;
        private String title;
        private int top;
        private long topTime;
        private String url;
        private int userId;
        private String userName;
        private String yulin;
        private List<?> imgs;

        public int getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(int anonymous) {
            this.anonymous = anonymous;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public int getClick() {
            return click;
        }

        public void setClick(int click) {
            this.click = click;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public int getForumType() {
            return forumType;
        }

        public void setForumType(int forumType) {
            this.forumType = forumType;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIndexImg() {
            return indexImg;
        }

        public void setIndexImg(String indexImg) {
            this.indexImg = indexImg;
        }

        public int getNice() {
            return nice;
        }

        public void setNice(int nice) {
            this.nice = nice;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getReadOnly() {
            return readOnly;
        }

        public void setReadOnly(int readOnly) {
            this.readOnly = readOnly;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public int getReplyTimes() {
            return replyTimes;
        }

        public void setReplyTimes(int replyTimes) {
            this.replyTimes = replyTimes;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public long getTopTime() {
            return topTime;
        }

        public void setTopTime(long topTime) {
            this.topTime = topTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getYulin() {
            return yulin;
        }

        public void setYulin(String yulin) {
            this.yulin = yulin;
        }

        public List<?> getImgs() {
            return imgs;
        }

        public void setImgs(List<?> imgs) {
            this.imgs = imgs;
        }
    }
}
