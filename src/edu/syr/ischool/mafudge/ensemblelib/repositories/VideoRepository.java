package edu.syr.ischool.mafudge.ensemblelib.repositories;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

import edu.syr.ischool.mafudge.ensemblelib.ObjectSerializer;
import edu.syr.ischool.mafudge.ensemblelib.collections.VideoCollection;
import edu.syr.ischool.mafudge.ensemblelib.models.Video;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


//
// Repository for Video(s) supports Xml persistence and CRUD
//
public class VideoRepository {
	
	private List<Video> m_vidList;
	
	// Constructor
	public VideoRepository() {
		m_vidList = new ArrayList<Video>();
	}

	// Returns a collection of Videos
	public List<Video> getVideos()
	{
		return m_vidList;
	}
	
	// Sets the collection of Videos
	public void setVideos(List<Video> vList)
	{
		m_vidList = vList;
	}
	
	// Adds a Video, provided the wdId does not already exist.
	public boolean addVideo(Video vid)
	{	
		boolean result = false;
		if  (!existsVideoById(vid.videoID))
		{
			result = m_vidList.add(vid);
		}
		return result;
	}
	
	// Adds a list of video to this repository returns the number added
	public int addVideos( List<Video> vl)
	{
		int count = 0;
		for (Video v : vl)
		{
			if (this.addVideo(v)) count++;
		}
		return count;
	}
	
	// Deletes the Video with wdIdKey from the collection
	public boolean deleteVideoById(String videoId)
	{
		boolean result = false;
		for (Video vid : m_vidList)
		{
			if (vid.videoID == videoId) {
				result = m_vidList.remove(vid);
				break;
			}
		}
		return result;
	}
	
	// Verifies whether the wdIdKey exists in the collection
	public boolean existsVideoById(String videoId)
	{
		boolean result = false;
		for (Video vid : m_vidList)
		{
			if (vid.videoID == videoId) {
				result = true;
				break;
			}
		}
		return result;	
	}

	// Converts the Repository to XML
	public String toSerializedXmlString() throws Exception
	{
		VideoCollection vc = new VideoCollection();
		vc.setCollection(m_vidList);
		ObjectSerializer os = new ObjectSerializer();
		String xmlString = os.toXmlString(vc);
		return xmlString;
	}
	
	// builds the repository from an XML string.
	public void  fromSerializedXmlString(String xmlString) throws Exception
	{
		ObjectSerializer os = new ObjectSerializer();
		VideoCollection vc = new VideoCollection();
		vc = (VideoCollection)os.fromXmlString(VideoCollection.class, xmlString);
		m_vidList = vc.getCollection();
	}
	
	// builds the repository from an Raw XML string (as received from Ensemble APIs).
	public void  fromRawXmlString(String xmlString) throws Exception
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db =dbf.newDocumentBuilder();
		InputStream is = new ByteArrayInputStream(xmlString.getBytes("UTF8"));		
		Document doc=db.parse(is);
		m_vidList = new ArrayList<Video>(); // start with empty
		Boolean contentSchema = true;
		    
		NodeList nl = doc.getElementsByTagName("Content");
		if (nl.getLength() == 0) {
			nl = doc.getElementsByTagName("video");
			contentSchema = false;
		}
		for(int i=0;i<nl.getLength();i++)
		{
			Video v = new Video();
			NodeList videoID;
			if (contentSchema) {
				videoID=doc.getElementsByTagName("ID");
				v.videoID=videoID.item(i).getChildNodes().item(0).getNodeValue();
				v.videoDate = getElementValue(doc,"DateAdded",i);
				v.videoDescription = getElementValue(doc,"Description",i);
				v.videoTitle = getElementValue(doc,"Title",i);
				v.videoKeywords = getElementValue(doc,"Keywords",i);
				v.thumbnailUrl = getElementValue(doc,"ThumbnailUrl",i);
				v.libraryID = getElementValue(doc,"LibraryID",i);
				v.libraryName = getElementValue(doc,"LibraryName",i);				
			} else { // not contentSchema, but old publicApi schema
				videoID = doc.getElementsByTagName("videoID");
				v.videoID=videoID.item(i).getChildNodes().item(0).getNodeValue();
				v.videoDate = getElementValue(doc,"videoDate",i);
				v.videoDescription = getElementValue(doc,"videoDescription",i);
				v.videoTitle = getElementValue(doc,"videoTitle",i);
				v.videoKeywords = getElementValue(doc,"videoKeywords",i);
				v.thumbnailUrl = getElementValue(doc,"thumbnailUrl",i);
				v.libraryID = getElementValue(doc,"departmentID",i);
				v.libraryName = getElementValue(doc,"departmentName",i);				
			}
			if (videoID.getLength()== 0 ) {
			}
			  
			this.addVideo(v);
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
		
} //end class
		/* XML that comes from ensemble
		 <video>
			<videoID>p2hJ_RWViki60F7wHKYeTg</videoID>
			<videoDate>2010-10-13T10:34:52-04:00</videoDate>
			<videoDateProduced>2010-10-13T00:00:00-04:00</videoDateProduced>
			<videoDescription/>
			<videoTitle>Ensemble Video Annotation Demo</videoTitle>
			<videoDuration>99</videoDuration>
			<videoKeywords/>
			<isVideoContent>true</isVideoContent>
			<thumbnailUrl>http://demo.ensemblevideo.com/app/assets/xace_lzrwEO46x2BKiJtKA.jpg?width=100</thumbnailUrl>
			<previewUrl>http://demo.ensemblevideo.com/app/assets/ejQBw6WQ3UG-csd_OqSvKQ.jpg</previewUrl>
			<viewCount>9</viewCount>
			<webSiteID>B9DB461B-7853-411C-892B-3AF840804C4B</webSiteID>
			<webSiteName>Demo</webSiteName>
			<departmentID>B75A1899-768C-4DDA-8586-BAE9F6300735</departmentID>
			<departmentName>University Relations Office</departmentName>
			<departmentWebSite/>
			<departmentLogo/>
		</video> 
		 */



