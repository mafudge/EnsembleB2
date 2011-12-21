package edu.syr.ischool.mafudge.ensemblelib.repositories;

import edu.syr.ischool.mafudge.ensemblelib.ObjectSerializer;
import edu.syr.ischool.mafudge.ensemblelib.models.ApiSettings;

public class ApiSettingsRepository {
	
	private ApiSettings m_settings;
	
	public ApiSettings getApiSettings() { return m_settings; }
	public void setApiSettings(ApiSettings value) { m_settings = value; }

	public String toSerializedXmlString() throws Exception
	{
		ObjectSerializer os = new ObjectSerializer();
		String xmlString = os.toXmlString(m_settings);
		return xmlString;
	}
	
	public void fromSerializedXmlString(String xmlString) throws Exception
	{
		ObjectSerializer os = new ObjectSerializer();
		m_settings = (ApiSettings)os.fromXmlString(ApiSettings.class, xmlString);
	}


}
