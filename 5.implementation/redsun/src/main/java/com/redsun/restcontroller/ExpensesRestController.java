package com.redsun.restcontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.controller.BaseController;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Created by HauLL on 3/22/2017.
 */
@RestController
@RequestMapping("restful-expenses")
public class ExpensesRestController extends BaseController{

	
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Object addExpense(@RequestBody final Map<String, Object> expense, HttpServletRequest request) {
        return RestAPIHelper.post(getMainDomain(request) + RedSunURLs.EXPENSES_URL_SERVICE_ROOT_INSERT, expense);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public Object updateExpense(@PathVariable final Integer id, @RequestBody final Map<String, Object> expense, HttpServletRequest request) {
        return RestAPIHelper.put(getMainDomain(request) + RedSunURLs.EXPENSES_URL_SERVICE_ROOT_UPDATE + id, expense, null);
    }

    @RequestMapping(value = "delete/{scope}/{id}", method = RequestMethod.DELETE)
    public Object deleteExpense(@PathVariable final String scope, @PathVariable final Integer id, HttpServletRequest request){
        return RestAPIHelper.delete(getMainDomain(request) + RedSunURLs.EXPENSES_URL_SERVICE_ROOT_DELETE + scope + "/" + id, null, null);
    }

    @RequestMapping("getById/{id}")
    public Object getExpenseById(@PathVariable final Integer id, HttpServletRequest request){
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.EXPENSES_URL_SERVICE_ROOT_GET_BY_ID + id, new HashMap<>());
    }

    @RequestMapping(value = "list/{clientId}/{scope}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
    public Object listExpensesForPageAndFilter(@PathVariable final Integer clientId, @PathVariable final String scope,
                                               @PathVariable final Integer itemsPerPage, @PathVariable final Integer pageNo, HttpServletRequest request) {
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.EXPENSES_URL_SERVICE_ROOT_LIST + clientId + "/" + scope + "/" + itemsPerPage + "/" + pageNo, new HashMap<>());
    }
}
