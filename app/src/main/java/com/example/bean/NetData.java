package com.example.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 从网络请求下来的数据保存
 *
 * @author :   郗琛
 * @date :   2016/12/29
 */

@Table(name = "netData")      //为表创建NAME
public class NetData {
    @Column(            //id
            name = "ID",
            isId = true
    )
    private int id;
    /**
     * 请求路径
     */
    @Column(name = "URL", property = "NOT NULL")
    private String url;
    /**
     * 过期时间
     */
    @Column(name = "EXPIRATION_TIME", property = "NOT NULL")
    private String expirationTime;
    /**
     * 请求时间
     */
    @Column(name = "REQUEST_TIME", property = "NOT NULL")
    private String requestTime;


}
