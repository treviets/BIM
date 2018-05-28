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
 * Created by HauLL on 3/20/2017.
 */
@RestController
@RequestMapping("restful-tender")
public class TendersRestController extends BaseController{
	

    @RequestMapping(value = "list/{clientId}/{pageNo}/{itemsPerPage}", method = { RequestMethod.GET })
    public Object listTendersForPageAndFilter(@PathVariable final Integer clientId, @PathVariable final Integer pageNo, @PathVariable final Integer itemsPerPage, HttpServletRequest request) {
        final Map<String, Integer> mapUrl = new HashMap<>();
        mapUrl.put("clientId", clientId);
        mapUrl.put("pageNo", pageNo);
        mapUrl.put("itemsPerPage", itemsPerPage);
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.TENDERS_LIST + "{clientId}/{pageNo}/{itemsPerPage}", mapUrl);
    }

    @RequestMapping("getById/{id}")
    public Object getTenderById(@PathVariable("id") Integer id, HttpServletRequest request){
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.TENDERS_GET + id, new HashMap<>());
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Object addTender(@RequestBody final Map<String, Object> tender, HttpServletRequest request){
        return RestAPIHelper.post(getMainDomain(request) + RedSunURLs.TENDERS_INSERT, tender);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public Object updateTender(@PathVariable("id") final Integer id, @RequestBody final Map<String, Object> tender, HttpServletRequest request) {
        return RestAPIHelper.put(getMainDomain(request) + RedSunURLs.TENDERS_UPDATE + id, tender, new HashMap<>());
    }

	@RequestMapping(value = "delete/{refType}/{id}", method = RequestMethod.DELETE)
	public Object deleteTender(@PathVariable final String refType, @PathVariable final Integer id, HttpServletRequest request){
		return RestAPIHelper.delete(getMainDomain(request) + RedSunURLs.TENDERS_DELETE + "/" + refType + "/" + id, null, new HashMap<>());
	}
}
