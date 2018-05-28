package com.redsun.restcontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redsun.controller.BaseController;
import com.redsun.utils.RedSunURLs;
import com.redsun.utils.RestAPIHelper;
import com.redsun.utils.UserUtil;

/**
 * Selection Controller
 */
@RestController
@RequestMapping("restful-selection")
public class SelectionsRestController extends BaseController{
	
	@Autowired
	UserUtil userUtil;

	private Object callService(final String actionName, HttpServletRequest request) throws Exception {
		String serviceUrl = getMainDomain(request) + RedSunURLs.SELECTION_URL_SERVICE + actionName + "?clientId=";
		int clientId = userUtil.getClientIdOfLoginedUser();
		serviceUrl += clientId;
		return RestAPIHelper.get(serviceUrl, new HashMap<>());
	}

	/**
	 * get projects
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list-projects")
	public Object listProjectsForSelection(HttpServletRequest request)  throws Exception {
		return callService("list-projects", request);
	}

	/**
	 * get quotations
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list-quotation-types")
	public Object listQuotationTypesForSelection(HttpServletRequest request)  throws Exception {
		return callService("list-quotation-types", request);
	}

	/**
	 * get types
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list-command-types")
	public Object listCommandTypesForSelection(HttpServletRequest request)  throws Exception {
		return callService("list-command-types", request);
	}

	/**
	 * get bill types
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list-bill-types")
	public Object listBillTypesForSelection(HttpServletRequest request)  throws Exception {
		return callService("list-bill-types", request);
	}

	/**
	 * get bills
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list-bills")
	public Object listBillsForSelection(HttpServletRequest request)  throws Exception {
		return callService("list-bills", request);
	}

	/**
	 * get payment types
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list-payment-types")
	public Object listPaymentTypesForSelection(HttpServletRequest request)  throws Exception {
		return callService("list-payment-types", request);
	}

	/**
	 * get payment modes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list-payment-modes")
	public Object listPaymentModesForSelection(HttpServletRequest request)  throws Exception {
		return callService("list-payment-modes", request);
	}

	/**
	 * get statuses
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-statuses", method = { RequestMethod.GET })
	public Object listStatusesForSelection(HttpServletRequest request) throws Exception {
		return callService("list-statuses", request);
	}

	/**
	 * get statuses by scope
	 * @param
	 * @return
	 */
	@RequestMapping(value = "list-statuses/{scope}", method = { RequestMethod.GET })
	public Object listStatusesForSelectionByScope(@PathVariable final String scope, HttpServletRequest request) {
		final Map<String, Object> mapUrl = new HashMap<>();
		mapUrl.put("scope", scope);

		return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.SELECTION_URL_SERVICE_LIST_STATUS, mapUrl);
	}

	/**
	 * get customers
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-customers", method = { RequestMethod.GET })
	public Object listCustomersForSelection(HttpServletRequest request) throws Exception {
		return callService("list-customers", request);
	}

	/**
	 * get users
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-users", method = { RequestMethod.GET })
	public Object listUsersForSelection(HttpServletRequest request) throws Exception {
		return callService("list-users", request);
	}

	/**
	 * get resources
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-resources", method = { RequestMethod.GET })
	public Object listResourcesForSelection(HttpServletRequest request) throws Exception {
		return callService("list-resources", request);
	}

	/**
	 * get contacts
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-contacts", method = { RequestMethod.GET })
	public Object listContactsForSelection(HttpServletRequest request) throws Exception {
		return callService("list-contacts", request);
	}

	/**
	 * get types by scope
	 * @param scope
	 * @return
	 */
	@RequestMapping(value = "list-types/{scope}", method = { RequestMethod.GET })
	public Object listTypesForSelectionByScope(@PathVariable final String scope, HttpServletRequest request) {
		final Map<String, Object> mapUrl = new HashMap<>();
		mapUrl.put("scope", scope);
		return RestAPIHelper.get(getMainDomain(request) + RedSunURLs.SELECTION_URL_SERVICE_LIST_TYPES, mapUrl);
	}

	/**
	 * get providers
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-providers", method = { RequestMethod.GET })
	public Object listProvidersForSelection(HttpServletRequest request) throws Exception {
		return callService("list-providers", request);
	}

	/**
	 * get call for tenders
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-callfortenders", method = { RequestMethod.GET })
	public Object listCallForTendersForSelection(HttpServletRequest request) throws Exception {
		return callService("list-callfortenders", request);
	}

	/**
	 * get expense types
	 * @param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "list-expensetypes", method = { RequestMethod.GET })
	public Object listExpenseTypesForSelection(HttpServletRequest request) throws Exception {
		return callService("list-expensetypes", request);
	}

	@RequestMapping(value = "list-deliverymodes", method = { RequestMethod.GET })
	public Object listDeliveryModeForSelection(HttpServletRequest request) throws Exception {
		return callService("list-deliverymodes", request);
	}

}
