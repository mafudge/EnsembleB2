package edu.syr.ischool.mafudge.ensemblelib.models;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


//
// A WebDestination
//
@Root
public class WebDestination {
	
	@Element (required=true)
	public String webDestinationID;
	
	@Element (required=false)
	public String webDestinationName;

	@Element (required=true)
	public String libraryID;
	
	@Element (required=false)
	public String libraryName;

	
	public WebDestination() { }
}

/*
  XML for WebDestination goes here. (for reference)
<WebDestination>
	<departmentID>2feb6c02-b28f-4ebe-b016-77f548f9cadb</departmentID>
	<departmentName>Foo Department</departmentName>
	<webDestinationID>C58fbEQZ50SaTG-WO-kRlg</webDestinationID>
	<webDestinationName>Blackboard</webDestinationName>
</WebDestination> 
 */