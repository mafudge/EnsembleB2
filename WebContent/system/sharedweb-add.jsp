
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.spvsoftwareproducts.blackboard.utils.B2Context" errorPage="../error.jsp"%>
<%@page import="edu.syr.ischool.mafudge.ensemblelib.*,
				edu.syr.ischool.mafudge.ensemblelib.models.*,  
				edu.syr.ischool.mafudge.ensemblelib.collections.*,  
				edu.syr.ischool.mafudge.ensemblelib.repositories.*"  
				errorPage ="../error.jsp" %>
<%@taglib uri="/bbNG" prefix="bbNG" %>
<bbNG:genericPage title="${bundle['page.system.sharedweb.add.title']}" entitlement="system.admin.VIEW">
  <bbNG:cssFile href="../css/EnsembleB2.css" />
<%
	String WEB_DEST_ID = "web-destination-id";
	String FRIENDLY_NAME = "friendly-name";
	String SHARED_WEB_DEST = "shared-web-destinations";	
	B2Context b2Context = new B2Context(request);
	String cancelUrl = "sharedweb.jsp";
	String thisUrl = "sharedweb-add.jsp";


	// on post, write form data to collection
	if (request.getMethod().equalsIgnoreCase("POST")) {
		String xmlString = b2Context.getSetting(SHARED_WEB_DEST);
		InstContentRepository wdr = new InstContentRepository();
		wdr.fromSerializedXmlString(xmlString);
	    String wdId = b2Context.getRequestParameter(WEB_DEST_ID, "").trim();
	    String wdName = b2Context.getRequestParameter(FRIENDLY_NAME, "").trim();
		wdr.addWebDestination(new InstContentWebDestination(wdId, wdName));
		xmlString = wdr.toSerializedXmlString();
    	b2Context.setSetting(SHARED_WEB_DEST, xmlString);
	    b2Context.persistSettings();
    	
	    response.sendRedirect(cancelUrl + "?inline_receipt_message=" +
		b2Context.getResourceString("page.system.sharedweb.add.success"));
 	} // end if

	pageContext.setAttribute("bundle", b2Context.getResourceStrings());
	pageContext.setAttribute("cancelUrl", cancelUrl);
%>
  <bbNG:pageHeader instructions="${bundle['page.system.admin.instructions']}">
    <bbNG:breadcrumbBar environment="SYS_ADMIN_PANEL" navItem="admin_plugin_manage">
      <bbNG:breadcrumb href="index.jsp" title="${bundle['plugin.name']}" />
      <bbNG:breadcrumb title="${bundle['page.system.sharedweb.add.title']}" />
    </bbNG:breadcrumbBar>
    <bbNG:pageTitleBar iconUrl="../images/powered.by.ensemble.gif" showTitleBar="true" title="${bundle['page.system.sharedweb.add.title']}"/>
  </bbNG:pageHeader>
  <bbNG:form action="" id="id_simpleForm" name="simpleForm" method="post" onsubmit="return validateForm();">
  <bbNG:dataCollection markUnsavedChanges="true" showSubmitButtons="true">
    <bbNG:step hideNumber="true" id="stepOne" title="${bundle['page.system.sharedweb.add.step1.title']}" instructions="${bundle['page.system.sharedweb.add.step1.instructions']}">
      <bbNG:dataElement isRequired="true" label="${bundle['page.system.sharedweb.add.step1.webdestinationid.label']}">
        <bbNG:textElement name="<%=WEB_DEST_ID%>" value="" helpText="${bundle['page.system.sharedweb.add.step1.webdestinationid.instructions']}" size="50" minLength="1" />
      </bbNG:dataElement>
      <bbNG:dataElement isRequired="true" label="${bundle['page.system.sharedweb.add.step1.webdestinationname.label']}">
        <bbNG:textElement name="<%=FRIENDLY_NAME%>" value="" helpText="${bundle['page.system.sharedweb.add.step1.webdestinationname.instructions']}" size="50" minLength="1" />
      </bbNG:dataElement>
    </bbNG:step>
    <bbNG:stepSubmit hideNumber="true" showCancelButton="true"  cancelUrl="${cancelUrl}"/>
  </bbNG:dataCollection>
  </bbNG:form>
  </bbNG:genericPage>
