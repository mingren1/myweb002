package com.athome.mygmall.domain;

import java.net.URL;

public class Student {

	private String name; 
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		ClassLoader classLoader = Student.class.getClassLoader();
		URL resource = classLoader.getResource("MATE-INF/services/a.txt");
		String path = resource.getPath();
		System.out.println(resource);
	}


	public class NickName{
		private String xiaomin;

		
		/**
		 * 内部类调外部类方法
		 * @return
		 */
		public String getXiaomin() {
			String name2 = Student.this.getName();
			return xiaomin;
			
		}

		public void setXiaomin(String xiaomin) {
			this.xiaomin = xiaomin;
		}
		
		
	}
}
