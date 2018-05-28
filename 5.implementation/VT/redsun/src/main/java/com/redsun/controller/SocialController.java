package com.redsun.controller;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping(value = "/social")
public class SocialController extends BaseController {
	
	
	SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public String index(){
		return "Social";
	}

}
