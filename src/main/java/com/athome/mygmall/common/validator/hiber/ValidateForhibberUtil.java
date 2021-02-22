package com.athome.mygmall.common.validator.hiber;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;

import com.athome.mygmall.domain.User;

public class ValidateForhibberUtil {

	public static void serviceValid(Object object){
		   String validMessage = "";
		   //初始化检查器。
		   ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
		         .configure()
		         .failFast( false )
		         .buildValidatorFactory();
		   Validator validator = validatorFactory.getValidator();
		   //检查object
		   Set<ConstraintViolation<Object>> set = validator.validate(object);
		   //循环set，获取检查结果
		   for(ConstraintViolation<Object> voset : set){
		      validMessage = validMessage + voset.getMessage() +";";
		   }
		   if(!StringUtils.isEmpty(validMessage)){
		      //抛出业务异常
//		      throw new XXXException(validMessage);
		   }
		}
	
	public static void main(String[] args) {
		User user = new User();
		user.setAge(1);
		user.setDescription("用户描述");
		user.setName("小妖");
		user.setPassword("password");
		user.setSex("人妖");
		user.setUsername("xiaoyao");
		
		serviceValid(user);
	}
}
