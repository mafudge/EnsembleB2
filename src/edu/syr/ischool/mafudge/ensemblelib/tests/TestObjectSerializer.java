package edu.syr.ischool.mafudge.ensemblelib.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.syr.ischool.mafudge.ensemblelib.ObjectSerializer;
import edu.syr.ischool.mafudge.ensemblelib.collections.InstContentCollection;
import edu.syr.ischool.mafudge.ensemblelib.models.InstContentWebDestination;

public class TestObjectSerializer {
	
	
	List<InstContentWebDestination> sampleData()
	{
		List<InstContentWebDestination> m_list = new ArrayList<InstContentWebDestination>();
		m_list.add(new InstContentWebDestination("kfuS9kxl70mW7NBM9IPJsA","BIO 101"));
		m_list.add(new InstContentWebDestination("hbGv8P7Y9U-hTKml7t0wIA","Campus TV"));
		m_list.add(new InstContentWebDestination("kxNqGEBxbU-S3JjD_kB-Qw","HIS 222"));			
		return  m_list;
	}

	@Test
	public void testToXmlString() {
		int expectedLen = 442;
		InstContentCollection c = new InstContentCollection();
		c.setCollection(sampleData());
		ObjectSerializer os = new ObjectSerializer();
		String buff;
		try {
			buff = os.toXmlString(c);
			int actualLen = buff.length();
			assertEquals(expectedLen, actualLen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFromXmlString() {
		int expectedSize = 3;
		InstContentCollection c = new InstContentCollection();
		c.setCollection(sampleData());
		ObjectSerializer os = new ObjectSerializer();
		String xmlString;
		try {
			xmlString = os.toXmlString(c);
			InstContentCollection a = (InstContentCollection) os.fromXmlString(InstContentCollection.class, xmlString);
			assertEquals(expectedSize, a.getCollection().size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
