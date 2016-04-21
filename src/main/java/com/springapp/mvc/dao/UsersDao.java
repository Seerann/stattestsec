package com.springapp.mvc.dao;

import com.springapp.mvc.entity.Users;
import com.springapp.mvc.utils.DaoJdbc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Slame on 07.09.2015.
 */
public class UsersDao extends DaoJdbc<Users> implements UsersDaoI {


	public UsersDao() {
		setTableName("users");

		setInsertSql("insert into users  "
				+ "(id, login, password, role_id, email, first, last, locale, default_testproject_id, active," +
				"script_key, cookie_string)"
				+ "values  "
				+ "(:id, :login, :password, :role_id, :email, :first, :last, :locale, :default_testproject_id," +
				":active, :script_key, :cookie_string)");

		setUpdateSql("update users   "
				+ "set login = :login,       "
				+ "password     = :password,       "
				+ "email       = :email,       "
				+ "first = :first,       "
				+ "last = :last,       "
				+ "locale = :locale,       "
				+ "default_testproject_id = :default_testproject_id, "
				+ "active = :active, "
				+ "script_key = :script_key,"
				+ "cookie_string = :cookie_string"
				+ "where id = :id");


		setDeleteSql("delete from users where id = :id ");

		setSelectSql("select * from users ");

		setWhereById("where id = :id ");

	}

	/**
	 * @inheritDoc
	 */
	@Override
	public Users getUsers(String id) {
		List<Users> usersList = null;
		HashMap params = new HashMap();
		params.put("id", id);

		String sql = "SELECT t.*\n"
				+ "     FROM " + this.getTableName() + " t \n"
				+ "    WHERE t.id = :id \n";

		usersList = getNamedParameterJdbcTemplate().query(
				sql,
				params,
				this.getRowMaper());
		Users users = null;
		if (null != usersList && !usersList.isEmpty()) {
			users = usersList.get(0);
		}
		return users;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Users> getAllUsers() {
		List<Users> usersList = null;
		HashMap params = new HashMap();

		String sql = "SELECT t.*\n"
				+ "     FROM " + this.getTableName() + " t \n";

		usersList = getNamedParameterJdbcTemplate().query(
				sql,
				params,
				this.getRowMaper());

		return usersList;
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Users> getAllUseresFromDepBackEnd() {
		List<Users> usersList = null;
		HashMap params = new HashMap();

		String sql = "SELECT t.*\n"
				+ "     FROM " + this.getTableName() + " t \n"
				+ "    WHERE t.login IN('dn270985vdp','IT270985VDP','DN170779RAA','DN030190SBR','IT030190SBR'," +
				"'DN120990MGN1','DN101187AJN','DN091289VRV','DN170878KSV','IT170878KSV','DN020986LEV','DN071175MAJ'," +
				"'IT071175MAJ','dn040589lvv1','dn140192tiv','IT140192TIV','dn250886lmg','dn020589sea','IT020589SEA'," +
				"'DN271288AEV','DN061284BAM','DN270189KBS','DN210780MSV','DN260484MSA','DN020189LDA','IT020189LDA'," +
				"'dn070890sds','dn080287dep','IT080287DEP','it160591sas','dn150786cjm','DN010389ADV','IT010389ADV'," +
				"'DN041088TEM','IT240476MEV','dn310387ism','dn260591aoi','it091090mas','dn210990jjs2','IT210990JJS2'," +
				"'DN010485KNP','DN270189KBS','IT270189KBS','DN260484MSA','IT260484MSA','DN090489MIV','IT090489MIV'," +
				"'DN230489PAS','DN190387PEP','DN210780RSV','IT210780RSV','DN260289SVV','IT260289SVV') Order by t.last \n";

		usersList = getNamedParameterJdbcTemplate().query(
				sql,
				params,
				this.getRowMaper());

		return usersList;
	}
}
