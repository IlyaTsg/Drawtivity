package com.ETU.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Замена web.xml
public class SerlvetInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {AppConfig.class};
    }

    // Указываем класс с конфигурацией приложения
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    // Указываем, что все запросы необходимо передавать на DespatcherSerlvet
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
