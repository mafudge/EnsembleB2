
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.spvsoftwareproducts.blackboard.utils.B2Context" errorPage="../error.jsp"%>
<%@page import="edu.syr.ischool.mafudge.ensemblelib.*,
				edu.syr.ischool.mafudge.ensemblelib.models.*,  
				edu.syr.ischool.mafudge.ensemblelib.collections.*,  
				edu.syr.ischool.mafudge.ensemblelib.repositories.*"  
				errorPage ="../error.jsp" %>
<%@page import="org.simpleframework.xml.*"  errorPage ="../error.jsp" %>
<%@page import="java.util.List" errorPage="../error.jsp" %>
<%@page import="java.util.Iterator"  errorPage="../error.jsp" %>
<%@taglib uri="/bbNG" prefix="bbNG" %>
<bbNG:genericPage title="${bundle['page.system.admin.title']}" entitlement="system.admin.VIEW">
  <bbNG:cssFile href="../css/EnsembleB2.css" />
<%
	String SHARED_WEB_DEST = "shared-web-destinations";
	String SERVER_NAME = "server-name";
	String API_KEY = "api-key";
	String SECRET_KEY = "secret-key";
	String DOMAIN = "domain";

	B2Context b2Context = new B2Context(request);
	String cancelUrl = "index.jsp";
	String thisUrl = "sharedweb.jsp";
	String xmlString = b2Context.getSetting(SHARED_WEB_DEST);
	String serverUrl = b2Context.getSetting(SERVER_NAME);
	String apiKey = b2Context.getSetting(API_KEY);
	String secretKey = b2Context.getSetting(SECRET_KEY);
	String domain = b2Context.getSetting(DOMAIN);
	
	EnsembleB2 eb2 = new EnsembleB2(serverUrl, apiKey, secretKey, domain);
	InstContentRepository wdr = eb2.getInstContentRepository();
	wdr.fromSerializedXmlString(xmlString);
		
	 if (request.getMethod().equalsIgnoreCase("POST")) {
	   String[] wdIdsToDelete = request.getParameterValues("ckbox");
	   //b2Context.getRequestParameterValues("ckbox","");  // Does not work!
	   String wdIds = "";
	   boolean success = false;
	   if (wdIdsToDelete!= null) 
	   {
	      for (int i = 0; i < wdIdsToDelete.length; i++) 
	      {
	    	 success = wdr.deleteWebDestinationById(wdIdsToDelete[i]);
	    	 wdIds += " [id:" + wdIdsToDelete[i] + "]";
	      }
	      xmlString = wdr.toSerializedXmlString();
	      b2Context.setSetting(SHARED_WEB_DEST, xmlString);
		  b2Context.persistSettings();
	   }
	   response.sendRedirect(thisUrl + "?inline_receipt_message=" + 
			   b2Context.getResourceString("page.system.sharedweb.success") + wdIds);
	 }
	 
	 pageContext.setAttribute("bundle", b2Context.getResourceStrings());
	 pageContext.setAttribute("cancelUrl", cancelUrl);
%>
  <bbNG:pageHeader instructions="${bundle['page.system.sharedweb.instructions']}">
    <bbNG:breadcrumbBar environment="SYS_ADMIN_PANEL" navItem="admin_plugin_manage">
      <bbNG:breadcrumb href="index.jsp" title="${bundle['plugin.name']}" />
      <bbNG:breadcrumb title="${bundle['page.system.sharedweb.title']}" />
    </bbNG:breadcrumbBar>
    <bbNG:pageTitleBar iconUrl="../images/powered.by.ensemble.gif" showTitleBar="true" title="${bundle['page.system.sharedweb.title']}"/>
    <bbNG:actionControlBar>
      <bbNG:actionButton title="${bundle['page.system.sharedweb.button.add']}" url="sharedweb-add.jsp" primary="true" />
      <bbNG:actionButton title="${bundle['page.system.sharedweb.button.back']}" url="index.jsp" primary="true" />
    </bbNG:actionControlBar>  </bbNG:pageHeader>
  <bbNG:form action="" id="id_simpleForm" name="simpleForm" method="post" onsubmit="return validateForm();">
 	<bbNG:inventoryList collection="<%=wdr.getWebDestinations()%>" objectVar="wd" className="InstContentWebDestination"> 
 		<bbNG:listActionBar> 
			<bbNG:listActionItem title="Delete" id="removeListItem" url="javascript:deleteSelectedWebDestinations()" /> 
		</bbNG:listActionBar>           
          <bbNG:listCheckboxElement name="ckbox" value="<%=wd.wdId%>" />             
          <bbNG:listElement label="Playlist ID" name="wdId"> 
            <a href="<%= eb2.getWebDestinationHref(wd.wdId) %>" target="new_<%=wd.wdId%>"><%=wd.wdId%></a> 
          </bbNG:listElement>           
          <bbNG:listElement isRowHeader="true" label="Playlist Name" name="wdName"> 
            <%=wd.wdName%>
          </bbNG:listElement> 
    </bbNG:inventoryList> 
  </bbNG:form>
<!-- JavaScript Section -->
<bbNG:jsBlock>
<script language="javascript" type="text/javascript">
function deleteSelectedWebDestinations()
{
  var submitForDelete= confirm( "${bundle['page.system.sharedweb.button.delete']}" );
  if ( submitForDelete)
  {
    document.simpleForm.action.value='delete';
    document.simpleForm.submit();
  } 
}
</script>
</bbNG:jsBlock>
</bbNG:genericPage>
