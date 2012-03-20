package nu.pich.vucplace.shared.guestbook.domain;

import java.io.Serializable;
import java.util.Date;

public class Day implements Serializable {

	private static final long serialVersionUID = 1L;

	private String year;
	private String monthOfYear;
	private String dayOfMonth;

	public Day(String year, String monthOfYear, String dayOfMonth) {
		this.year = year;
		this.monthOfYear = monthOfYear;
		this.dayOfMonth = dayOfMonth;
	}

	Day() {

	}

	@SuppressWarnings("deprecation")
	public static Day fromDate(Date date) {
		if (date == null) {
			return null;
		}
		return new Day("" + (date.getYear() + 1900), "" + twoDigits((date.getMonth() + 1)), "" + twoDigits(date.getDate()));
	}

	private static String twoDigits(int num) {
		return (num < 10 ? "0" : "") + num;
	}

	public String getYear() {
		return year;
	}

	public String getMonthOfYear() {
		return monthOfYear;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	@SuppressWarnings("deprecation")
	public Date toDate() {
		return new Date(Integer.parseInt(year) - 1900, Integer.parseInt(monthOfYear) - 1, Integer.parseInt(dayOfMonth), 12, 0, 0);
	}

	public boolean isSameDay(Day day) {
		return (day.getYear().equals(year) && day.getMonthOfYear().equals(monthOfYear) && day.getDayOfMonth().equals(dayOfMonth));
	}

	public boolean isAfter(Day day) {
		boolean isAfter = false;

		if (i(year).equals(i(day.getYear()))) {

			if (i(monthOfYear).equals(i(day.getMonthOfYear()))) {

				if (i(dayOfMonth).equals(i(day.getDayOfMonth()))) {
					isAfter = false;

				} else if (i(dayOfMonth) > i(day.getDayOfMonth())) {
					isAfter = true;
				}

			} else if (i(monthOfYear) > i(day.getMonthOfYear())) {
				isAfter = true;
			}

		} else if (i(year) > i(day.getYear())) {
			isAfter = true;
		}

		return isAfter;
	}

	public boolean isBefore(Day day) {
		boolean isBefore = false;

		if (i(year).equals(i(day.getYear()))) {

			if (i(monthOfYear).equals(i(day.getMonthOfYear()))) {

				if (i(dayOfMonth).equals(i(day.getDayOfMonth()))) {
					isBefore = false;

				} else if (i(dayOfMonth) < i(day.getDayOfMonth())) {
					isBefore = true;
				}

			} else if (i(monthOfYear) < i(day.getMonthOfYear())) {
				isBefore = true;
			}

		} else if (i(year) < i(day.getYear())) {
			isBefore = true;
		}

		return isBefore;
	}

	private Integer i(String string) {
		return Integer.parseInt(string);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayOfMonth == null) ? 0 : dayOfMonth.hashCode());
		result = prime * result + ((monthOfYear == null) ? 0 : monthOfYear.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		if (dayOfMonth == null) {
			if (other.dayOfMonth != null)
				return false;
		} else if (!dayOfMonth.equals(other.dayOfMonth))
			return false;
		if (monthOfYear == null) {
			if (other.monthOfYear != null)
				return false;
		} else if (!monthOfYear.equals(other.monthOfYear))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return year + "-" + monthOfYear + "-" + dayOfMonth;
	}
}
