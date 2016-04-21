package com.springapp.mvc.dao;

import com.springapp.mvc.entity.Users;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Slame on 07.09.2015.
 */
public interface UsersDaoI extends Remote {

	/**
	 * Метод получения данных о пользователе по Ид
	 * @param login идентификатор
	 * @return сущность Users
	 */
	Users getUsers(String login);

	/**
	 * Метод получения всех пользователей из таблицы Users
	 * @return Список сущностей Users
	 */
	List<Users> getAllUsers();

	/**
	 * Метод получения списка пользователей Департамента тестирования Back-End
	 * @return Список сущностей Users
	 */
	List<Users> getAllUseresFromDepBackEnd();
}

