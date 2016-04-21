package com.springapp.mvc.dao.rowmapper;

import com.springapp.mvc.entity.Tcversions;
import com.springapp.mvc.entity.Users;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Slame on 08.09.2015.
 */
public class TcversionsRowMapper implements ParameterizedRowMapper<Tcversions> {

	@Override
	public Tcversions mapRow(ResultSet rs, int i) throws SQLException {
		Tcversions tcversions = new Tcversions();

		tcversions.setId(rs.getInt("id"));
		tcversions.setStatus(rs.getInt("status"));
		tcversions.setSummary(rs.getString("summary"));
		tcversions.setImportance(rs.getInt("importance"));
		tcversions.setCreation_ts(rs.getTimestamp("creation_ts"));
		tcversions.setUpdater_id(rs.getInt("updater_id"));
		tcversions.setActive(rs.getBoolean("active"));
		tcversions.setCnt(rs.getInt("cnt"));

		return tcversions;
	}
}