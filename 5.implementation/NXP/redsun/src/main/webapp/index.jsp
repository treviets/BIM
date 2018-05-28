<% 
	String contextPath = getServletContext().getContextPath();
	response.sendRedirect(contextPath + "/login");
%>