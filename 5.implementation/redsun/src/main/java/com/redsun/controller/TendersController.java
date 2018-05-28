package com.redsun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Tender Controller
 */
@Controller
@RequestMapping("tender")
public class TendersController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listTender(){
		return "Financial/Expenses/Tenders_list";
	}

	@RequestMapping(value = {"get", "new"}, method = RequestMethod.GET)
	public String getTenderDetail(){
		return "Financial/Expenses/Tenders_detail";
	}

}
