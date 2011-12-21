<%@ page import="blackboard.platform.plugin.PlugInUtil" errorPage="../error.jsp"%>
<%@ page import="com.spvsoftwareproducts.blackboard.utils.B2Context" errorPage="../error.jsp"%>
<%@ page import="edu.syr.ischool.mafudge.ensemblelib.*"  errorPage ="../error.jsp" %>

<%
	String SERVER_NAME = "server-name";
	String API_KEY = "api-key";
	String SECRET_KEY = "secret-key";
	B2Context b2Context = new B2Context(request);
	EnsembleB2 eb2 = new EnsembleB2(b2Context.getSetting(SERVER_NAME),b2Context.getSetting(API_KEY),  b2Context.getSetting(SECRET_KEY));
	String WYSIWYG_WEBAPP = "/webapps/wysiwyg";
	String webDestId = request.getParameter("webDestination");
	String title = request.getParameter("title");
	String courseId = request.getParameter("course_id");
	String contentId =  request.getParameter("content_id");
	String returnUrl = request.getParameter("http_ref");
	String embedHtml = eb2.getWebDestinationHtml(webDestId);	

	ContentCreator cc = new ContentCreator();
	cc.createContent(title, embedHtml,courseId, contentId);

    response.sendRedirect(returnUrl + "?inline_receipt_message=Content%20Added.");
%>
