package edu.syr.ischool.mafudge.ensemblelib.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.syr.ischool.mafudge.ensemblelib.SimpleHttpClient;
import edu.syr.ischool.mafudge.ensemblelib.models.Video;
import edu.syr.ischool.mafudge.ensemblelib.repositories.VideoRepository;

public class TestVideoRepository {

	VideoRepository vr;
	boolean samplesFromEnsemble = true;
	
	List<Video> sampleData()
	{
		List<Video> sample = null;
		if (samplesFromEnsemble) 
		{
			try {
				sample =  sampleDataFromEnsemble();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			sample =  sampleDataFromFakes();
		}
		return sample;
	}

	List<Video> sampleDataFromFakes()
	{
		List<Video> vlist = new ArrayList<Video>();
		vlist.add(sampleVideo("sample1"));
		vlist.add(sampleVideo("sample2"));
		return vlist;
		// http://demo.ensemblevideo.com/app/simpleAPI/video/list.xml/G0bbuVN4HEGJKzr4QIBMSw
	}
	
	
	List<Video> sampleDataFromEnsemble() throws Exception
	{
		SimpleHttpClient c = new SimpleHttpClient();
		String testUrl = "http://demo.ensemblevideo.com/app/simpleAPI/video/list.xml/G0bbuVN4HEGJKzr4QIBMSw";
		String result = c.get(testUrl);
		VideoRepository v = new VideoRepository();
		v.fromRawXmlString(result);
		v.addVideo(sampleVideo("sample1"));
		return v.getVideos();
	}
	Video sampleVideo(String videoId)
	{
		Video v = new Video();
		v.videoID =videoId;
		return v;
	}
		
	@Test
	public void testVideoRepository() throws Exception {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleData());
		assertTrue(vr != null);
	}

	@Test
	public void testGetVideos() {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleData());
		assertEquals(sampleData().size(),vr.getVideos().size());
	}

	@Test
	public void testSetVideos() {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleData());
		assertEquals(sampleData().size(),vr.getVideos().size());
	}

	@Test
	public void testAddVideo() {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleData());
		// this should get added
		vr.addVideo(sampleVideo("key1"));
		assertEquals(sampleData().size()+1,vr.getVideos().size());		
		// second add should not go.
		vr.addVideo(sampleVideo("key1"));
		assertEquals(sampleData().size()+1,vr.getVideos().size());		
	}

	@Test
	public void testAddVideos() {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleData());
		// this should get added
		List<Video> vl = new ArrayList<Video>();
		vl.add(sampleVideo("key1"));
		vl.add(sampleVideo("key2"));
		vl.add(sampleVideo("sample1"));
		
		int count = vr.addVideos(vl);
		assertEquals(vr.getVideos().size()+2,vr.getVideos().size()+count);		
	}

	@Test
	public void testDeleteVideoById() {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleData());
		vr.addVideo(sampleVideo("key1"));
		assertEquals(sampleData().size()+1,vr.getVideos().size());		
		// should be able to delete what's added.
		vr.deleteVideoById("key1");
		assertEquals(sampleData().size(),vr.getVideos().size());		
	}

	@Test
	public void testExistsVideoById() {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleData());
		vr.addVideo(sampleVideo("key1"));
		assertEquals(sampleData().size()+1,vr.getVideos().size());		
		// should exist - was just added.
		assertEquals(true,vr.existsVideoById("key1"));
		// should not exist
		assertEquals(false,vr.existsVideoById("key2"));		
	}

	@Test
	public void testToSerializedXmlString() throws Exception {
		int expectedLen = 219;
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleDataFromFakes());
		String xmlString = vr.toSerializedXmlString();
		assertEquals(expectedLen, xmlString.length());
	}

	@Test
	public void testFromSerializedXmlString() throws Exception {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleDataFromFakes());
		String expectedString = vr.toSerializedXmlString();
		vr.fromSerializedXmlString(expectedString);
		String actualString = vr.toSerializedXmlString();
		assertEquals(expectedString, actualString);			
	}

	@Test
	public void testfromRawXmlString() throws Exception {
		VideoRepository vr = new VideoRepository();
		vr.setVideos(sampleDataFromEnsemble());
		String expectedString = vr.toSerializedXmlString();
		vr.fromSerializedXmlString(expectedString);
		String actualString = vr.toSerializedXmlString();
		assertEquals(expectedString, actualString);
	}
	
}
