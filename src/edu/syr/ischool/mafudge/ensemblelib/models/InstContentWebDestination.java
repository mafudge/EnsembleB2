package edu.syr.ischool.mafudge.ensemblelib.models;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


//
// A WebDestination
//
@Root
public class InstContentWebDestination {
	
	@Attribute
	public String wdId;
	@Element (required=false)
	public String wdName;
	
	public InstContentWebDestination(String theId, String theName) { 
		this.wdId = theId; 
		this.wdName = theName;
	}
	
	public InstContentWebDestination() { }
}
