package com.redsun.controller.hrm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redsun.controller.BaseController;
import com.redsun.service.ClientService;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Resource Controller
 */
@Controller
@RequestMapping("departmentservice")
public class DepartmentController extends BaseController{

	@Autowired
	UserUtil userUtil;
	
	@RequestMapping(value="list/departments", method = {RequestMethod.GET})
    @ResponseBody
    public Object getAllDepartment(HttpServletRequest request) throws Exception {
		String userName = "NT005"; //userUtil.getLoginedUsername();
    	int clientId = 2; //userUtil.getClientIdOfLoginedUser();
    	Map<String, Object> listDepartments = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.STAFFS_URL_GET_ALL_DEPARTMENT + userName + "/" + clientId;
        return RestAPIHelper.get(getDataURL, listDepartments);
    }

}
