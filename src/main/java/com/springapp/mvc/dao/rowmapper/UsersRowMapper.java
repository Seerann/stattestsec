package com.springapp.mvc.dao.rowmapper;

import com.springapp.mvc.entity.Users;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Slame on 07.09.2015.
 */
public class UsersRowMapper implements ParameterizedRowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int i) throws SQLException {
		Users users = new Users();

		users.setId(rs.getInt("id"));
		users.setLogin(rs.getString("login"));
		users.setPassword(rs.getString("password"));
		users.setRole_id(rs.getInt("role_id"));
		users.setEmail(rs.getString("email"));
		users.setFirst(rs.getString("first"));
		users.setLast(rs.getString("last"));
		users.setLocale(rs.getString("locale"));
		users.setDefault_testproject_id(rs.getInt("default_testproject_id"));
		users.setActive(rs.getInt("active"));
		users.setScript_key(rs.getString("script_key"));
		users.setCookie_string(rs.getString("cookie_string"));

		return users;
	}
}
