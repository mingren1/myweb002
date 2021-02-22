package com.athome.mygmall.common.javassist;

import java.lang.reflect.Method;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
//动态修改字节码文件
public class UpdateClass {

	@SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        try {
            ClassPool pool = ClassPool.getDefault();
            // 读取com.irish.User
            CtClass userClass = pool.get("com.irish.User");
            CtMethod method = new CtMethod(CtClass.voidType, "sum", new CtClass[] { CtClass.intType, CtClass.intType },
                    userClass);
            method.setBody("{System.out.println(\"sun:\" + ($1 + $2));}");
            // 添加方法
            userClass.addMethod(method);
            //将修改后的字节码写入到磁盘中，通过反编译工具，可以看到对类进行了修改
            userClass.writeFile("F:/test");
            // 动态执行方法
            Class clazz = userClass.toClass();
            Object newInstance = clazz.newInstance();
            Method sumMethod = clazz.getDeclaredMethod("sum", int.class, int.class);
            sumMethod.invoke(newInstance, 2, 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
