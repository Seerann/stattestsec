package com.springapp.mvc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Slame on 08.09.2015.
 */
@XmlRootElement(name = "usersl")
public class Usersl implements Serializable{
	private List<Users> usersList;

	@XmlElement(name = "users")
	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}
}
