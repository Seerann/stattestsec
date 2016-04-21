package com.springapp.mvc.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Slame on 07.09.2015.
 */
@XmlRootElement(name = "errResponse")
public class ErrResponse {
	private String code; //код ошибки
	private String msg; //сообщение об ошибке

	/**
	 * Сложный конструктор
	 *
	 * @param code код ошибки
	 * @param msg сообщение об ошибке
	 */
	public ErrResponse(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ErrResponse() {
	}

	/**
	 * Устанавливает сообщение об ошибке
	 *
	 * @param msg сообщение об ошибке
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Возвращает сообщение об ошибке
	 *
	 * @return сообщение об ошибке
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Возвращает код ошибки
	 *
	 * @return код ошибки
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Устанавливает код ошибки
	 *
	 * @param code код ошибки
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
