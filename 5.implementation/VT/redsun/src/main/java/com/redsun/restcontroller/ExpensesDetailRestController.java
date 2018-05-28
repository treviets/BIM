package com.redsun.restcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.redsun.controller.BaseController;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Created by HauLL on 3/22/2017.
 */
@RestController
@RequestMapping("restful-expenses-detail")
public class ExpensesDetailRestController extends BaseController{

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Object addExpenseDetail(@RequestBody Map<String, Object> expenseDetail, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
        String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EXPENSES_DETAIL_URL_SERVICE_ROOT_INSERT;
        Object result = RestAPIHelper.post(redsunServiceUrl, expenseDetail);
        return result;
    }


    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public Object updateExpenseDetail(@PathVariable("id") Integer id, @RequestBody Map<String, Object> expenseDetail, HttpServletRequest request) {
        String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EXPENSES_DETAIL_URL_SERVICE_ROOT_UPDATE + id;
        Object result = RestAPIHelper.put(redsunServiceUrl, expenseDetail, null);
        return result;
    }

    // Delete.
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Object deleteExpenseDetail(@PathVariable("id") Integer id, HttpServletRequest request){
        String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EXPENSES_DETAIL_URL_SERVICE_ROOT_DELETE + id;
        Object result = RestAPIHelper.delete(redsunServiceUrl, null, null);
        return result;
    }

    @RequestMapping("getById/{id}")
    public Object getExpenseDetailById(@PathVariable("id") Integer id, HttpServletRequest request){
        String redsunServiceUrl = getMainDomain(request) + RedSunURLs.EXPENSES_DETAIL_URL_SERVICE_ROOT_GET_BY_ID + id;
        return RestAPIHelper.get(redsunServiceUrl, new HashMap<>());
    }

    @RequestMapping(value = "list/{clientId}/{expensesId}/{itemsPerPage}/{pageNo}", method = { RequestMethod.GET })
    public Object listExpenseDetailsByExpenseId(@PathVariable Integer clientId, @PathVariable Integer expensesId, @PathVariable Integer itemsPerPage,
                                                @PathVariable Integer pageNo, HttpServletRequest request) {
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.EXPENSES_DETAIL_URL_SERVICE_ROOT_LIST + clientId + "/" + expensesId + "/"
                + itemsPerPage + "/" + pageNo, new HashMap<>());
    }
}
