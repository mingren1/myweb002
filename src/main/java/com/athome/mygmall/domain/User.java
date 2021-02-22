package com.athome.mygmall.domain;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.athome.mygmall.common.validator.hiber.anno.Sex;

public class User {
    @NotBlank(message = "名称不能为空")
    private String name;
    @Sex(message = "性别有误！！！")
    private String sex;

    @Range(min = 1, max = 120, message = "年龄必须为1-120岁")
    private Integer age;

    @NotBlank(message = "描述不能为空")
    private String description;

    @NotNull
    @Length(min = 6, max = 18, message = "用户名长度必须为6-18位")
    private String username;

    @NotNull
    @Length(min = 6, max = 18, message = "密码长度必须为6-18位")
    private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** get、set方法 */
    
    
}

