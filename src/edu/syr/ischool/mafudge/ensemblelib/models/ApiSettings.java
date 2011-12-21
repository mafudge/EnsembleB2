package edu.syr.ischool.mafudge.ensemblelib.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

// NOT USED right now

@Root
public class ApiSettings {
	@Element (required=true)
	public String serverUrl;
	
	@Element (required=true)	
	public String apiKey;
	
	@Element (required=true)
	public String secretKey;
	
}
