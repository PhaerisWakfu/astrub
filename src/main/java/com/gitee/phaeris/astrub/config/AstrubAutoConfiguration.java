package com.gitee.phaeris.astrub.config;

import com.gitee.phaeris.astrub.CalciteDatasource;
import com.gitee.phaeris.astrub.ConnectionHelper;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wyh
 * @since 2023/5/6
 */
@ConditionalOnProperty(prefix = AstrubProperties.PREFIX, value = "enabled", havingValue = "true")
@Configuration
@AllArgsConstructor
public class AstrubAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CalciteDatasource calciteDatasource() {
        return ConnectionHelper.getDatasource();
    }
}
