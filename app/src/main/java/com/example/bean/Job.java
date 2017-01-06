package com.example.bean;

import java.util.List;

/**
 * Created by asus on 2017/1/3.
 */

public class Job {

    private int code;
    private int width;
    private int height;
    private boolean success;
    private Object message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

        private String code;
        private String pcode;
        private String url;
        private int occId;
        private String title;
        private String showName;
        private long createDate;
        private int seat;
        private String treepath;
        private String text;
        private Object checked;
        private boolean expanded;
        private String icon;
        private boolean leaf;
        private String id;
        private List<ChildrenBean> children;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getOccId() {
            return occId;
        }

        public void setOccId(int occId) {
            this.occId = occId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public String getTreepath() {
            return treepath;
        }

        public void setTreepath(String treepath) {
            this.treepath = treepath;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Object getChecked() {
            return checked;
        }

        public void setChecked(Object checked) {
            this.checked = checked;
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {


            private String code;
            private String pcode;
            private String url;
            private int occId;
            private String title;
            private String showName;
            private long createDate;
            private int seat;
            private String treepath;
            private String text;
            private Object checked;
            private boolean expanded;
            private String icon;
            private boolean leaf;
            private String id;
            private List<?> children;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getPcode() {
                return pcode;
            }

            public void setPcode(String pcode) {
                this.pcode = pcode;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getOccId() {
                return occId;
            }

            public void setOccId(int occId) {
                this.occId = occId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShowName() {
                return showName;
            }

            public void setShowName(String showName) {
                this.showName = showName;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public int getSeat() {
                return seat;
            }

            public void setSeat(int seat) {
                this.seat = seat;
            }

            public String getTreepath() {
                return treepath;
            }

            public void setTreepath(String treepath) {
                this.treepath = treepath;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public Object getChecked() {
                return checked;
            }

            public void setChecked(Object checked) {
                this.checked = checked;
            }

            public boolean isExpanded() {
                return expanded;
            }

            public void setExpanded(boolean expanded) {
                this.expanded = expanded;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public boolean isLeaf() {
                return leaf;
            }

            public void setLeaf(boolean leaf) {
                this.leaf = leaf;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }
}
