package com.athome.mygmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/cart")
public class MainContrller {
	
//	public String index() {
//		return "index";
//	}
	
	@RequestMapping("/index")
	public String cartIndex() {
		System.out.println("aaaaaaaaa");
		return "index";
	}
	@RequestMapping("/cartList")
	public String cartList() {
		System.out.println("bbbbb");
		return "cart/cart";
	}

}
