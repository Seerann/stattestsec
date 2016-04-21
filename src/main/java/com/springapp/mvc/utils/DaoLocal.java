package com.springapp.mvc.utils;

import com.springapp.mvc.entity.TBEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Slame on 07.09.2015.
 */
public interface DaoLocal <T extends TBEntity> extends Serializable {

	int createNT(T entity);

	/**
	 * Всавить запись в таблицу соотвествующую сущности (транзакционный)
	 * @param T entity - объект
	 * @return id вставленной записи
	 */
	int create(T entity);

	/**
	 * Вставляет спиок записей в таблицу соотвествующую сущности
	 * @param entities - список сущностей
	 * @return список ID вставленных записей
	 */
	ArrayList<Integer> create(List<T> entities);

	/**
	 * Обновить запись в таблице соотвествующей сущности (нетранзакционный)
	 * @param entity объект запись
	 * @return количесво модифицированных записей
	 */
	int updateNT(T entity);
	/**
	 * Обновить запись в таблице соотвествующей сущности (транзакционный)
	 * @param entity объект запись
	 * @return количесво модифицированных записей
	 */
	int update(T entity);

	/**
	 * Обновляет записи согласно списка сущностей
	 * @param entities спиок сущностей
	 * @return количесво модифицированных записей
	 */
	int update(List<T> entities);

	/**
	 * Удалить запись (нетранзакционный)
	 * @param id ID записи которую нужно удалить
	 * @return количесво удаленных записей
	 */
	int removeNT(Integer id);

	/**
	 * Удалить запись (транзакционный)
	 * @param id ID записи которую нужно удалить
	 * @return количесво удаленных записей
	 */
	int remove(Integer id);

	/**
	 * Удаляет список сущностей
	 * @param entities список сущностей к удалению
	 * @return количесво удаленных записей
	 */
	int remove(List<T> entities);

	/**
	 * Поиск строений по ID+IRCID
	 * @param id
	 * @return
	 */
	List<T> findById(Integer id);


	/**
	 * Поиск объектов "строения" по заданому условию SQL с заданной сортировкой
	 * @param w - условие where
	 * @param o - условие сортировки
	 * @return
	 */
	List<T> findBySubst(String w, String o);

	/**
	 * поиск по пргизвольному запросу
	 * @param sql тело SQL запроса
	 * @return список сущностей который возвратить заданный SQL запрос
	 */
	List<T> findByArbitraryQuery(String sql, HashMap params);

	/**
	 * Возвращает следующее значение сиквенции
	 * @return
	 */

	Integer getSequenceNextVal();
	/**
	 * Возвращает значение свойства @SequnceName - имя генератора уникальных хначений
	 * @return
	 */
	public String getSequnceName();

	/**
	 * Возвращает значение свойства @SequnceName - имя генератора уникальных хначений
	 * @param SequnceName
	 */
	public void setSequnceName(String sequnceName);

}
