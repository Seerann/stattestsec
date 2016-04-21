package com.springapp.mvc.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by Slame on 07.09.2015.
 */
@XmlType
@XmlRootElement(name = "users")
public class Users extends TBEntity implements Serializable {

	private Integer id;
	private String login;
	private String password;
	private Integer role_id;
	private String email;
	private String first;
	private String last;
	private String locale;
	private Integer default_testproject_id;
	private Integer active;
	private String script_key;
	private String cookie_string;

	public Users() {
	}

	public Users(Integer id, String login, String password, Integer role_id, String email, String first, String last,
				 String locale, Integer default_testproject_id, Integer active, String script_key,
				 String cookie_string) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role_id = role_id;
		this.email = email;
		this.first = first;
		this.last = last;
		this.locale = locale;
		this.default_testproject_id = default_testproject_id;
		this.active = active;
		this.script_key = script_key;
		this.cookie_string = cookie_string;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Integer getDefault_testproject_id() {
		return default_testproject_id;
	}

	public void setDefault_testproject_id(Integer default_testproject_id) {
		this.default_testproject_id = default_testproject_id;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getScript_key() {
		return script_key;
	}

	public void setScript_key(String script_key) {
		this.script_key = script_key;
	}

	public String getCookie_string() {
		return cookie_string;
	}

	public void setCookie_string(String cookie_string) {
		this.cookie_string = cookie_string;
	}
}
