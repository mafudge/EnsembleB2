package edu.syr.ischool.mafudge.ensemblelib;


import java.io.*;  //legacy
import java.net.*; //legacy

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;


// This is a wrapper on the org.apache.commons.httpclient.*;
public class SimpleHttpClient {

	public SimpleHttpClient() { }
	
	public boolean validUrl(String urlString)
	{
		boolean result = true;
		try {
			URL u = new URL(urlString);
			result = (u.toString().length() > 0);
		} catch (MalformedURLException e) {
			result = false;
		}
		return result; 
	}
		
	public String get(String urlString) throws Exception 
	{
		URL u;
		InputStream is = null;
		BufferedReader dis;
		StringWriter sw;	
		String buff;

		u = new URL(urlString);
		is = u.openStream();         
		dis = new BufferedReader(new InputStreamReader(is)); 
		sw = new StringWriter();
		
		 while ((buff = dis.readLine()) != null) 
		 {
			 sw.append(buff);
		 }
        is.close();
        return sw.toString();
     }

	public String webGet(String urlString) throws Exception
	{
		String result = "";
		String buff = "";
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(urlString);
     	HttpResponse response = httpclient.execute(httpget);

		 // Get the response status
		result = response.getStatusLine().toString();
		int statusCode = response.getStatusLine().getStatusCode();
		
		 // Get hold of the response entity
		 HttpEntity entity = response.getEntity();
		 
		 if (entity != null && statusCode == 200) {
			 result = "";
		     InputStream instream = entity.getContent();
		     try {

		         BufferedReader reader = new BufferedReader(
		                 new InputStreamReader(instream));
		         // do something useful with the response
		         do
		         {
		        	 buff = reader.readLine();
		        	 if (buff != null ) result += buff;
		         } while (buff != null);

		     } catch (IOException ex) {
		         // In case of an IOException the connection will be released
		         // back to the connection manager automatically
		         throw ex;
		     } catch (RuntimeException ex) {
		         // In case of an unexpected exception you may want to abort
		         // the HTTP request in order to shut down the underlying
		         // connection and release it back to the connection manager.
		         httpget.abort();
		         throw ex;
		     } finally {
		         // Closing the input stream will trigger connection release
		         instream.close();
		     }
		     // When HttpClient instance is no longer needed,
		     // shut down the connection manager to ensure
		     // immediate deallocation of all system resources
		     httpclient.getConnectionManager().shutdown();
		 } else {
			 throw new RuntimeException(result);
		 }
			 
		return result;
	}
	
} // end of class definition


