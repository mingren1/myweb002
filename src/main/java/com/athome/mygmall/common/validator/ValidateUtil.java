package com.athome.mygmall.common.validator;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.RegexValidator;


public class ValidateUtil {

	public void validateDate() {
		// 获取日期验证  
        DateValidator validator = DateValidator.getInstance();  
    
        // 验证/转换日期  
        Date fooDate = validator.validate("fooString", "dd/MM/yyyy");  
        if (fooDate == null) {  
            // 错误 不是日期  
            return;  
        }
	}
	
	public void validateExpr() {
		// 设置参数  
        boolean caseSensitive = false;  
        String regex1   = "^([A-Z]*)(?:\\-)([A-Z]*)*$";
        String regex2   = "^([A-Z]*)$";  
        String[] regexs = new String[] {regex1, regex1};  
    
        // 创建验证  
        RegexValidator validator = new RegexValidator(regexs, caseSensitive);  
    
        // 验证返回boolean  
        boolean valid = validator.isValid("abc-def");  
    
        // 验证返回字符串  
        String result = validator.validate("abc-def");  
    
        // 验证返回数组  
        String[] groups = validator.match("abc-def");
	}
	
	public void validateFile() throws Exception {
		//加载验证配置文件  
		InputStream in = this.getClass().getResourceAsStream("validator-name-required.xml");  
		  
		ValidatorResources resources = new ValidatorResources(in);  
		//这个是自己创建的bean 我这里省略了  
		Name name = new Name();  
		  
		Validator validator = new Validator(resources, "nameForm");  
		//设置参数  
		validator.setParameter(Validator.BEAN_PARAM, name);  
		  
		  
		Map results = null;  
		//验证  
//		results = validator.validate();  
//		ValidatorResults results = validator.validate();
//		  
//		if (results.get("firstName") == null) {  
//		    //验证成功  
//		} else {  
//		    //有错误     int errors = ((Integer)results.get("firstName")).intValue();  
//		}
	}
	
	public class Name {
		
	}
}
