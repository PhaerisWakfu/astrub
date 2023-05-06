package com.phaeris.astrub.config;

import cn.hutool.core.io.FileUtil;
import com.phaeris.astrub.CalciteDatasource;
import com.phaeris.astrub.STHolder;
import com.phaeris.astrub.Schema;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * @author wyh
 * @since 2023/4/24
 */
@EnableConfigurationProperties(AstrubProperties.class)
@Configuration
@AllArgsConstructor
public class AstrubAutoConfiguration {

    private static final String CONFIG_PATH = "astrub.json";

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public CalciteDatasource calciteDatasource(AstrubProperties schemaProperties) throws IOException {
        List<Schema> schemas = schemaProperties.getSchemas();
        if (schemas == null || schemas.isEmpty()) {
            throw new IllegalArgumentException("Please set your schema config.");
        }
        schemas.forEach(s -> s.setName(s.getName().toUpperCase(Locale.ROOT)));
        String content = STHolder.getConfig(schemas, schemas.get(0).getName());
        String path = getPath();
        FileUtil.appendString(content, path, StandardCharsets.UTF_8);
        return new CalciteDatasource(path, schemaProperties.isIgnoreCase());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String getPath() throws IOException {
        File file = FileUtil.file(CONFIG_PATH);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file.getAbsolutePath();
    }
}
