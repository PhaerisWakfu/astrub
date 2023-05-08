package com.gitee.phaeris;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author wyh
 * @since 2023/5/8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalciteConstants {

    public static final String CONFIG_PATH = "astrub.json";

    public static final String MODEL_NAME = "model";

    public static final String CASE_SENSITIVE = "caseSensitive";

    public static final String LEX = "lex";

    public static final String LEX_MYSQL = "mysql";

    public static final String JDBC_PREFIX = "jdbc:calcite:";
}
