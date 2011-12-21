package edu.syr.ischool.mafudge.ensemblelib.repositories;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

import edu.syr.ischool.mafudge.ensemblelib.models.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



public class WebDestinationRepository {
	
	private List<WebDestination> m_wdlist;
	
	//Constructor
	public WebDestinationRepository() {
		m_wdlist = new ArrayList<WebDestination>();
	}
	
	public List<WebDestination> getWebDestinations()
	{
		return m_wdlist;
	}
	
	public boolean addWebDestination( WebDestination wd)
	{
		boolean result = false;
		if  (!existsWebDestinationById(wd.webDestinationID))
		{
			result = m_wdlist.add(wd);
		}
		return result;
	}
	
	public boolean existsWebDestinationById(String webDestinationID)
	{
		boolean result = false;
		for (WebDestination wd : m_wdlist)
		{
			if (wd.webDestinationID == webDestinationID) {
				result = true;
				break;
			}
		}
		return result;	
	}

	
	// builds the repository from an Raw XML string (as received from Ensemble APIs).
	public void  fromRawXmlString(String xmlString) throws Exception
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db =dbf.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(xmlString.getBytes());		
		Document doc=db.parse(is);
		m_wdlist  = new ArrayList<WebDestination>(); // start with empty
		    
		NodeList nl = doc.getElementsByTagName("WebDestination");
		if (nl.getLength() == 0) {
			nl = doc.getElementsByTagName("WebDestination");
		}
		
		for(int i=0;i<nl.getLength();i++)
		{
			WebDestination wd = new WebDestination();
			NodeList wdID=doc.getElementsByTagName("ID");
			  
			wd.webDestinationID =wdID.item(i).getChildNodes().item(0).getNodeValue();
			wd.webDestinationName = getElementValue(doc,"Name",i);
			wd.libraryID = getElementValue(doc,"LibraryID",i);
			wd.libraryName = getElementValue(doc,"LibraryName",i);
			this.addWebDestination(wd);
		} //end for
	}

	private String getElementValue(Document xmlDoc, String tagName, int index) 
	{
		String result = "";
		try {
			result =  xmlDoc.getElementsByTagName(tagName).item(index).getChildNodes().item(0).getNodeValue();
		} catch (NullPointerException e) {
			result = "";
		}
		return result;
	}
	
}
