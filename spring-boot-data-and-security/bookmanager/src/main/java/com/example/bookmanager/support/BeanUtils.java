package com.example.bookmanager.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// implements ApplicationContextAware 을 하여
// ApplicationContext 을 가져올 수 있게 된다.
@Component
public class BeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    // implements 에 의해 ApplicationContext 을 가져올 수 있게 된 모습
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

}
