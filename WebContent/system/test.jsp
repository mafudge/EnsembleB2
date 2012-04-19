<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="edu.syr.ischool.mafudge.ensemblelib.EnsembleB2" errorPage="../error.jsp"%>
<%
	String serverUrl = request.getParameter("url");
	String apiKey = request.getParameter("api");
	String secretKey = request.getParameter("sec");
	EnsembleB2 eb2 = new  EnsembleB2( serverUrl,  apiKey,  secretKey, ""); 

	try	
	{
		String result = eb2.TestApiWithResult();
		out.println(result);
	}
	catch (Exception e)
	{
		out.println(e.getMessage());
	}
	
%>