//package com.athome.mygmall.test;
//
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.support.AbstractRefreshableApplicationContext;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//public class Test2 {
//
//	//刷新所有bean
//	public static void main1(String[] args) {
//		//
//		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
//		((AbstractRefreshableApplicationContext) context).refresh();
//	}
//	//刷新指定bean
//	public static void main2(String[] args) {
//		DefaultListableBeanFactory defaultListableBeanFactory =(DefaultListableBeanFactory) xmlWebApplicationContext.getBeanFactory();
//		BeanDefinition beanDefinition = defaultListableBeanFactory.getBeanDefinition("pooledConnectionFactory");
//		beanDefinition.setBeanClassName(PooledConnectionFactory.class.getName());
//		defaultListableBeanFactory.registerBeanDefinition( "pooledConnectionFactory", beanDefinition);
//	}
//}
