/**
 * A simple program to display the next available bus from Carleton.
 * 
 * @author Michael Stoneman
 * @version Last modified 2015-04-06
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class NorthfieldBus {
	private static LocalTime timeNow = LocalTime.now();
	// private static LocalTime timeNow = LocalTime.of(16,33);

	/**
	 * Fills an ArrayList with LocalTime objects corresponding to the bus times
	 * for the day.
	 * 
	 * @return Nothing. It alters a list.
	 */
	public static void dayCheck(ArrayList<LocalTime> busTimes) {
		LocalDate curDate = LocalDate.now();
		DayOfWeek isSunday = curDate.getDayOfWeek();

		if (isSunday.getValue() == 7) { // If it's Sunday,
			LocalTime t0 = LocalTime.of(13,22); // start at 1:22...
			busTimes.add(t0);
			for (int i = 0; i < 3; i++) { // ...and do this three times.
				busTimes.add(busTimes.get(i).plusMinutes(40));
			}
		} else {
			LocalTime t0 = LocalTime.of(16,22);
			busTimes.add(t0);
			for (int i = 0; i < 9; i++) {
				busTimes.add(busTimes.get(i).plusMinutes(40));
			}
		}
	}

	/**
	 * Computes the time until the next bus departs Carleton into town.
	 */
	public static int timeUntilTown(ArrayList<LocalTime> busTimes) {
		int i = 0;
		while (i < busTimes.size() && busTimes.get(i).compareTo(timeNow) != 1) {
			i++;
		} //loop breaks for smallest i that gives a 1 or after checking all.
		if (i >= busTimes.size()) { return -1; }
		int minsUntil = (int) timeNow.until(busTimes.get(i), MINUTES);
		if (minsUntil > 60) {
			return -1;
		} else {
			return minsUntil;
		}
	}

	/**
	 * Computes the time until the next bus departs Carleton for Olaf.
	 */
	public static int timeUntilOlaf(ArrayList<LocalTime> busTimes) {
		int i = 0;
		while (i < busTimes.size() && busTimes.get(i).plusMinutes(20).compareTo(timeNow) != 1) {
			i++;
		} //loop breaks for smallest i that gives a 1 or after checking all.
		if (i >= busTimes.size()) { return -1; }
		int minsUntil = (int) timeNow.until(busTimes.get(i).plusMinutes(20), MINUTES);
		if (minsUntil > 60) {
			return -1;
		} else {
			return minsUntil;
		}
	}

	public static void main(String[] args) {
		ArrayList<LocalTime> busTimes = new ArrayList<LocalTime>();
		dayCheck(busTimes);
		if (args[0].equals("town")) {
			int untilTown = timeUntilTown(busTimes);
			if (untilTown == -1) {
				System.out.println("--");
			} else {
				System.out.println(untilTown);
			}	
		}
		if (args[0].equals("olaf")) {
			int untilOlaf = timeUntilOlaf(busTimes);
			if (untilOlaf == -1) {
				System.out.println("(Not running to Olaf now.)");
			} else {
				System.out.println("(" + untilOlaf + " minutes for bus to Olaf.)");
			}	
		}
		
	}
}