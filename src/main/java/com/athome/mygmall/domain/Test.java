package com.athome.mygmall.domain;

public class Test {

	public static void main(String[] args) {
		a: // a在此处,运行结果为：i:0
		for (int j = 0; j < 3; j++) {
//			a: // a在此处，结果与无a标记一样
			for (int i = 0; i < 3; i++) {
				if (i == 1) {
					break a;
				}
				System.out.print("i:" + i);
			}
			System.out.println("j：" + j);
		}
	}
}
