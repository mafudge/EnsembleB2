package edu.syr.ischool.mafudge.ensemblelib;

import java.io.StringWriter;

import org.simpleframework.xml.core.*;
import org.simpleframework.xml.*;

public class ObjectSerializer {

	private Serializer serializer;
	
	public ObjectSerializer() {
		serializer = new Persister();
	}

	public String toXmlString(Object obj) throws Exception {
        StringWriter sw= new StringWriter();
		serializer.write(obj,sw);
        return sw.toString();
	}
	
	public Object fromXmlString( Class<?> className, String xmlString) throws Exception {
	    Serializer serializer = new Persister();
	    Object obj= serializer.read(className,xmlString);
	    return obj;
	}
}
