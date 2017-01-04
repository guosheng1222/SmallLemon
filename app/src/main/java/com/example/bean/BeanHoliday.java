package com.example.bean;

/**
 * Created by PC on 2017/1/4.
 */

public class BeanHoliday {
    private int code;
    private int height;
    private int width;
    private boolean success;
    private DataBean data;
    private Object message;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * remark : http://www.yulin520.com/a2a/h/i/yulin/h5_festival
         * name : 春节-  1.28
         * img : http://img1.yulin520.com/news/JULIM57SWHT0OA56ZQ2N.png#210_210
         * holidayDetails : http://www.yulin520.com/a2a/h/i/app/next_festival
         * festivalTime : 1485532800000
         * festivalId : 7
         */

        private String remark;
        private String name;
        private String img;
        private String holidayDetails;
        private long festivalTime;
        private int festivalId;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getHolidayDetails() {
            return holidayDetails;
        }

        public void setHolidayDetails(String holidayDetails) {
            this.holidayDetails = holidayDetails;
        }

        public long getFestivalTime() {
            return festivalTime;
        }

        public void setFestivalTime(long festivalTime) {
            this.festivalTime = festivalTime;
        }

        public int getFestivalId() {
            return festivalId;
        }

        public void setFestivalId(int festivalId) {
            this.festivalId = festivalId;
        }
    }
}
