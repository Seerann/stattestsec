package com.springapp.mvc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slame on 10.09.2015.
 */
@XmlRootElement(name = "fortablel")
public class Fortablel implements Serializable {
	private List<Fortable> fortableList;

	@XmlElement(name = "fortable")
	public List<Fortable> getFortableList() {
		return fortableList;
	}

	public void setFortableList(List<Fortable> fortableList) {
		this.fortableList = fortableList;
	}
}
