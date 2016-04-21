package com.springapp.mvc.utils;

import com.springapp.mvc.entity.TBEntity;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Slame on 07.09.2015.
 */
public class DaoJdbc<T extends TBEntity> extends NamedParameterJdbcDaoSupport implements DaoLocal<T> {
	private String tableName;
	private String sequenceName;
	private String insertSql;
	private String deleteSql;
	private String updateSql;
	private String selectSql;
	private String whereById;
	private ParameterizedRowMapper<T> rowMaper;

	/**
	 * Get the value of tableName
	 *
	 * @return the value of tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Set the value of tableName
	 *
	 * @param tableName new value of tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String getSequnceName() {
		return this.sequenceName;
	}

	@Override
	public void setSequnceName(String sequnceName) {
		this.sequenceName = sequnceName;
	}

	public String getDeleteSql() {
		return deleteSql;
	}

	public void setDeleteSql(String DeleteSql) {
		this.deleteSql = DeleteSql;
	}

	public String getInsertSql() {
		return insertSql;
	}

	public void setInsertSql(String InsertSql) {
		this.insertSql = InsertSql;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String SequenceName) {
		this.sequenceName = SequenceName;
	}

	public String getUpdateSql() {
		return updateSql;
	}

	public void setUpdateSql(String UpdateSql) {
		this.updateSql = UpdateSql;
	}

	public ParameterizedRowMapper getRowMaper() {
		return rowMaper;
	}

	public void setRowMaper(ParameterizedRowMapper rowMaper) {
		this.rowMaper = rowMaper;
	}

	public String getSelectSql() {
		return selectSql;
	}

	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

	public String getWhereById() {
		return whereById;
	}

	public void setWhereById(String whereById) {
		this.whereById = whereById;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.MANDATORY, rollbackFor=Exception.class, readOnly = false)
	public int createNT(T entity) {
		Integer id;
		//получаю значение сиквенции если ID не задан
		if (null == entity.getId()) {
			id = this.getSequenceNextVal();
			entity.setId(id);
		} else {
			id = entity.getId();
		}
		//получаю дату-время обновления
		entity.setRudc(Calendar.getInstance().getTime());
		getNamedParameterJdbcTemplate().update(getInsertSql(),
				new BeanPropertySqlParameterSource(entity));

		return id;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor=Exception.class, readOnly = false)
	public int create(T entity) {
		return this.createNT(entity);
	}



	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.MANDATORY, rollbackFor=Exception.class, readOnly = false)
	public int updateNT(T entity) {

		//получаю дату-время обновления
		entity.setRudc(Calendar.getInstance().getTime());

		return getNamedParameterJdbcTemplate().update(getUpdateSql(),
				new BeanPropertySqlParameterSource(entity));
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor=Exception.class, readOnly = false)
	public int update(T entity) {
		return this.updateNT(entity);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.MANDATORY, rollbackFor=Exception.class, readOnly = false)
	public int removeNT(Integer id) {
		HashMap params = new HashMap();

		params.put("id", id);

		return getNamedParameterJdbcTemplate().update(getDeleteSql(), params);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor=Exception.class, readOnly = false)
	public int remove(Integer id) {
		return this.removeNT(id);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<T> findById(Integer id) {
		if (null == this.selectSql || null == this.whereById) {
			throw new UnsupportedOperationException(
					"Unspecified property selectSql or whereById");
		} else {
			HashMap params = new HashMap();

			params.put("id", id);

			return getNamedParameterJdbcTemplate().query(
					this.selectSql + " " + this.whereById, //"select * from bui1$ where id = :id and ircid = :ircid",
					params,
					this.rowMaper);
		}
	}

	/**
	 * поиск записей по произвольному условию
	 *
	 * @param w условие выборки
	 * @param o условие сортировки (учитывается только когда не пустое и не
	 * null)
	 * @return
	 */
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<T> findBySubst(String w, String o) {
		if (null == this.rowMaper) {
			throw new UnsupportedOperationException(
					"Unspecified property rowMaper");
		}
		if (null == this.tableName) {
			throw new UnsupportedOperationException(
					"Unspecified property tableName");
		} else {
			HashMap params = new HashMap();

			String sql = "select * from " + this.tableName + '\n'
					+ "where " + w;

			if (null != o && !o.isEmpty()) {
				sql = sql + " order by " + o;
			}

			return getNamedParameterJdbcTemplate().query(
					sql,
					params,
					this.rowMaper);
		}
	}

	@Override
	public Integer getSequenceNextVal() {
		if (null != this.sequenceName) {
			String sql = "select " + this.sequenceName + ".NextVal from dual";
			Integer newId = getNamedParameterJdbcTemplate().queryForInt(sql, new HashMap());

			return newId;
		} else {
			throw new UnsupportedOperationException(
					"Unspecified property sequenceName");

		}

	}

	/**
	 * поиск по пргизвольному запросу
	 *
	 * @param sql тело SQL запроса
	 * @return список сущностей который возвратить заданный SQL запрос
	 */
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<T> findByArbitraryQuery(String sql, HashMap params) {
		return getNamedParameterJdbcTemplate().query(
				sql,
				params,
				this.rowMaper);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor=Exception.class, readOnly = false)
	public ArrayList<Integer> create(List<T> entities) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		if (null != entities) {
			int id;
			for (T entity: entities ) {
				id = this.createNT(entity);
				ids.add(id);
			}
		}
		return ids;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor=Exception.class, readOnly = false)
	public int update(List<T> entities) {
		int cntTotal = 0;
		if (null != entities) {
			int cnt = 0;
			for (T entity: entities ) {
				cnt = this.updateNT(entity);
				cntTotal = cntTotal + cnt;
			}
		}
		return cntTotal;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor=Exception.class, readOnly = false)
	public int remove(List<T> entities) {
		int cntTotal = 0;
		if (null != entities) {
			int cnt = 0;
			for (T entity: entities ) {
				cnt = this.removeNT(entity.getId());
				cntTotal = cntTotal + cnt;
			}
		}
		return cntTotal;
	}
}
