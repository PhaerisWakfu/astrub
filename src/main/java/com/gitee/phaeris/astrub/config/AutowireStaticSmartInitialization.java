package com.gitee.phaeris.astrub.config;

import com.gitee.phaeris.astrub.ConnectionHelper;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wyh
 * @since 2023/5/6
 */
@EnableConfigurationProperties(AstrubProperties.class)
@Configuration
public class AutowireStaticSmartInitialization implements SmartInitializingSingleton {

    private final AutowireCapableBeanFactory beanFactory;

    public AutowireStaticSmartInitialization(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 当所有的单例Bean初始化完成后，对static静态成员进行赋值
     */
    @Override
    public void afterSingletonsInstantiated() {
        // 因为是给static静态属性赋值，因此这里new一个实例做注入是可行的
        beanFactory.autowireBean(new ConnectionHelper());
    }
}