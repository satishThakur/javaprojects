package com.satish.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicatioContextHelper {
	
	private static ApplicationContext ctx=new ClassPathXmlApplicationContext("META-INF/application-context.xml");
	
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
	
	public static <T> T getBean(String beanName, Class<T> clazz){
		
		return ctx.getBean(beanName, clazz);
	}

}
