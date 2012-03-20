package nu.pich.vucplace.shared.guestbook.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DayTest {

	@Test
	public void shouldCreateDayFromDate1() throws ParseException {
		// given
		Day expectedDay = new Day("2012", "01", "19");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse("2012-01-19");
		// when
		Day day = Day.fromDate(date);
		// then
		assertEquals(expectedDay, day);
	}

	@Test
	public void shouldCreateDayFromDate2() throws ParseException {
		// given
		Day expectedDay = new Day("2012", "11", "01");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse("2012-11-01");
		// when
		Day day = Day.fromDate(date);
		// then
		assertEquals(expectedDay, day);
	}

	@Test
	public void shouldBeSameDay() {
		// given
		Day day1 = new Day("2012", "10", "01");
		Day day2 = new Day("2012", "10", "01");
		// when
		boolean isSameDay = day1.isSameDay(day2);
		// then
		assertTrue(isSameDay);
	}

	@Test
	public void shouldNotBeSameDay() {
		// given
		Day day1 = new Day("2012", "10", "01");
		Day day2 = new Day("2012", "10", "02");
		// when
		boolean isSameDay = day1.isSameDay(day2);
		// then
		assertFalse(isSameDay);
	}

	@Test
	public void shouldBeAfter1() {
		// given
		Day day1 = new Day("2012", "10", "05");
		Day day2 = new Day("2012", "10", "01");
		// when
		boolean isAfter = day1.isAfter(day2);
		// then
		assertTrue(isAfter);
	}

	@Test
	public void shouldBeAfter2() {
		// given
		Day day1 = new Day("2012", "11", "01");
		Day day2 = new Day("2012", "10", "01");
		// when
		boolean isAfter = day1.isAfter(day2);
		// then
		assertTrue(isAfter);
	}

	@Test
	public void shouldBeAfter3() {
		// given
		Day day1 = new Day("2013", "10", "01");
		Day day2 = new Day("2012", "10", "01");
		// when
		boolean isAfter = day1.isAfter(day2);
		// then
		assertTrue(isAfter);
	}

	@Test
	public void shouldBeBefore1() {
		// given
		Day day1 = new Day("2012", "10", "01");
		Day day2 = new Day("2012", "10", "05");
		// when
		boolean isBefore = day1.isBefore(day2);
		// then
		assertTrue(isBefore);
	}

	@Test
	public void shouldBeBefore2() {
		// given
		Day day1 = new Day("2012", "10", "01");
		Day day2 = new Day("2012", "11", "01");
		// when
		boolean isBefore = day1.isBefore(day2);
		// then
		assertTrue(isBefore);
	}

	@Test
	public void shouldBeBefore3() {
		// given
		Day day1 = new Day("2012", "10", "01");
		Day day2 = new Day("2013", "10", "01");
		// when
		boolean isBefore = day1.isBefore(day2);
		// then
		assertTrue(isBefore);
	}

}
