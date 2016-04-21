package com.springapp.mvc.dao;

import com.springapp.mvc.entity.Tcversions;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Slame on 08.09.2015.
 */
public interface TcversionsDaoI extends Remote {
	/**
	 * Метод получения колличества ТестКейсов за период по Ид пользователя
	 * @param userId идентификатор пользователя
	 * @param bper начальный период
	 * @param eper конечный период
	 * @return список сущностей Tcversions
	 */
	List<Tcversions> getCntByMonth(String userId, String bper, String eper);
}
