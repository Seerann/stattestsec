package com.springapp.mvc.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by Slame on 10.09.2015.
 */
@XmlType
@XmlRootElement(name = "fortable")
public class Fortable extends TBEntity implements Serializable {
	private Integer id;
	private String login;
	private String email;
	private String first;
	private String last;
	private Integer cntC;
	private Integer cntM;
	private String secId;

	public Fortable(Integer id, String login, String email, String first, String last, Integer cntC, Integer cntM) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.first = first;
		this.last = last;
		this.cntC = cntC;
		this.cntM = cntM;
	}

	public Fortable() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public Integer getCntC() {
		return cntC;
	}

	public void setCntC(Integer cntC) {
		this.cntC = cntC;
	}

	public Integer getCntM() {
		return cntM;
	}

	public void setCntM(Integer cntM) {
		this.cntM = cntM;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}
}
