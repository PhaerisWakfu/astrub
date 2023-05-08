package com.gitee.phaeris.config;

import com.gitee.phaeris.Schema;
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
     */
    private boolean enabled;

    /**
     * 数据库（第一个是默认数据库）
     */
    private LinkedList<Schema> schemas;

    /**
     * 是否忽略表名字段名大小写
     */
    private boolean ignoreCase;
}
