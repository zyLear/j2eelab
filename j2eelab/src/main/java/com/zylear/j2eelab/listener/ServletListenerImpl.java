package com.zylear.j2eelab.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by xiezongyu on 2019/7/2.
 */
public class ServletListenerImpl implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(ServletListenerImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("servlet context init test do something");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
