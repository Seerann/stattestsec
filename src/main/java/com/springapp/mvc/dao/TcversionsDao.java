package com.springapp.mvc.dao;

import com.springapp.mvc.entity.Tcversions;
import com.springapp.mvc.utils.DaoJdbc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Slame on 08.09.2015.
 */
public class TcversionsDao extends DaoJdbc<Tcversions> implements TcversionsDaoI {

	public TcversionsDao() {
		setTableName("tcversions");

		setInsertSql("insert into tcversions  "
				+ "(id, tc_external_id, version, layout, status, summary, preconditions, importance, author_id, creation_ts," +
				"updater_id, modification_ts, active, is_open, execution_type, estimated_exec_duration)"
				+ "values  "
				+ "(:id, :tc_external_id, :version, :layout, :status, :summary, :preconditions, :importance, :author_id," +
				":creation_ts, :updater_id, :modification_ts, :active, :is_open, :execution_type, :estimated_exec_duration)");

		setUpdateSql("update tcversions   "
				+ "set tc_external_id = :tc_external_id,       "
				+ "version     = :version,       "
				+ "layout       = :layout,       "
				+ "status = :status,       "
				+ "summary = :summary,       "
				+ "preconditions = :preconditions,       "
				+ "importance = :importance, "
				+ "author_id = :author_id, "
				+ "creation_ts = :creation_ts,"
				+ "updater_id = :updater_id,"
				+ "modification_ts = :modification_ts,"
				+ "active = :active,"
				+ "is_open = :is_open,"
				+ "execution_type = :execution_type,"
				+ "estimated_exec_duration = :estimated_exec_duration"
				+ "where id = :id");


		setDeleteSql("delete from tcversions where id = :id ");

		setSelectSql("select * from tcversions ");

		setWhereById("where id = :id ");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Tcversions> getCntByMonth(String userId, String bper, String eper) {
		List<Tcversions> tcversionsList = null;
		HashMap params = new HashMap();
		params.put("userId", userId);
		params.put("bper", bper);
		params.put("eper", eper);

		String sql = "select \n" +
				"date_format(t.creation_ts, \"%Y-%m\") summary,\n" +
				"SUM(STATUS) cnt,\n" +
				"null id,\n" +
				"null status,\n" +
				"null importance,\n" +
				"null creation_ts,\n" +
				"null updater_id,\n" +
				"null active"
				+ "     FROM " + this.getTableName() + " t \n"
				+ "    where t.author_id in (" + userId + ") " +
				"AND t.creation_ts is not null " +
				"AND t.creation_ts >= :bper " +
				"AND t.creation_ts  <= :eper\n" +
				"GROUP BY date_format(t.creation_ts, \"%Y-%m\") \n" +
				"ORDER BY summary";

		tcversionsList = getNamedParameterJdbcTemplate().query(
				sql,
				params,
				this.getRowMaper());
		return tcversionsList;
	}
}
