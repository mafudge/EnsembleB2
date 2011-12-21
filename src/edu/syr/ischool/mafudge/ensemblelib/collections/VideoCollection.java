package edu.syr.ischool.mafudge.ensemblelib.collections;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import edu.syr.ischool.mafudge.ensemblelib.models.Video;

//
// This is an enumerable collection of class Video
//
@Root
public class VideoCollection {

	@ElementList
	private List<Video> m_list;

	public VideoCollection() { 
		m_list = new ArrayList<Video>();
	}
	
	public List<Video> getCollection()  {
		return m_list;
	}
	
	public void setCollection(List<Video> vids) {
		m_list = vids;
	}

}
