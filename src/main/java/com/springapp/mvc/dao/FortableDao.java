package com.springapp.mvc.dao;

import com.springapp.mvc.entity.Fortable;
import com.springapp.mvc.utils.DaoJdbc;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Slame on 10.09.2015.
 */
public class FortableDao extends DaoJdbc<Fortable> implements FortableDaoI {

	public FortableDao() {
		setTableName("tcversions");
	}

	/**
	 * @inheritDoc
	 */
	@Override
	public List<Fortable> getTableData(String bper, String eper) {
		List<Fortable> fortableList = null;
		HashMap params = new HashMap();
		params.put("bper", bper);
		params.put("eper", eper);

		String sql = "\n" +
				"SELECT a.id, a.last, a.`first`, a.login, a.email, q.cntC, r.cntM\n" +
				"from users a\n" +
				"LEFT JOIN \n" +
				"(\n" +
				"select COUNT(t.author_id) cntC, t.author_id\n" +
				"from users a\n" +
				"LEFT JOIN tcversions t\n" +
				"on a.id = t.author_id\n" +
				"WHERE t.creation_ts BETWEEN :bper AND :eper\n" +
				"\n" +
				"GROUP BY a.id\n" +
				") q\n" +
				"on a.id = q.author_id\n" +
				"\n" +
				"LEFT JOIN \n" +
				"(\n" +
				"select COUNT(t.updater_id) cntM, t.updater_id\n" +
				"from users a\n" +
				"LEFT JOIN tcversions t\n" +
				"on a.id = t.updater_id\n" +
				"WHERE t.modification_ts BETWEEN :bper AND :eper\n" +
				"and DATE_FORMAT(t.modification_ts,'%d.%m.%Y') != DATE_FORMAT(t.creation_ts,'%d.%m.%Y')\n" +
				"GROUP BY a.id\n" +
				") r\n" +
				"on a.id = r.updater_id\n" +
				"\n" +
				"WHERE a.login IN (" +
				"'DN010389ADV','IT010389ADV'," +
				"'DN101187AJN','IT101187AJN'," +
				"'DN271288AEV','IT271288AEV'," +
				"'DN061284BAM','IT061284BAM'," +
				"'DN190986GSS','IT190986GSS'," +
				"'DN080287DEP','IT080287DEP'," +
				"'DN170878KSV','IT170878KSV'," +
				"'DN010485KNP','IT010485KNP'," +
				"'IT210990JJS2','DN210990JJS2'," +
				"'IT270189KBS','DN270189KBS'," +
				"'IT250886LMG','DN250886LMG'," +
				"'DN040589LVV1','IT040589LVV1'," +
				"'IT020189LDA','DN020189LDA'," +
				"'IT260484MSA','DN260484MSA'," +
				"'DN120990MGN1','IT120990MGN1'," +
				"'IT071175MAJ','DN071175MAJ'," +
				"'IT240476MEV','DN240476MEV'," +
				"'DN230489PAS','IT230489PAS'," +
				"'DN190387PEP','IT190387PEP'," +
				"'IT210780RSV','DN210780RSV','DN210780MSV','IT210780MSV'," +
				"'IT260289SVV','DN260289SVV'," +
				"'DN150786CJM','IT150786CJM'," +
				"'IT030190SBR','DN030190SBR'," +
				"'IT160591SAS','DN160591SAS'," +
				"'IT041088TEM','DN041088TEM')" +
				"ORDER BY a.last";

		fortableList = getNamedParameterJdbcTemplate().query(
				sql,
				params,
				this.getRowMaper());
		return fortableList;
	}
}
