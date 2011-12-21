<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="blackboard.platform.session.*,
				java.util.*,
				java.net.*,
				blackboard.data.user.*,
				blackboard.persist.*,
				blackboard.data.course.*,
				blackboard.persist.course.*" 
		errorPage="../error.jsp"%>
<%@page import="com.spvsoftwareproducts.blackboard.utils.B2Context" errorPage="../error.jsp"%>
<%@page import="edu.syr.ischool.mafudge.ensemblelib.*,
				edu.syr.ischool.mafudge.ensemblelib.models.*,  
				edu.syr.ischool.mafudge.ensemblelib.collections.*,  
				edu.syr.ischool.mafudge.ensemblelib.repositories.*"  
				errorPage ="../error.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Preview</title>
</head>
<body>
<%
	String SHARED_WEB_DEST = "shared-web-destinations";
	String SERVER_NAME = "server-name";
	String API_KEY = "api-key";
	String SECRET_KEY = "secret-key";
	String MY_MEDIA = "myMedia";
	String SHARED_MEDIA= "sharedMedia";
	String INST_CONTENT= "instContent";
	B2Context b2Context = new B2Context(request);
	EnsembleB2 eb2 = new EnsembleB2(b2Context.getSetting(SERVER_NAME),b2Context.getSetting(API_KEY),  b2Context.getSetting(SECRET_KEY));
	pageContext.setAttribute("bundle", b2Context.getResourceStrings());
	String contentId = request.getParameter("contentId");
	String content = eb2.getContentHtml(contentId);
	out.println(content);
%>
</body>
</html>