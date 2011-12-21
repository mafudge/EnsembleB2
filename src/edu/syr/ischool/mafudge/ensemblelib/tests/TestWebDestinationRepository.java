package edu.syr.ischool.mafudge.ensemblelib.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.syr.ischool.mafudge.ensemblelib.models.InstContentWebDestination;
import edu.syr.ischool.mafudge.ensemblelib.repositories.InstContentRepository;

public class TestWebDestinationRepository {

	InstContentRepository wdr;

	List<InstContentWebDestination> sampleData()
	{
		List<InstContentWebDestination> m_list = new ArrayList<InstContentWebDestination>();
		m_list.add(new InstContentWebDestination("kfuS9kxl70mW7NBM9IPJsA","BIO 101"));
		m_list.add(new InstContentWebDestination("hbGv8P7Y9U-hTKml7t0wIA","Campus TV"));
		m_list.add(new InstContentWebDestination("kxNqGEBxbU-S3JjD_kB-Qw","HIS 222"));			
		return  m_list;
	}

	@Test
	public void testWebDestinationRepository() {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		assertTrue(wdr != null);
	}

	@Test
	public void testGetWebDestinations() {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		assertEquals(sampleData().size(),wdr.getWebDestinations().size());
	}

	@Test
	public void testSetWebDestinations() {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		assertEquals(sampleData().size(),wdr.getWebDestinations().size());
	}

	@Test
	public void testAddWebDestination() {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		// this should get added
		wdr.addWebDestination(new InstContentWebDestination("key1","value1"));
		assertEquals(sampleData().size()+1,wdr.getWebDestinations().size());		
		// second add should not go.
		wdr.addWebDestination(new InstContentWebDestination("key1","value1"));
		assertEquals(sampleData().size()+1,wdr.getWebDestinations().size());		
	}

	@Test
	public void testDeleteWebDestinationById() throws Exception {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		wdr.addWebDestination(new InstContentWebDestination("key1","value1"));
		assertEquals(sampleData().size()+1,wdr.getWebDestinations().size());		
		// should be able to delete what's added.
		wdr.deleteWebDestinationById("key1");
		assertEquals(sampleData().size(),wdr.getWebDestinations().size());		
	}

	@Test
	public void testExistsWebDestinationById() {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		wdr.addWebDestination(new InstContentWebDestination("key1","value1"));
		assertEquals(sampleData().size()+1,wdr.getWebDestinations().size());		
		// should exist - was just added.
		assertEquals(true,wdr.existsWebDestinationById("key1"));
		// should not exist
		assertEquals(false,wdr.existsWebDestinationById("key2"));		
	}

	@Test
	public void testToSerializedXmlString() throws Exception {
		int expectedLen = 442;
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		String xmlString = wdr.toSerializedXmlString();
		assertEquals(expectedLen, xmlString.length());
	}

	@Test
	public void testSerializedFromXmlString() throws Exception {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		String expectedString = wdr.toSerializedXmlString();
		wdr.fromSerializedXmlString(expectedString);
		String actualString = wdr.toSerializedXmlString();
		assertEquals(expectedString, actualString);
	}

	
	@Test
	public void testGenericTestRunner() throws Exception {
		InstContentRepository wdr = new InstContentRepository();
		wdr.setWebDestinations(sampleData());
		int expected = wdr.getWebDestinations().size();
		wdr.fromSerializedXmlString(wdr.toSerializedXmlString());
		wdr.addWebDestination(new InstContentWebDestination("xxxxx","data"));
		wdr.fromSerializedXmlString(wdr.toSerializedXmlString());
		wdr.deleteWebDestinationById("xxxxx");
		wdr.fromSerializedXmlString(wdr.toSerializedXmlString());		
		assertEquals(expected , wdr.getWebDestinations().size());
	}
}
