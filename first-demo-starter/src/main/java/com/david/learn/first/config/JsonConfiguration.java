package com.david.learn.first.config;

import com.david.learn.first.service.JsonConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 表示当前是配置类
@ConditionalOnClass(JsonConvertService.class) //存在这个JsonConvertService类配置才生效
@EnableConfigurationProperties(JsonProperties.class) //使使用 @ConfigurationProperties 注解的类生效。
public class JsonConfiguration {
    private JsonProperties jsonProperties;

    /**
     * @autowired注解放在方法上:
     * 如果方法没有参数，spring IOC容器会在类加载完后执行一次这个方法；
     * 如果方法中有参数的话，还会从IOC容器中自动注入这个方法的参数，然后执行一次这个方法。
     * 自动注入配置类参数
     */
    @Autowired
    public JsonConfiguration(JsonProperties jsonProperties) {
        this.jsonProperties = jsonProperties;
    }

    @Bean
    @ConditionalOnMissingBean(JsonConvertService.class)
    public JsonConvertService jsonConvertService() {
        JsonConvertService jsonConvertService = new JsonConvertService();
        jsonConvertService.setPrefixName(jsonProperties.getPrefixName());
        jsonConvertService.setSuffixName(jsonProperties.getSuffixName());
        return jsonConvertService;
    }
}
