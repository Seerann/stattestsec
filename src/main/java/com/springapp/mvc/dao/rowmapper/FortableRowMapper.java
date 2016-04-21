package com.springapp.mvc.dao.rowmapper;

import com.springapp.mvc.entity.Fortable;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Slame on 10.09.2015.
 */
public class FortableRowMapper implements ParameterizedRowMapper<Fortable> {
	@Override
	public Fortable mapRow(ResultSet rs, int i) throws SQLException {
		Fortable fortable = new Fortable();

		fortable.setId(rs.getInt("id"));
		fortable.setLogin(rs.getString("login"));
		fortable.setEmail(rs.getString("email"));
		fortable.setFirst(rs.getString("first"));
		fortable.setLast(rs.getString("last"));
		fortable.setCntC(rs.getInt("cntC"));
		fortable.setCntM(rs.getInt("cntM"));

		return fortable;
	}
}
