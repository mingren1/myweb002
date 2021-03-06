package com.athome.mygmall.common.javassist;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

//创建字节码文件
public class CreateClass {

	public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException {
		ClassPool pool = ClassPool.getDefault();
		// 1.创建user类
		CtClass userClass = pool.makeClass("com.irish.entity.User");
		// 2.创建name 和age属性
		CtField nameField = CtField.make("private String name;", userClass);
		CtField ageField = CtField.make("private Integer age;", userClass);
		// 3.添加属性
		userClass.addField(nameField);
		userClass.addField(ageField);
		// 4.创建方法
		CtMethod nameMethod = CtMethod.make("public String getName() {return name;}", userClass);
		// 5.添加方法
		userClass.addMethod(nameMethod);
		// 6.添加构造函数
		CtConstructor ctConstructor = new CtConstructor(
				new CtClass[] { pool.get("java.lang.String"), pool.get("java.lang.Integer") }, userClass);

		ctConstructor.setBody("    { this.name = name; this.age = age; }");
		userClass.addConstructor(ctConstructor);

		// 生成class文件
		userClass.writeFile("F:/test");
		System.out.println("生成字节码，成功！");
	}

}
