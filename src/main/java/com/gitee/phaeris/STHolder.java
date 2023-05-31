package com.gitee.phaeris;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;

import static com.gitee.phaeris.constants.STConstants.*;

/**
 * @author wyh
 * @since 2023/5/6
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class STHolder {

    private static final STGroupFile ST_GROUP_FILE = new STGroupFile(TEMP_PATH);

    /**
     * 获取calcite配置
     *
     * @param schemas schema列表
     * @param def     默认schema
     * @return instance
     */
    public static String getConfig(List<Schema> schemas, String def) {
        return ST_GROUP_FILE.getInstanceOf(FUNC_GET_CONFIG)
                .add(ARG_SCHEMAS, schemas)
                .add(ARG_DEFAULT_SCHEMA, def)
                .render();
    }
}
