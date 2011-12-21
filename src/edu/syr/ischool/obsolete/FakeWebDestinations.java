package edu.syr.ischool.obsolete;
import edu.syr.ischool.obsolete.WebDestination;

import java.util.ArrayList;


public class FakeWebDestinations {
	private ArrayList<WebDestination> m_al = new ArrayList<WebDestination>();
	
	public FakeWebDestinations() {
		m_al.add(new WebDestination("kfuS9kxl70mW7NBM9IPJsA","BIO 101"));
		m_al.add(new WebDestination("hbGv8P7Y9U-hTKml7t0wIA","Campus TV"));
		m_al.add(new WebDestination("kxNqGEBxbU-S3JjD_kB-Qw","HIS 222"));		
	}
	
	public ArrayList<WebDestination> getFakeWebDestinations()  {
		return m_al;
	}
}
