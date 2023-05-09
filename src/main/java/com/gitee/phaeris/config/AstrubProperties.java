package com.gitee.phaeris.config;

import com.gitee.phaeris.Schema;
import com.gitee.phaeris.constants.CalciteConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedList;

/**
 * @author wyh
 * @since 2023/4/24
 */
@Getter
@Setter
@ConfigurationProperties(prefix = AstrubProperties.PREFIX)
public class AstrubProperties {

    /**
     * 配置前缀
     */
    public static final String PREFIX = "astrub";

    /**
     * 是否注册datasource为bean
     * <p>如果本身已有数据源可能会导致覆盖的情况</p>
     */
    private boolean enabled;

    /**
     * 数据库
     * <p>第一个是默认schema</p>
     */
    private LinkedList<Schema> schemas;

    /**
     * sql语法解析器{@link org.apache.calcite.config.Lex}
     */
    private String lex = CalciteConstants.DEFAULT_LEX;

    /**
     * 是否忽略大小写
     * <p>开启则不要求表与字段都使用大写，但是可能会导致查询效率下降，因为Calcite将无法使用索引来加速查询</p>
     */
    private boolean ignoreCase;
}
