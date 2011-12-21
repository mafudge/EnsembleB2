package edu.syr.ischool.mafudge.ensemblelib.tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.crypto.*;
import javax.crypto.spec.*;

import org.joda.time.*;
import org.joda.time.format.*;
import org.junit.Test;

import edu.syr.ischool.mafudge.ensemblelib.HMacMD5Encoder;
import edu.syr.ischool.mafudge.ensemblelib.SimpleHttpClient;

public class TestEnsembleSecureApi {

	String baseUrl = "http://ed1.ischool.syr.edu/ensembletrunk/SecureApi/SecureApi.svc/";
	String testApiKey = "aef4147ab0fb7f00531bfbcf1d0bc34e";
	String testSecretKey = "15a2cf90610988ebb716002c6e912738";	
	
	@Test
	public void testUnauthenticatedAboutRequestPass() throws Exception {
		SimpleHttpClient c = new SimpleHttpClient();
		String testUrl = this.baseUrl + "About";
		String result = c.webGet(testUrl);
		assertTrue(result.length()> 0);
	}

	@Test
	public void test404Error() throws Exception {
		String expected = "404 Not Found";
		SimpleHttpClient c = new SimpleHttpClient();
		String testUrl = this.baseUrl + "thisis404/Test?ts=";
		String result = c.webGet(testUrl);
		assertTrue(result.indexOf(expected)>= 0);		
	}

	@Test
	public void testAuthenticatedTestRequestFailWithExpiredTimeStamp() throws Exception {
		String expected = "401 Timestamp outside acceptable range";
		SimpleHttpClient c = new SimpleHttpClient();
		String timeStamp = "20110531093000";
		String testUrl = this.baseUrl + "api/" + testApiKey + "/Test?ts=" + timeStamp + "&hmac=";
		String hmac = "123456789";
		String result = c.webGet(testUrl + hmac);
		assertTrue(result.indexOf(expected)>= 0);		
	}
	
	@Test
	public void testAuthenticatedTestRequestFailWithInvalidTimeStamp() throws Exception {
		String expected = "401 Invalid timestamp format";
		SimpleHttpClient c = new SimpleHttpClient();
		String timeStamp = "mikefudge";
		String testUrl = this.baseUrl + "api/" + testApiKey + "/Test?ts=" + timeStamp + "&hmac=";
		String hmac = "123456789";
		String result = c.webGet(testUrl + hmac);
		assertTrue(result.indexOf(expected)>= 0);		
	}
	
	@Test
	public void testAuthenticatedTestRequestFailWithInvalidHMac() throws Exception {
		String expected = "401 Invalid HMac CheckSum";
		SimpleHttpClient c = new SimpleHttpClient();
		DateTime nowUtc = (new DateTime()).withZone(DateTimeZone.UTC);
		String timeStamp = nowUtc.toString(DateTimeFormat.forPattern("yyyyMMddHHmmss"));
		String testUrl = this.baseUrl + "api/" + testApiKey + "/Test?ts=" + timeStamp + "&hmac=";
		String hmac = "123456789";
		String result = c.webGet(testUrl + hmac);
		assertTrue(result.indexOf(expected)>= 0);		
	}

	//TODO
	@Test
	public void testAuthenticatedTestRequestFailWithInvalidApiKey() throws Exception {
		String expected = "401 Invalid API Key";
		SimpleHttpClient c = new SimpleHttpClient();
		DateTime nowUtc = (new DateTime()).withZone(DateTimeZone.UTC);
		String timeStamp = nowUtc.toString(DateTimeFormat.forPattern("yyyyMMddHHmmss"));
		String badApiKey = "1234567890abcdef";
		String testUrl = this.baseUrl + "api/" + badApiKey + "/Test?ts=" + timeStamp + "&hmac=";
		String hmac = HMacMD5Encoder.Encode(this.testSecretKey, testUrl.toLowerCase());
		String result = c.webGet(testUrl + hmac);
		assertTrue(result.indexOf(expected)>= 0);		
	}

	@Test
	public void testAuthenticatedTestRequestPass() throws Exception {
		String result;
		String expected = "Success";
		SimpleHttpClient c = new SimpleHttpClient();
		DateTime nowUtc = (new DateTime()).withZone(DateTimeZone.UTC);
		String timeStamp = nowUtc.toString(DateTimeFormat.forPattern("yyyyMMddHHmmss"));
		String testUrl = this.baseUrl + "api/" + testApiKey + "/Test?ts=" + timeStamp + "&hmac=";
		String hmac = HMacMD5Encoder.Encode(this.testSecretKey, testUrl.toLowerCase());
		result = c.webGet(testUrl + hmac);
		result = result.replaceAll("\\<.*?\\>", "");
		assertTrue(result.indexOf(expected)>= 0);		
	}
}
