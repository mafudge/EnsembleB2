package edu.syr.ischool.mafudge.ensemblelib.collections;
import edu.syr.ischool.mafudge.ensemblelib.models.InstContentWebDestination;


import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


//
// This is an enumerable collection of WebDestination
//
@Root
public class InstContentCollection {
	
	@ElementList
	private List<InstContentWebDestination> m_list; 
	
	public InstContentCollection() {
		m_list = new ArrayList<InstContentWebDestination>();
	}

	
	public List<InstContentWebDestination> getCollection()  {
		return m_list;
	}
	
	public void setCollection(List<InstContentWebDestination> wd) {
		m_list = wd;
	}

}
