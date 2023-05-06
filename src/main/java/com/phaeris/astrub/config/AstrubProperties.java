package com.phaeris.astrub.config;

import com.phaeris.astrub.Schema;
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
@ConfigurationProperties(prefix = "astrub")
public class AstrubProperties {

    /**
     * 数据库（第一个是默认数据库）
     */
    private LinkedList<Schema> schemas;

    /**
     * 是否忽略大小写, 默认false
     * <p>
     * <li>关闭的情况下, 需要建表的时候字段与表名都使用大写, 否则会有找不到表与字段的情况</>
     * <li>开启的情况下, 可能会导致查询效率下降，因为 Calcite 将无法使用索引来加速查询</>
     */
    private boolean ignoreCase;
}
