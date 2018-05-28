package com.redsun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Expense Controller
 */
@Controller
@RequestMapping("expenses")
public class ExpensesController {

	@RequestMapping(value = "individual/list", method = RequestMethod.GET)
	public String listIndividualExpenses(){
		return "Financial/Expenses/IndividualExpenses_list";
	}

	@RequestMapping(value = {"individual/get", "individual/new"}, method = RequestMethod.GET)
	public String getIndividualExpenses(){
		return "Financial/Expenses/IndividualExpenses_detail";
	}

	@RequestMapping(value = "project/list", method = RequestMethod.GET)
	public String listProjectExpenses(){
		return "Financial/Expenses/ProjectExpenses_list";
	}

	@RequestMapping(value = {"project/get", "project/new"}, method = RequestMethod.GET)
	public String getProjectExpenses(){
		return "Financial/Expenses/ProjectExpenses_detail";
	}

}
