<%@ page import="blackboard.platform.plugin.PlugInUtil" errorPage="../error.jsp"%>
<%@ page import="com.spvsoftwareproducts.blackboard.utils.B2Context" errorPage="../error.jsp"%>
<%@ page import="edu.syr.ischool.mafudge.ensemblelib.*"  errorPage ="../error.jsp" %>
<%@ page import="java.util.*,
				java.net.*" 
		errorPage="../error.jsp"%>
<%
	String SERVER_NAME = "server-name";
	String API_KEY = "api-key";
	String SECRET_KEY = "secret-key";
    String DOMAIN = "domain";
	B2Context b2Context = new B2Context(request);
	EnsembleB2 eb2 = new EnsembleB2(b2Context.getSetting(SERVER_NAME),b2Context.getSetting(API_KEY), b2Context.getSetting(SECRET_KEY), b2Context.getSetting(DOMAIN));
	String WYSIWYG_WEBAPP = "/webapps/wysiwyg";
	String videoId = request.getParameter("video_id");
	String courseId = request.getParameter("course_id");
	String contentId =  request.getParameter("content_id");
	String title = URLDecoder.decode(request.getParameter("title"),"UTF-8");
	String returnUrl = URLDecoder.decode(request.getParameter("http_ref"),"UTF-8");
	
	String thumbnail = "<img src=\"" + b2Context.getPath() + "images/thumbnail.jpg\" />";
	String embedHtml = eb2.getContentHtml(videoId,thumbnail);
	
	// Process the vtbe
    request.setAttribute( "embedHtml", embedHtml );
    String embedUrl = PlugInUtil.getInsertToVtbePostUrl().replace( WYSIWYG_WEBAPP, "" );
    RequestDispatcher rd = getServletContext().getContext( WYSIWYG_WEBAPP ).getRequestDispatcher( embedUrl );
    rd.forward( request, response );
    
%>