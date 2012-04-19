<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
      import="java.io.PrintWriter, com.spvsoftwareproducts.blackboard.utils.B2Context"
      isErrorPage="true" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%
  B2Context b2Context = new B2Context(request);
  pageContext.setAttribute("bundle", b2Context.getResourceStrings());

	String strException = exception.getMessage();
	String jQueryPath = b2Context.getPath() + "js/jquery.min.js";
	String strError = strException;
%>

<bbNG:genericPage>
  <bbNG:pageHeader>
    <bbNG:pageTitleBar showTitleBar="true" title="${bundle['plugin.name']}: ${bundle['page.error.title']}" titleColor="#990000"/>
  </bbNG:pageHeader>
    <bbNG:jsFile href="<%=jQueryPath %>"/> <!--  Note: does not support absolute Url's -->  

<bbNG:breadcrumbBar/>
	<bbNG:jsBlock>
   	<script type="text/javascript">
    	 jQuery.noConflict();
    	      
     	// Use jQuery via jQuery(...)
     	jQuery(document).ready(function(){
			jQuery("#errorDetails").hide();
			
			jQuery("#toggleErrorDetails").click(function () {

				jQuery("#errorDetails").toggle(); 

				if (jQuery("#toggleErrorDetails").html() === "Show Error Details") {
					jQuery("#toggleErrorDetails").html("Hide Error Details");
				} else {
					jQuery("#toggleErrorDetails").html("Show Error Details");
				}
			}); 
     	});     	
   </script>
   </bbNG:jsBlock>

	<h3>${bundle['page.error.introduction']}</h3>
	<p><b>Error Message:</b> <%=strError %></p>
	<h3></h3>
	<p><button id="toggleErrorDetails">Show Error Details</button></p>
	<div id="errorDetails">
		<h3>Error Details</h3>
		<p><b>Java Exception: </b> <%=strException%></p>
		<p><b>Stack Trace: </b> <br/>
		<pre>
		<%
			// now display a stack trace of the exception
		  PrintWriter pw = new PrintWriter( out );
		  exception.printStackTrace( pw );
		%>
		</pre>
	</div>
</bbNG:genericPage>