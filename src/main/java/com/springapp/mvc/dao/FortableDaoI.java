package com.springapp.mvc.dao;

import com.springapp.mvc.entity.Fortable;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Slame on 10.09.2015.
 */
public interface FortableDaoI extends Remote {
	/**
	 * Метод получения данных для основной таблицы статистики ТестЛинк
	 * @param bper начальный период
	 * @param eper конечный период
	 * @return список сущностей Fortable
	 */
	List<Fortable> getTableData(String bper, String eper);
}
