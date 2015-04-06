/**
 * A simple program to display the next available bus from Carleton.
 * @author Michael Stoneman
 * @version Last modified 2015-04-06
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class NorthfieldBus {
	// Conveniently, the default length of ArrayList is 10.
	private ArrayList<LocalTime> busTimes = new ArrayList<LocalTime>();
	private LocalTime t0 = null;

	public void dayCheck(ArrayList<LocalTime> busTimes) {
		LocalDate curDate = LocalDate.now();
		DayOfWeek isSunday = curDate.getDayOfWeek();

		if (isSunday.getValue() == 7) { // If it's Sunday,
			t0 = LocalTime.of(1,22); // start at 1:22...
			busTimes.add(t0);
			for (int i = 0; i < 3; i++) { // ...and do this three times.
				busTimes.add(busTimes.get(i).plusMinutes(40));
			}
		} else {
			t0 = LocalTime.of(4,22);
			busTimes.add(t0);
			for (int i = 0; i < 9; i++) {
				busTimes.add(busTimes.get(i).plusMinutes(40));
			}
		}
	}

	public void printList(ArrayList<LocalTime> aList) {
		for (int i = 0; i < aList.size(); i++) {
			System.out.println(aList.get(i).toString());
		}
	}

	public static void main(String[] args) {
		dayCheck(busTimes);
		printList(busTimes);
	}
}