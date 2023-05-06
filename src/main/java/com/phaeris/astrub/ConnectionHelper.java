package com.phaeris.astrub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author wyh
 * @since 1.0
 */
public class ConnectionHelper {

    private static final String MODEL_NAME = "model";

    private static final String CASE_SENSITIVE = "caseSensitive";

    private static final String JDBC_PREFIX = "jdbc:calcite:";

    static {
        //解决中文映射不对的问题
        System.setProperty("saffron.default.charset", "UTF-8");
    }

    public static Connection getConnection(String path, boolean ignoreCase) throws SQLException {
        Properties info = new Properties();
        // 配置文件路径
        info.setProperty(MODEL_NAME, path);
        if (ignoreCase) {
            // 设置大小写不敏感
            info.setProperty(CASE_SENSITIVE, String.valueOf(false));
        }
        return DriverManager.getConnection(JDBC_PREFIX, info);
    }
}
