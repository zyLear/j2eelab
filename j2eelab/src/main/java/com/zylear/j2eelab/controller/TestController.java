package com.zylear.j2eelab.controller;

import com.zylear.j2eelab.config.mq.MqConfig;
import com.zylear.j2eelab.domain.Student;
import com.zylear.j2eelab.manager.TxManager;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiezongyu on 2018/8/22.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TxManager txManager;
//    @Value("${okok}")
    private String ok;

    @RequestMapping("/send")
    @ResponseBody
    public String send(@RequestParam("msg") String msg) {
        rabbitTemplate.convertAndSend(MqConfig.ONE_PREFIX + MqConfig.ONE_VERSION, msg);
        return "ok";
    }

    @RequestMapping("/student-transaction")
    @ResponseBody
    public String test1() {

        txManager.insert(new Student(), new Student());
//        txManager.testt(new Student(), new Student());



        return "ok";
    }

    @RequestMapping("/show")
    @ResponseBody
    public String s() {

//        txManager.insert(new Student(), new Student());
////        txManager.testt(new Student(), new Student());
//


        return ok;
    }


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() + 6 * 60 * 1000);
        System.out.println(System.currentTimeMillis() + 5 * 60 * 1000);
        System.out.println(System.currentTimeMillis() + 7 * 60 * 1000);
        System.out.println(System.currentTimeMillis() + 8 * 60 * 1000);
    }


}
