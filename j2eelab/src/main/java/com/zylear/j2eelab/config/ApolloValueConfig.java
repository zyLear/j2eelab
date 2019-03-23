package com.zylear.j2eelab.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiezongyu on 2019/3/22.
 */
@Configuration
//@EnableApolloConfig
public class ApolloValueConfig {


    @Value("${ap.test:df}")
    private String input;


//    @Bean
//    public String viu() {
//        System.out.println(System.getProperty("env"));
//        System.out.println(input);
//        return "";
//    }

    @Bean
    public String s() {

        Config config = ConfigService.getAppConfig();
        System.out.println(config.getProperty("ap.test", "xxs"));


        return "";
    }

}
