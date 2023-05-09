package com.gitee.phaeris;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.gitee.phaeris.config.AstrubProperties;
import org.apache.calcite.config.CalciteConnectionProperty;
import org.apache.calcite.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.gitee.phaeris.constants.CalciteConstants.*;

/**
 * @author wyh
 * @since 1.0
 */
public class ConnectionHelper {

    private static AstrubProperties astrubProperties;

    @Autowired
    @SuppressWarnings("all")
    public void setUp(AstrubProperties astrubProperties) {
        ConnectionHelper.astrubProperties = astrubProperties;
        //解决中文映射不对的问题
        System.setProperty("saffron.default.charset", "UTF-8");
    }

    /**
     * 根据配置生成数据源
     *
     * @return datasource
     */
    public static CalciteDatasource getDatasource() {
        List<Schema> schemas = astrubProperties.getSchemas();
        if (schemas == null || schemas.isEmpty()) {
            throw new IllegalArgumentException("Please set your schema config.");
        }
        String content = STHolder.getConfig(schemas, schemas.get(0).getName());
        String path = getPath();
        FileUtil.appendString(content, path, StandardCharsets.UTF_8);
        return new CalciteDatasource(path, astrubProperties.getLex(), astrubProperties.isIgnoreCase());
    }

    /**
     * 根据calcite配置文件生成connection
     * <p>
     * calcite properties支持哪些属性详见{@link org.apache.calcite.config.CalciteConnectionProperty}
     *
     * @param path       calcite配置文件路径
     * @param ignoreCase 是否忽略大小写
     * @param lex        sql语法解析器
     * @return 数据库连接
     * @throws SQLException 获取连接可能导致异常
     */
    public static Connection getConnection(String path, boolean ignoreCase, String lex) throws SQLException {
        Properties info = new Properties();
        // 配置文件路径
        info.setProperty(CalciteConnectionProperty.MODEL.camelName(), path);
        // 设置SQL解析器
        info.setProperty(CalciteConnectionProperty.LEX.camelName(), StrUtil.isNotBlank(lex) ? lex : DEFAULT_LEX);
        if (ignoreCase) {
            // 设置大小写不敏感
            info.setProperty(CalciteConnectionProperty.CASE_SENSITIVE.camelName(), String.valueOf(false));
        }
        return DriverManager.getConnection(Driver.CONNECT_STRING_PREFIX, info);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static String getPath() {
        File file = FileUtil.file(CONFIG_PATH);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException("Init calcite config file exception.");
        }
        return file.getAbsolutePath();
    }
}
