package edu.syr.ischool.mafudge.ensemblelib.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

//
// A Video
//
@Root
public class Video {

	// TODO: Change these to the appropraite data type
	public Video() { }
	
	@Element
	public String videoID;
	@Element (required=false)
	public String videoDate;
	@Element (required=false)
	public String videoDescription;
	@Element (required=false)
	public String videoTitle;
	@Element (required=false)
	public String videoKeywords;
	@Element (required=false)
	public String thumbnailUrl;
	@Element (required=false)
	public String libraryID;
	@Element (required=false)
	public String libraryName;
	
}
/* XML that comes from ensemble
<Content><ID>GM3qbb72fEe03qtRzpnyiQ</ID><DateAdded>2/7/2011 3:36:49 PM</DateAdded><Description/><Title>UGA Movie</Title><Keywords/><ThumbnailUrl>http://dev.ensembleVideo.com/app/assets/L3ae6LUOck6qH2jCCGS30A.jpg?width=100</ThumbnailUrl><LibraryID>9D7BA2A6-D2C8-4B79-9414-49A751B0530B</LibraryID><LibraryName>College of Business</LibraryName></Content> */
