package com.springapp.mvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Slame on 07.09.2015.
 */
public class DateUtils {
	/**
	 * возвращает первый день месяца
	 * @param date дата
	 * @return
	 */
	public static Date getLoMonhtDate(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	/**
	 * возвращает последний день месяца
	 * @param date дата
	 * @return
	 */
	public static Date getHiMonhtDate(Date date)
	{
		return decDateDay( incDateMonth( getLoMonhtDate(date), 1) ,1 );
	}

	/**
	 * увеличивает дату на заданное количество дней
	 * @param date дата
	 * @param cnt насколько увеличить
	 * @return
	 */
	public static Date incDateDay(Date date, Integer cnt)
	{
		return incDecDate(date, Calendar.DAY_OF_MONTH, cnt);
	}

	/**
	 * уменьшает дату на заданное количество дней
	 * @param date дата
	 * @param cnt насколько уменьшить
	 * @return
	 */
	public static Date decDateDay(Date date, Integer cnt)
	{
		return incDecDate(date, Calendar.DAY_OF_MONTH, -cnt);
	}

	/**
	 * увеличивает дату на заданное количество месяцев
	 * @param date дата
	 * @param cnt насколько увеличить
	 * @return
	 */
	public static Date incDateMonth(Date date, Integer cnt)
	{
		return incDecDate(date, Calendar.MONTH, cnt);
	}

	/**
	 * уменьшает дату на заданное количество месяцев
	 * @param date дата
	 * @param cnt насколько уменьшить
	 * @return
	 */
	public static Date decDateMonth(Date date, Integer cnt)
	{
		return incDecDate(date, Calendar.MONTH, -cnt);
	}

	/**
	 * увеличивает дату на заданное количество лет
	 * @param date дата
	 * @param cnt насколько увеличить
	 * @return
	 */
	public static Date incDateYear(Date date, Integer cnt)
	{
		return incDecDate(date, Calendar.YEAR, cnt);
	}

	/**
	 * уменьшает дату на заданное количество лет
	 * @param date дата
	 * @param cnt насколько уменьшить
	 * @return
	 */
	public static Date decDateYear(Date date, Integer cnt)
	{
		return incDecDate(date, Calendar.YEAR, -cnt);
	}

	/**
	 * уменьшает/увеличивает дату на заданное количество ...
	 * @param date дата
	 * @param cnt насколько уменьшить
	 * @param optInc - что увеличиваем? (Calendar.YEAR, Calendar.MONTH...)
	 * @return
	 */
	private static Date incDecDate(Date date, int optInc, Integer cnt)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(optInc, c.get(optInc) + cnt);
		return c.getTime();
	}

	/**
	 * получение даты из строки по маске.
	 * @param date дата
	 * @param format маска (к примеру DD.MM.YYYY)
	 * @return
	 */
	public static Date getDateFromStringByMask(String date, String format) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(date);
	}

	/**
	 * получение строки из даты по маске.
	 * @param date дата
	 * @param format маска ((к примеру DD.MM.YYYY))
	 * @return
	 */
	public static String getStringFromDateByMask(Date date, String format) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
}
