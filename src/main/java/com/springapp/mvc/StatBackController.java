package com.springapp.mvc;

import com.springapp.mvc.dao.FortableDaoI;
import com.springapp.mvc.dao.TcversionsDaoI;
import com.springapp.mvc.dao.UsersDaoI;
import com.springapp.mvc.entity.*;
import com.springapp.mvc.utils.JaxbGsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/statback")
public class StatBackController {

	public static String XML_ATTR_NAME = "RESULT_XML";
	@Autowired
	private String viewName;
	@Autowired
	private String dateFormat;
	@Autowired
	private String ldapList;
	@Autowired
	private UsersDaoI usersDao;
	@Autowired
	private TcversionsDaoI tcversionsDao;
	@Autowired
	private FortableDaoI fortableDao;


	/**
	 * Получение списка всех пользователей или 1 при указании ид
	 * @param id идентификатор пользователя (необязательный)
	 * @param model
	 * @return xml информация о пользователе/ях
	 */
	@RequestMapping(value = "/getusers.dao", method = RequestMethod.GET, params = {})
	public String getUsers(@RequestParam(required = false, value = "id") String id,
						   Model model) {

		Object response = null;
		if (id != null) {
			Users users = this.usersDao.getUsers(id);
			if (null == users) {
				response = new ErrResponse("-1", "Нет данных по указанному фильтру");
			} else {
				response = users;
			}
		} else {
			List<Users> usersList = this.usersDao.getAllUsers();
			if (usersList.isEmpty()) {
				response = new ErrResponse("-1", "Нет данных по указанному фильтру");
			} else {
				Usersl usersl = new Usersl();
				usersl.setUsersList(usersList);
				response = usersl;
			}
		}

		model.addAttribute(XML_ATTR_NAME, JaxbGsonUtils.ObjectToXML(response));
		return this.viewName;
	}

	/**
	 * Метод получения колличества ТестКейсов за период по Ид пользователя по месяцам
	 * @param userid Идентификатор пользователя
	 * @param bper начальный период
	 * @param eper конечный период
	 * @param model
	 * @return xml
	 */
	@RequestMapping(value = "/gettcbymonth.dao", method = RequestMethod.GET, params = {})
	public String getCntByMonth(@RequestParam(required = true, value = "userid") String userid,
								@RequestParam(required = true, value = "bper") String bper,
								@RequestParam(required = true, value = "eper") String eper,
								Model model) {

		Object response = null;
		List<Tcversions> tcversionsList = this.tcversionsDao.getCntByMonth(userid, bper, eper);
		if (tcversionsList.isEmpty()) {
			response = new ErrResponse("-1", "Нет данных по указанному фильтру");
		} else {
			Tcversionsl tcversionsl = new Tcversionsl();
			tcversionsl.setTcversionsList(tcversionsList);
			response = tcversionsl;
		}

		model.addAttribute(XML_ATTR_NAME, JaxbGsonUtils.ObjectToXML(response));
		return this.viewName;
	}

	/**
	 * Метод получения данных для тоаблицы статистики ТестЛинк (объединяет измененные лдапы)
	 * @param bper начальный период
	 * @param eper конечный период
	 * @param model
	 * @return xml
	 */
	@RequestMapping(value = "/gettabledata.dao", method = RequestMethod.GET, params = {})
	public String getTableData(@RequestParam(required = true, value = "bper") String bper,
							   @RequestParam(required = true, value = "eper") String eper,
							   Model model) {

		Object response = null;
		List<Fortable> fortableList = this.fortableDao.getTableData(bper, eper);
		if (fortableList.isEmpty()) {
			response = new ErrResponse("-1", "Нет данных по указанному фильтру");
		} else {
			Fortablel fortablel = new Fortablel();
			//отрезаем у лдап первые 2 символа
			List<Fortable> fortablels = new ArrayList<Fortable>();
			for (Fortable fortable : fortableList) {
				fortable.setLogin(fortable.getLogin().substring(2, fortable.getLogin().length()));
			}
			for (int i = 0; i < fortableList.size(); i++) {
				if (i == 0) {
					Fortable fortable = new Fortable();
					fortable.setId(fortableList.get(i).getId());
					fortable.setLast(fortableList.get(i).getLast());
					fortable.setFirst(fortableList.get(i).getFirst());
					fortable.setLogin(fortableList.get(i).getLogin());
					fortable.setEmail(fortableList.get(i).getEmail());
					fortable.setCntC(fortableList.get(i).getCntC());
					fortable.setCntM(fortableList.get(i).getCntM());
					fortablels.add(fortable);

				} else {
					for (int x = 0; x < fortablels.size(); x++) {
						if (fortablels.get(x).getLogin().equalsIgnoreCase(fortableList.get(i).getLogin())) {
							fortablels.get(x).setSecId(fortableList.get(i).getId().toString());
							fortablels.get(x).setCntC(fortablels.get(x).getCntC() + fortableList.get(i).getCntC());
							fortablels.get(x).setCntM(fortablels.get(x).getCntM() + fortableList.get(i).getCntM());
							break;
						} else if (fortablels.get(x).getLogin().equalsIgnoreCase("210780rsv") &&
								fortableList.get(i).getLogin().equalsIgnoreCase("210780MSV") ||
								fortablels.get(x).getLogin().equalsIgnoreCase("210780MSV") &&
								fortableList.get(i).getLogin().equalsIgnoreCase("210780rsv")){
							fortablels.get(x).setSecId(fortableList.get(i).getId().toString());
							fortablels.get(x).setCntC(fortablels.get(x).getCntC() + fortableList.get(i).getCntC());
							fortablels.get(x).setCntM(fortablels.get(x).getCntM() + fortableList.get(i).getCntM());
							break;
						} else if (x == fortablels.size() - 1) {
							Fortable fortable = new Fortable();
							fortable.setId(fortableList.get(i).getId());
							fortable.setLast(fortableList.get(i).getLast());
							fortable.setFirst(fortableList.get(i).getFirst());
							fortable.setLogin(fortableList.get(i).getLogin());
							fortable.setEmail(fortableList.get(i).getEmail());
							fortable.setCntC(fortableList.get(i).getCntC());
							fortable.setCntM(fortableList.get(i).getCntM());
							fortablels.add(fortable);
							break;
						}
					}
				}
				fortableList.get(i);
			}


			fortablel.setFortableList(fortablels);
			response = fortablel;
		}

		model.addAttribute(XML_ATTR_NAME, JaxbGsonUtils.ObjectToXML(response));
		return this.viewName;
	}

	/**
	 * Метод получения списка пользователей Департамента тестирования Back-End
	 * @param bper
	 * @param eper
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gettableallusersdata.dao", method = RequestMethod.GET, params = {})
	public String getTableAllUsersData(@RequestParam(required = true, value = "bper") String bper,
									   @RequestParam(required = true, value = "eper") String eper,
									   Model model) {

		Object response = null;
		//Выбор наших пользователей
		List<Users> usersList = this.usersDao.getAllUseresFromDepBackEnd();
		if (usersList.isEmpty()) {
			response = new ErrResponse("-1", "Нет данных по указанному фильтру");
		} else {
			String usersIdList = "";
			for (Users users : usersList) {
				usersIdList = usersIdList + users.getId() + ",";
			}
			usersIdList = usersIdList.substring(0, usersIdList.length() - 1);
			List<Tcversions> tcversionsList = this.tcversionsDao.getCntByMonth(usersIdList, bper, eper);
			if (tcversionsList.isEmpty()) {
				response = new ErrResponse("-1", "Нет данных по указанному фильтру");
			} else {
				Tcversionsl tcversionsl = new Tcversionsl();
				tcversionsl.setTcversionsList(tcversionsList);
				response = tcversionsl;
			}
		}
		model.addAttribute(XML_ATTR_NAME, JaxbGsonUtils.ObjectToXML(response));
		return this.viewName;
	}
}