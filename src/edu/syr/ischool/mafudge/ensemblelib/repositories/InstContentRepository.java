package edu.syr.ischool.mafudge.ensemblelib.repositories;

import java.util.ArrayList;
import java.util.List;

import edu.syr.ischool.mafudge.ensemblelib.ObjectSerializer;
import edu.syr.ischool.mafudge.ensemblelib.collections.InstContentCollection;
import edu.syr.ischool.mafudge.ensemblelib.models.InstContentWebDestination;

//
// Repository for WebDestination(s) supports Xml persistence and CRUD
//
public class InstContentRepository {
	
	private List<InstContentWebDestination> m_wdList;
	
	// Constructor
	public InstContentRepository() {
		m_wdList = new ArrayList<InstContentWebDestination>();
	}

	// Returns a collection of InstContentWebDestinations
	public List<InstContentWebDestination> getWebDestinations()
	{
		return m_wdList;
	}
	
	// Sets the collection of WebDestinations
	public void setWebDestinations(List<InstContentWebDestination> wdList)
	{
		m_wdList = wdList;
	}
	
	// Adds a WebDestination, provided the wdId does not already exist.
	public boolean addWebDestination(InstContentWebDestination wd)
	{	
		boolean result = false;
		if  (!existsWebDestinationById(wd.wdId))
		{
			result = m_wdList.add(wd);
		}
		return result;
	}
	
	// Deletes the WebDestination with wdIdKey from the collection
	public boolean deleteWebDestinationById(String wdIdKey) throws Exception
	{
		boolean result = false;
		for (InstContentWebDestination wd : m_wdList)
		{
			if (wd.wdId.equals(wdIdKey)) {
				result = m_wdList.remove(wd);
				break;
			}
		}
		return result;
	}
	
	// Verifies whether the wdIdKey exists in the collection
	public boolean existsWebDestinationById(String wdIdKey) 
	{
		boolean result = false;
		for (InstContentWebDestination wd : m_wdList)
		{
			if (wd.wdId.equals(wdIdKey)) {
				result = true;
				break;
			}
		}
		return result;	
	}
	
	// Converts the Repository to XML
	public String toSerializedXmlString() throws Exception
	{
		InstContentCollection wdc = new InstContentCollection();
		wdc.setCollection(m_wdList);
		ObjectSerializer os = new ObjectSerializer();
		String xmlString = os.toXmlString(wdc);
		return xmlString;
	}
	
	// builds the repository from an XML string.
	public void  fromSerializedXmlString(String xmlString) throws Exception
	{
		ObjectSerializer os = new ObjectSerializer();
		InstContentCollection wdc = new InstContentCollection();
		if (xmlString.length() > 0) {  // null check
			wdc = (InstContentCollection)os.fromXmlString(InstContentCollection.class, xmlString);
		}
		m_wdList = wdc.getCollection();
	}
	
}
