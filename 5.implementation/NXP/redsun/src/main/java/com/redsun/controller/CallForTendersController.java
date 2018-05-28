package com.redsun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * CallForTender Controller
 */
@Controller
@RequestMapping("call-for-tender")
public class CallForTendersController {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String listCallForTender(){
		return "Financial/Expenses/CallForTenders_list";
	}

	@RequestMapping(value = {"get", "new"}, method = RequestMethod.GET)
	public String getCallForTenderDetail(){
		return "Financial/Expenses/CallForTenders_detail";
	}

}
