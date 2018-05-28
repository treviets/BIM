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
@RequestMapping("humanresource")
public class HRListController extends BaseController{

	@Autowired
	UserUtil userUtil;
	@Autowired
	ClientService clientService;
	// Department List.
	@RequestMapping(value = "DepartmentList", method = RequestMethod.GET)
	public String getDepartmentList() {
		return "hrm/DepartmentList";
	}
	// HR List.
	@RequestMapping(value = "HRList", method = RequestMethod.GET)
	public String getHRList() {
		return "hrm/HRList";
	}
	// HR Detail.
	@RequestMapping(value = "HRDetail", method = RequestMethod.GET)
	public String getHRDetail() {
		return "hrm/HRDetail";
	}
	
	@RequestMapping(value="/list/staffs", method = {RequestMethod.GET})
    @ResponseBody
    public Object getAllStaffs(HttpServletRequest request) throws Exception {
    	int clientId = userUtil.getClientIdOfLoginedUser();
    	Map<String, Object> listStaffs = new HashMap<String, Object>();
		String getDataURL = getMainDomain(request) + RedSunURLs.STAFFS_URL_GET_ALL + clientId;
        return RestAPIHelper.get(getDataURL, listStaffs);
    }
}
