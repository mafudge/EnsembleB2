
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.spvsoftwareproducts.blackboard.utils.B2Context" errorPage="../error.jsp" %>
<%@page import="edu.syr.ischool.mafudge.ensemblelib.EnsembleB2" errorPage="../error.jsp" %>
<%@taglib uri="/bbNG" prefix="bbNG" %>
<bbNG:genericPage title="${bundle['page.system.title']}" entitlement="system.admin.VIEW">
  <bbNG:cssFile href="../css/EnsembleB2.css" />
<%
  String SERVER_NAME = "server-name";
  String API_KEY = "api-key";
  String SECRET_KEY = "secret-key";
  String DOMAIN = "domain";
  
  B2Context b2Context = new B2Context(request);
  String cancelUrl = "index.jsp";

  if (request.getMethod().equalsIgnoreCase("POST")) {
    String servername = b2Context.getRequestParameter(SERVER_NAME, "").trim();
    String apikey = b2Context.getRequestParameter(API_KEY, "").trim();
    String secretkey = b2Context.getRequestParameter(SECRET_KEY, "").trim();
    String domain = b2Context.getRequestParameter(DOMAIN, "").trim();
    
    b2Context.setSetting(SERVER_NAME, servername);
    b2Context.setSetting(API_KEY, apikey);
    b2Context.setSetting(SECRET_KEY, secretkey);
    b2Context.setSetting(DOMAIN, domain);
    b2Context.persistSettings();
    response.sendRedirect(cancelUrl + "?inline_receipt_message=" +
       b2Context.getResourceString("receipt.success"));
  }

  pageContext.setAttribute("bundle", b2Context.getResourceStrings());
  pageContext.setAttribute("cancelUrl", cancelUrl);
%>
  <bbNG:pageHeader instructions="${bundle['page.system.admin.instructions']}">
    <bbNG:breadcrumbBar environment="SYS_ADMIN_PANEL" navItem="admin_plugin_manage">
      <bbNG:breadcrumb href="index.jsp" title="${bundle['plugin.name']}" />
      <bbNG:breadcrumb title="${bundle['page.system.admin.title']}" />
    </bbNG:breadcrumbBar>
    <bbNG:pageTitleBar iconUrl="../images/powered.by.ensemble.gif" showTitleBar="true" title="${bundle['page.system.admin.title']}"/>
  </bbNG:pageHeader>
  <bbNG:form action="" id="id_simpleForm" name="simpleForm" method="post" onsubmit="return validateForm();">
  <bbNG:dataCollection markUnsavedChanges="true" showSubmitButtons="true">
    <bbNG:step hideNumber="true" id="stepOne" title="${bundle['page.system.admin.title']}" instructions="${bundle['page.system.admin.step1.instructions']}">
      <bbNG:dataElement isRequired="true" label="${bundle['page.system.admin.step1.servername.label']}">
        <bbNG:textElement id="serverName" name="<%=SERVER_NAME%>" value="<%=b2Context.getSetting(SERVER_NAME)%>" helpText="${bundle['page.system.admin.step1.servername.instructions']}" size="50" minLength="1" />
      </bbNG:dataElement>
      <bbNG:dataElement isRequired="true" label="${bundle['page.system.admin.step1.apikey.label']}">
        <bbNG:textElement id="apiKey" name="<%=API_KEY%>" value="<%=b2Context.getSetting(API_KEY)%>" helpText="${bundle['page.system.admin.step1.apikey.instructions']}" size="50" minLength="1" />
      </bbNG:dataElement>
      <bbNG:dataElement isRequired="true" label="${bundle['page.system.admin.step1.secretkey.label']}">
        <bbNG:textElement id="secretKey" name="<%=SECRET_KEY%>" value="<%=b2Context.getSetting(SECRET_KEY)%>" helpText="${bundle['page.system.admin.step1.secretkey.instructions']}" size="50" minLength="1" />
      </bbNG:dataElement>		
      <bbNG:dataElement isRequired="false" label="${bundle['page.system.admin.step1.domain.label']}">
        <bbNG:textElement id="domain" name="<%=DOMAIN%>" value="<%=b2Context.getSetting(DOMAIN)%>" helpText="${bundle['page.system.admin.step1.domain.instructions']}" size="50" minLength="0" />
      </bbNG:dataElement>		
    </bbNG:step>
    <bbNG:stepSubmit hideNumber="true" showCancelButton="true"  cancelUrl="${cancelUrl}"/>
  </bbNG:dataCollection>
  
  </bbNG:form>
  </bbNG:genericPage>
 