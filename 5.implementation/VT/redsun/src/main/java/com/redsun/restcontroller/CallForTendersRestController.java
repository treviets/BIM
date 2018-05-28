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
import com.redsun.entities.Result;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;

/**
 * Created by HauLL on 3/3/2017.
 */
@RestController
@RequestMapping("restful-call-for-tender")
public class CallForTendersRestController extends BaseController{

	
    @RequestMapping(value = "list/{clientId}/{pageNo}/{itemsPerPage}", method = RequestMethod.GET)
    public Result listCallForTender(@PathVariable final Integer clientId, @PathVariable final Integer pageNo, @PathVariable final Integer itemsPerPage, HttpServletRequest request){
        final Map<String, Integer> mapUrl = new HashMap<>();
        mapUrl.put("clientId", clientId);
        mapUrl.put("pageNo", pageNo);
        mapUrl.put("itemsPerPage", itemsPerPage);
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.CALL_FOR_TENDER_LIST + "{clientId}/{pageNo}/{itemsPerPage}", mapUrl);
    }

    @RequestMapping(value = "get/{clientId}/{tenderId}", method = RequestMethod.GET)
    public Result getTenderById(@PathVariable final Integer clientId, @PathVariable final Integer tenderId, HttpServletRequest request){
        final Map<String, Integer> mapUrl = new HashMap<String, Integer>();
        mapUrl.put("clientId", clientId);
        mapUrl.put("tenderId", tenderId);
        return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.CALL_FOR_TENDER_GET + "{clientId}/{tenderId}", mapUrl);
    }

    @RequestMapping(value = "update/{tenderId}", method = RequestMethod.PUT)
    public Object updateTender(@PathVariable final Integer tenderId, @RequestBody final Map<String, Object> tender, HttpServletRequest request){
        return RestAPIHelper.put(getMainDomain(request) + RedSunURLs.CALL_FOR_TENDER_UPDATE + tenderId, tender, new HashMap<>());
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Object createTender(@RequestBody final Map<String, Object> tender, HttpServletRequest request){
        return RestAPIHelper.post(getMainDomain(request) + RedSunURLs.CALL_FOR_TENDER_INSERT, tender);
    }

    @RequestMapping(value = "delete/{refType}/{tenderId}", method = RequestMethod.DELETE)
    public Object deleteTender(@PathVariable final String refType, @PathVariable final Integer tenderId, HttpServletRequest request){
        return RestAPIHelper.delete(getMainDomain(request) + RedSunURLs.CALL_FOR_TENDER_DELETE + refType + "/" + tenderId, null, null);
    }

}
