package com.gitee.phaeris;

import org.springframework.jdbc.datasource.AbstractDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wyh
 * @since 2022/10/14
 */
public class CalciteDatasource extends AbstractDataSource {

    /**
     * 配置文件路径
     * <p>
     * e.g. config/my.json
     */
    private final String configPath;

    /**
     * 是否忽略表名字段名大小写
     */
    private final boolean ignoreCase;

    public CalciteDatasource(String configPath, boolean ignoreCase) {
        this.configPath = configPath;
        this.ignoreCase = ignoreCase;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ConnectionHelper.getConnection(configPath, ignoreCase);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }
}
