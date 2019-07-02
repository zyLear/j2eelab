package com.zylear.j2eelab.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.zylear.j2eelab.dao.StudentMapper;
import com.zylear.j2eelab.domain.Student;
import com.zylear.j2eelab.domain.Teacher;
import com.zylear.j2eelab.domain.TeacherTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiezongyu on 2017/11/1.
 */
@Configuration
public class TestConfiguration {

//    @Value("${ttaa}")
//    private String test;
////    @Value("${okok}")
//    private String tt;


    @Bean("testTemplateTeacher")
    public Teacher getTemplateTeacher(TeacherTemplate teacherTemplate) {
        return teacherTemplate.getTeacher();
    }


//    @Bean
    public String teste(TeacherTemplate teacherTemplate, @Value("${xie}") String ds, StudentMapper studentMapper) {
        System.out.println("this is inside configuration's PostConstruct   " + teacherTemplate.getTeacher().toString());
        System.out.println(studentMapper.selectByPrimaryKey(1).toString());
        System.out.println("  " + ds);
        return "";
    }

    @ConditionalOnProperty(value = {"simulateClickOn"})
    @Bean(value = "testConditionalBean")
    public String conditionalBean() {

        return "conditionalBean";
    }

//    @Bean
//    public String showBBean(@Qualifier("testConditionalBean") String param) {
//        System.out.println("show conditional bean:" + param);
//        return "";
//    }

    @Bean("fdf")
    public Student sd() {
//        System.out.println(test);
//        System.out.println(tt);
        System.out.println(System.getProperty("zylear.enable"));
        return null;
    }

    @Bean("get-bean")
    public Student get() {
        return new Student(555, "get-b");
    }


//    @ApolloConfigChangeListener("config")
//    private void onChange(ConfigChangeEvent changeEvent) {
//        System.out.println(changeEvent.isChanged("okok"));
//
//        System.out.println(changeEvent.getChange("okok"));
//    }

//    @ApolloConfigChangeListener("config.properties")
//    private void onChangwe(ConfigChangeEvent changeEvent) {
//        System.out.println(changeEvent.isChanged("okok"));
//
//        System.out.println(changeEvent.getChange("okok"));
//    }
}
