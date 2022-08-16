package tradeit.shivani.shah.stocks.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

public class DateUtil {

	private DateUtil() {
	}
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static String formatDate(LocalDate date) throws DateTimeException {
		return date.format(formatter);
	}

	public static LocalDate parseDate(String date) throws DateTimeParseException {
		return LocalDate.parse(date, formatter);
	}

	public static String getCurrentDateInString() {
		return LocalDate.now().format(formatter);
	}

	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}
	
	public static List<String> fetchLastWeekDates() {
		int weekDays = 7;
		List<String> days = new LinkedList<>();
		for(int i=1; i<=weekDays; i++) {
			days.add(LocalDate.now().minusDays(i).format(formatter));
		}
		return days;
	}

}
