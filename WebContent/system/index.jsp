<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.spvsoftwareproducts.blackboard.utils.B2Context" errorPage="../error.jsp"%>
<%@taglib uri="/bbNG" prefix="bbNG" %>
<bbNG:genericPage title="${bundle['page.system.index.title']}" entitlement="system.admin.VIEW">
<%
	String SERVER_NAME = "server-name";
	String API_KEY = "api-key";
	String SECRET_KEY = "secret-key";
	String DOMAIN = "domain";

  B2Context b2Context = new B2Context(request);
  String cancelUrl = b2Context.getNavigationItem("admin_plugin_manage").getHref();

  pageContext.setAttribute("bundle", b2Context.getResourceStrings());
  pageContext.setAttribute("cancelUrl", cancelUrl);
  String jQueryPath = b2Context.getPath() + "js/jquery.min.js";

%>
  <bbNG:pageHeader instructions="${bundle['page.system.index.instructions']}">
    <bbNG:breadcrumbBar environment="SYS_ADMIN_PANEL" navItem="admin_plugin_manage">
      <bbNG:breadcrumb title="${bundle['plugin.name']}" />
    </bbNG:breadcrumbBar>
    <bbNG:pageTitleBar iconUrl="../images/powered.by.ensemble.gif" showTitleBar="true" title="${bundle['page.system.index.title']}"/>
    <bbNG:actionControlBar>
      <bbNG:actionButton title="${bundle['page.system.index.button.admin']}" url="admin.jsp" primary="true" />
      <bbNG:actionButton title="${bundle['page.system.index.button.shared']}" url="sharedweb.jsp" primary="true" />
    </bbNG:actionControlBar>
  </bbNG:pageHeader>
  <bbNG:jsFile href="<%=jQueryPath %>"/> <!--  Note: does not support absolute Url's -->  
  <div>
  <h3 class="steptitle">Current Building Block Configuration:</h3>
<bbNG:contentList>
	<bbNG:contentListItem title="${bundle['page.system.admin.step1.servername.label']}"><code><%=b2Context.getSetting(SERVER_NAME)%></code></bbNG:contentListItem>
	<bbNG:contentListItem title="${bundle['page.system.admin.step1.apikey.label']}"><code><%=b2Context.getSetting(API_KEY)%></code></bbNG:contentListItem>
	<bbNG:contentListItem title="${bundle['page.system.admin.step1.secretkey.label']}"><code><%=b2Context.getSetting(SECRET_KEY)%></code></bbNG:contentListItem>
	<bbNG:contentListItem title="${bundle['page.system.admin.step1.domain.label']}"><code><%=b2Context.getSetting(DOMAIN)%></code></bbNG:contentListItem>
	<bbNG:contentListItem title="Test Your Configuration">
		<button class="genericButton" id="testConfiguration">Test</button>
 		<span id="testStatus"></span>
	</bbNG:contentListItem>
</bbNG:contentList>
  
	<bbNG:jsBlock>
   	<script type="text/javascript">
    	 jQuery.noConflict();
    	      
     	// Use jQuery via jQuery(...)
     	jQuery(document).ready(function(){
     		     		
			jQuery("#testStatus").ajaxError( function(xhr, ajaxOptions, thrownError) { 
				//alert ("Error:" + xhr.responseText);
				jQuery("#testStatus").html("Error: " + thrownError);
			});
			
     		jQuery.ajaxSetup ({  
		         cache: false  
		     });  
	    	 var load_url = "test.jsp?url=<%=b2Context.getSetting(SERVER_NAME)%>&api=<%=b2Context.getSetting(API_KEY)%>&sec=<%=b2Context.getSetting(SECRET_KEY)%>";
	    	 var ajax_load = "Testing... "; 

       		jQuery("#testConfiguration").click(function() {
       			jQuery("#testStatus").html('');
    			jQuery("#testStatus").html(ajax_load).load(load_url);    			
    			return true;
	     	});
     	});
   </script>
   </bbNG:jsBlock>

  <bbNG:okButton url="${cancelUrl}" />
  </div>
</bbNG:genericPage>
