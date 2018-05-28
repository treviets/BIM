package com.redsun.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Bill Controller
 */
public class BaseController {
	public String getMainDomain(HttpServletRequest request) {
		if (request != null) {
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();

			StringBuffer mainDomain = new StringBuffer(url.substring(0, url.indexOf(uri)));
			return mainDomain.toString();
		}
		return null;
	}
}
