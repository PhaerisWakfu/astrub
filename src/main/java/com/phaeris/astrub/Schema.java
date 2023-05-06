package com.phaeris.astrub;


import lombok.Data;

/**
 * @author wyh
 * @since 2023/4/24
 */
@Data
public class Schema {

    /**
     * 库名
     */
    private String name;

    /**
     * 驱动全限定名
     */
    private String driver;

    /**
     * 数据库连接url
     */
    private String url;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;
}
