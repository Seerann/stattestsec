package com.springapp.mvc.utils;

import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Slame on 17.06.2015.
 */
public class JaxbGsonUtils {

	public String objectToXml(Object obj, Class... classes) throws JAXBException {
		JAXBContext jaxbContextSes = JAXBContext.newInstance(classes);
		Marshaller marshaller = jaxbContextSes.createMarshaller();
		StringWriter sw = new StringWriter();
		marshaller.marshal(obj, sw);
		return sw.toString();
	}

	public Object xmlToObject(String xml, Class... classes) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(classes);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xml);
		return unmarshaller.unmarshal(reader);
	}

	public String objectToJson(Object obj) throws JAXBException {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}

	public Object jsonToObject(String json, Class clas) throws JAXBException {
		Gson gson = new Gson();
		return gson.fromJson(json, clas);
	}

	/**
	 * конвертирует обект в XML
	 *
	 * @param o объект для перобразования
	 * @return строка с XML
	 */
	static public String ObjectToXML(Object o) {
		StringWriter sw = new StringWriter();

		try {

			JAXBContext context = JAXBContext.newInstance(o.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(o, sw);
		} catch (JAXBException exception) {
			Logger.getLogger(o.getClass().getName()).
					log(Level.SEVERE, "marshallExample threw JAXBException", exception);
		}

		return sw.toString();

	}
}
