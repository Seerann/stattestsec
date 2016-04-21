package com.springapp.mvc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slame on 09.09.2015.
 */
@XmlRootElement(name = "tcversionsl")
public class Tcversionsl implements Serializable {
	private List<Tcversions> tcversionsList;

	@XmlElement(name = "tcversions")
	public List<Tcversions> getTcversionsList() {
		return tcversionsList;
	}

	public void setTcversionsList(List<Tcversions> tcversionsList) {
		this.tcversionsList = tcversionsList;
	}
}
