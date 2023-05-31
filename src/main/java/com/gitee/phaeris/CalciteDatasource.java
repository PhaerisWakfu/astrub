package com.gitee.phaeris;

import org.springframework.jdbc.datasource.AbstractDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wyh
 * @since 2023/5/6
 */
public class CalciteDatasource extends AbstractDataSource {

    /**
     * 配置文件绝对路径
     * <p>
     * e.g. config/my.json
     */
    private final String configPath;

    /**
     * 是否忽略大小写
     * <p>开启则不要求表与字段都使用大写，但是可能会导致查询效率下降，因为Calcite将无法使用索引来加速查询</p>
     */
    private final boolean ignoreCase;

    /**
     * sql语法解析器{@link org.apache.calcite.config.Lex}
     */
    private final String lex;


    public CalciteDatasource(String configPath) {
        this(configPath, null, false);
    }

    public CalciteDatasource(String configPath, String lex) {
        this(configPath, lex, false);
    }

    public CalciteDatasource(String configPath, String lex, boolean ignoreCase) {
        this.configPath = configPath;
        this.ignoreCase = ignoreCase;
        this.lex = lex;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ConnectionHelper.getConnection(configPath, lex, ignoreCase);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnection();
    }
}
