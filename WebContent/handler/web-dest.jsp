<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="blackboard.platform.session.*,
				java.util.*,
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
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib uri="/bbData" prefix="bbData"%> 
<%@ page isErrorPage="true" %>

<bbData:context id="ctx">
<bbNG:learningSystemPage ctxId="ctx2">
<bbNG:cssFile href="../css/EnsembleB2.css"/>

<%
	String SERVER_NAME = "server-name";
	String API_KEY = "api-key";
	String SECRET_KEY = "secret-key";
    String DOMAIN = "domain";
	
	B2Context b2Context = new B2Context(request);
	EnsembleB2 eb2 = new EnsembleB2(b2Context.getSetting(SERVER_NAME),b2Context.getSetting(API_KEY), b2Context.getSetting(SECRET_KEY), b2Context.getSetting(DOMAIN));
	String courseId = ctx.getCourseId().toString();  
	String contentId = ctx.getContentId().toString();
	String ref = request.getHeader("referer");
	String userName = ctx.getUser().getUserName();
	pageContext.setAttribute("bundle", b2Context.getResourceStrings());
	List<WebDestination> wdList = eb2.getWebDestinations(userName);
%>
<bbNG:pageHeader instructions="${bundle['page.handler.web-dest.instructions']}">
    <bbNG:pageTitleBar iconUrl="../images/powered.by.ensemble.gif" showTitleBar="true" title="${bundle['page.handler.web-dest.title']}"/>
</bbNG:pageHeader>
<% if(wdList.size() > 0) { %>
<bbNG:form action="web-dest-process.jsp" method="POST" id="id_searchForm" name="searchForm">
<bbNG:dataCollection markUnsavedChanges="true" showSubmitButtons="true">
<bbNG:step title="${bundle['page.handler.web-dest.step1.title']}" instructions="${bundle['page.handler.web-dest..step1.instructions']}">
<h3>Ensemble Playlists for <%=userName %>:</h3>
		<input type="hidden" value="<%=courseId %>" name="course_id" /> 
		<input type="hidden" value="<%=contentId %>" name="content_id" />
		<input type="hidden" value="<%=ref %>" name="http_ref" />
      <bbNG:dataElement isRequired="true" label="${bundle['page.handler.web-dest.step1.title.label']}">
        <bbNG:textElement name="title" value=""  size="50" minLength="1" />
      </bbNG:dataElement>
      <bbNG:dataElement isRequired="true" label="${bundle['page.handler.web-dest.step1.webdestination.label']}">
		<bbNG:selectElement  name="webDestination" id="id_webDestination" size="10">
			<% for (WebDestination w : wdList) { 
				String optionValue = w.libraryName + " : " + w.webDestinationName;
			%>
			<bbNG:selectOptionElement value="<%=w.webDestinationID %>" optionLabel="<%=optionValue %>" />		
		    <% } %>
		</bbNG:selectElement>
      </bbNG:dataElement>
</bbNG:step>
<bbNG:stepSubmit>
<bbNG:stepSubmitButton label="Submit"/>
</bbNG:stepSubmit>
</bbNG:dataCollection>
</bbNG:form>
<% } else {%>
<h3>There are no Ensemble Web Destinations enabled for <%=userName %>'s library.</h3>
  <bbNG:okButton url="${ref}" />
<% } %>
</bbNG:learningSystemPage>
</bbData:context>