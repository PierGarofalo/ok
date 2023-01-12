package it.studenti.uniba.utility;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.studenti.uniba.entity.Noleggio;

public class DataUtility {

	/**
	 * checkDate verifica se sono passati più di 30 giorni dal noleggio di un
	 * computer. restituisce True se sono passati più di 30 giorni, altrimenti False
	 **/
	public boolean checkDate(Date todayDate, int rentDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.add(Calendar.DATE, -rentDate);
		cal.getTime();
		if (Calendar.DATE - rentDate >= 30) {
			return false;
		} else {
			return true;
		}
	}
	
	public int convertDateINT(Date todayDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.getTime();
		return cal.getTime().getDate();
	}

	public int differenceDate(Date todayDate, int rentDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.add(Calendar.DATE, -rentDate);
		cal.getTime();
		return cal.getTime().getDate();
	}
}
