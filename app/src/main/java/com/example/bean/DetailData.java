package com.example.bean;

import java.util.List;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/7.
 */

public class DetailData {

    /**
     * code : 1
     * data : [{"content":"希望不要出现这种我打你你不能打我的思想，大家都是爹妈生的，你打我，我就打你，这样才公平","createTime":1482979702000,"floor":"1楼","floorReplyTimes":0,"forumId":10460,"frmList":[],"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/513.jpg","host":0,"id":26352,"imgs":[],"phone":"","userId":8045,"userName":"杀被里梦在能你","yulin":"94246"},{"content":"你忍无可忍打他脸，他忍无可忍打你脸，两个都是辣鸡。吃瓜群众表示赶紧分","createTime":1482983629000,"floor":"2楼","floorReplyTimes":0,"forumId":10460,"frmList":[],"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E6%99%93%E4%B8%BD%E5%A4%B4%E5%83%8F3/150.jpg","host":0,"id":26353,"imgs":[],"phone":"","userId":7993,"userName":"喝饮料会咬管子的姑娘","yulin":"94090"},{"content":"不管怎么说 打女人的男人就是垃圾","createTime":1482993396000,"floor":"3楼","floorReplyTimes":0,"forumId":10460,"frmList":[],"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/525.jpg","host":0,"id":26354,"imgs":[],"phone":"","userId":8014,"userName":"CoooMP","yulin":"94153"},{"content":"你打他，他打你，分了吧，不然你以后孩子是爸妈混合双打","createTime":1483002161000,"floor":"4楼","floorReplyTimes":0,"forumId":10460,"frmList":[],"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/546.jpg","host":0,"id":26355,"imgs":[],"phone":"","userId":7698,"userName":"ZzLululy","yulin":"93205"},{"content":"骂你根本不管用","createTime":1483010690000,"floor":"5楼","floorReplyTimes":0,"forumId":10460,"frmList":[],"headImg":"http://img1.yulin520.com/head/GTYXHLVJI960OWM9XSE5.jpg","host":0,"id":26357,"imgs":[],"phone":"苹果手机","userId":6361,"userName":"terry","yulin":"88093"},{"content":"你也打他了 虽然他下手更重 最后结论是 你俩不太合适 家暴肯定有","createTime":1483013715000,"floor":"6楼","floorReplyTimes":0,"forumId":10460,"frmList":[],"headImg":"http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/471.jpg","host":0,"id":26356,"imgs":[],"phone":"","userId":7817,"userName":"孙嘉雪-","yulin":"93562"}]
     * height : 0
     * success : true
     * width : 0
     */

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
         * content : 希望不要出现这种我打你你不能打我的思想，大家都是爹妈生的，你打我，我就打你，这样才公平
         * createTime : 1482979702000
         * floor : 1楼
         * floorReplyTimes : 0
         * forumId : 10460
         * frmList : []
         * headImg : http://photo-60481.oss-cn-shenzhen.aliyuncs.com/%E5%B0%8F%E5%AE%87%E7%9A%84%E5%A4%B4%E5%83%8F%E7%AC%AC%E4%BA%8C%E6%B3%A2/513.jpg
         * host : 0
         * id : 26352
         * imgs : []
         * phone :
         * userId : 8045
         * userName : 杀被里梦在能你
         * yulin : 94246
         */

        private String content;
        private long createTime;
        private String floor;
        private int floorReplyTimes;
        private int forumId;
        private String headImg;
        private int host;
        private int id;
        private String phone;
        private int userId;
        private String userName;
        private String yulin;
        private List<?> frmList;
        private List<?> imgs;

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

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public int getFloorReplyTimes() {
            return floorReplyTimes;
        }

        public void setFloorReplyTimes(int floorReplyTimes) {
            this.floorReplyTimes = floorReplyTimes;
        }

        public int getForumId() {
            return forumId;
        }

        public void setForumId(int forumId) {
            this.forumId = forumId;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getHost() {
            return host;
        }

        public void setHost(int host) {
            this.host = host;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public List<?> getFrmList() {
            return frmList;
        }

        public void setFrmList(List<?> frmList) {
            this.frmList = frmList;
        }

        public List<?> getImgs() {
            return imgs;
        }

        public void setImgs(List<?> imgs) {
            this.imgs = imgs;
        }
    }
}
