package com.luv2code.customerTracker.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer { // bootstrapped

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MyAppConfig.class };   // replace servlet setting in abandon-web.xml
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };    // replace the servlet-mapping setting in abandon-web.xml
    }
}
