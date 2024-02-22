package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {
	
	//total seconds
	
	@Test
	public void testGetTotalSecondsGood()
	{
	int totalseconds =
	Time.getTotalSeconds("05:05:05:00");
	assertTrue("The seconds were not calculated properly", totalseconds==18305);

	}
	@Test
	public void testGetTotalSecondsBad()
	{
	assertThrows(
	StringIndexOutOfBoundsException.class,
	()-> {Time.getTotalSeconds("10:00");});
	}

	//seconds

	@ParameterizedTest
	@ValueSource(strings = {"01:01:01:00", "01:01:01:30", "01:01:01:59", "01:01:01:01", "01:01:01:58"})
	void testGetSeconds(String candidate) {
		int seconds = Time.getSeconds(candidate);
		assertTrue("The seconds were not calculated properly", seconds >= 0 && seconds < 60);
	}
	

	
	@Test
	void testGetSecondsBad() {
	    assertThrows(NumberFormatException.class, () -> Time.getSeconds("01:01:av:01"));
	}

	//milliseconds
	
	@ParameterizedTest
	@ValueSource(strings = {"01:01:01:00", "01:01:01:59"})
	void testGetMilliSeconds(String candidate) {
		int milliSeconds = Time.getMilliSeconds(candidate);
		assertTrue("The milli seconds were not calculated properly", milliSeconds >= 0 && milliSeconds < 60);
	}
	
	@Test
	void testGetMilliSecondsBad() {
	    assertThrows(NumberFormatException.class, () -> Time.getMilliSeconds("01:01:01:xx"));
	}
	
	//minutes

	@ParameterizedTest
	@ValueSource(strings = {"01:00:00:00", "01:30:00:00", "01:59:59:59"}) // Includes boundary cases
	void testGetTotalMinutes(String candidate) {
	    int minutes = Time.getTotalMinutes(candidate);
		assertTrue("The seconds were not calculated properly", minutes >= 0 && minutes < 60);

	}

	@Test
	void testGetTotalMinutesBad() {
	    assertThrows(NumberFormatException.class, () -> Time.getTotalMinutes("01:xx:01:01"));
	}

	//totalhours

	@ParameterizedTest
	@ValueSource(strings = {"00:00:00:00", "01:00:00:00", "23:00:00:00"})
	void testGetTotalHours(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly", hours >= 0 && hours <= 23);
	}

	@Test
	void testGetTotalHoursBad() {
		assertThrows(
				StringIndexOutOfBoundsException.class,
				()-> {Time.getTotalHours("2");});
	}

}
