package edu.syr.ischool.mafudge.ensemblelib.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.syr.ischool.mafudge.ensemblelib.SimpleHttpClient;


public class TestSimpleHttpClient {

	@Test
	public void testSimpleHttpClient() {
		SimpleHttpClient c = new SimpleHttpClient();
		assertTrue(c != null);
	}

	@Test
	public void testValidUrl() {
		SimpleHttpClient c = new SimpleHttpClient();
		String testUrl = "http://demo.ensemblevideo.com/app/simpleAPI/video/list.xml/G0bbuVN4HEGJKzr4QIBMSw";
		assertTrue(c.validUrl(testUrl));
	}

	@Test
	public void testGet() throws Exception {
		SimpleHttpClient c = new SimpleHttpClient();
		String testUrl = "http://demo.ensemblevideo.com/app/simpleAPI/video/list.xml/G0bbuVN4HEGJKzr4QIBMSw";
		String result = c.get(testUrl);
		assertTrue(result.length() > 0);
	}
	
	@Test
	public void testWebGet() throws Exception {
		SimpleHttpClient c = new SimpleHttpClient();
		String testUrl = "http://ed1.ischool.syr.edu/ensembletrunk/SecureApi/SecureApi.svc/About";
		String result = c.webGet(testUrl);
		assertTrue(result.length() > 0);
	}
	
}
