package com.redsun.utils;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.redsun.entities.Result;

/**
 * Class API helper to call Restful API service
 * Ref: http://howtodoinjava.com/spring/spring-restful/spring-restful-client-resttemplate-example/
 * Created by sam on 1/21/17.
 */
public class RestAPIHelper {

	
    /**
     * post method helper
     * @param uri
     * @param postData
     * @return
     */
    public static Object post(final String uri, final Object postData){
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, postData, postData.getClass());
    }

     /**
     * this method use for delete/put http method or any http method need to
      * pass both body & urlVariable to restful service
     * @param uri
     * @param urlVariables
     * @return
     */
    public static Object generalMethod(final String uri, final HttpMethod httpMethod, final Object bodyData, final Map<String, ?> urlVariables){
        final RestTemplate restTemplate = new RestTemplate();
        // create header of http entity
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // set body = bodyData & header to http entity
        final HttpEntity<Object> entity = new HttpEntity<Object>(bodyData, headers);
        // if urlVariable is empty or null then skip it
        if(urlVariables == null || urlVariables.isEmpty()){
            // send data to restful
            return restTemplate.exchange(uri, httpMethod, entity, Object.class);
        }
        // send data to restful
        return restTemplate.exchange(uri, httpMethod, entity, Object.class, urlVariables);
    }

    /**
     * put method helper
     * @param uri
     * @param bodyData
     * @param urlVariables
     * @return
     */
    public static Object put(final String uri, final Object bodyData, final Map<String, ?> urlVariables){
        return RestAPIHelper.generalMethod(uri, HttpMethod.PUT, bodyData, urlVariables);
    }

    /**
     * delete method helper
     * @param uri
     * @param bodyData
     * @param urlVariables
     * @return
     */
    public static Object delete(final String uri, final Object bodyData, final Map<String, ?> urlVariables){
        return RestAPIHelper.generalMethod(uri, HttpMethod.DELETE, bodyData, urlVariables);
    }


    /**
     * get method helper
     * @param uri
     * @param urlVariables
     * @return
     */
    public static Result get(final String uri, final Map<String, ?> urlVariables){
        final RestTemplate restTemplate = new RestTemplate();
        if(urlVariables == null){
            return restTemplate.getForObject(uri, Result.class, new HashMap<String, Object>());
        }
        return restTemplate.getForObject(uri, Result.class, urlVariables);
    }
}
