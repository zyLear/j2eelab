package com.zylear.j2eelab.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.zylear.j2eelab.annotation.AssignDefault;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiezongyu on 2019/3/22.
 */
@Configuration
//@EnableApolloConfig("config.properties")
public class ApolloValueConfig {

static {
    System.setProperty("env", "DEV");
}

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

//        Config config = ConfigService.getAppConfig();
//        System.out.println(config.getProperty("tt", "xxs"));

        return "";
    }

//    public static void main(String[] args) {
//        Config config = ConfigService.getConfig("config.properties");
//        System.out.println(config.getProperty("tesst", "xxs"));
//    }

}
