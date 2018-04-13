package com.article.recommend.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware{
    private static  ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext=applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 
    * @Title: getBean  
    * @Description: 通过name  
    * @param name
    * @return       
    * @return Object    
    * @author sw
    * @throws
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

   /**
    *
   * @Title: getBean  
   * @Description: 通过class  
   * @param clazz
   * @return       
   * @return T    
   * @author sw
   * @throws
    */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

   /**
    * 
   * @Title: getBean  
   * @Description: 通过name  和class
   * @param name
   * @param clazz
   * @return       
   * @return T    
   * @author sw
   * @throws
    */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
