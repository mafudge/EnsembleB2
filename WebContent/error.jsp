<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
      import="java.io.PrintWriter, com.spvsoftwareproducts.blackboard.utils.B2Context"
      isErrorPage="true" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%
  B2Context b2Context = new B2Context(request);
  pageContext.setAttribute("bundle", b2Context.getResourceStrings());

	String strException = exception.getMessage();
%>

<bbNG:genericPage>
  <bbNG:pageHeader>
    <bbNG:pageTitleBar showTitleBar="true" title="${bundle['plugin.name']}: ${bundle['page.error.title']}"/>
  </bbNG:pageHeader>
<bbNG:breadcrumbBar/>
<%=strException%>
<p>
${bundle['page.error.introduction']}
</p>
<pre>
<%
	// now display a stack trace of the exception
  PrintWriter pw = new PrintWriter( out );
  exception.printStackTrace( pw );
%>
</pre>

</bbNG:genericPage>