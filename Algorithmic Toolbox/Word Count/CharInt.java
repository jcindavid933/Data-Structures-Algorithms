import java.util.*;
public class CharInt {
	// Data fields
	private char thisChar; // The ASCII character
	private int  occurrenceCount; // The count of randomChar

	// Default constructor:
	public CharInt() {
	}

	// Parameterized constructors:
	public CharInt(char randomChar, int occurrence) {
		thisChar = randomChar;
		occurrenceCount = occurrence;
	}

	// Methods:
	// Overriding the abstract toString() method
	@Override // Denotes that we are overriding the default toString() method
	public String toString() {
		return("" + thisChar + "=" + occurrenceCount);
	}

	// Accessors / Getters:
	public char getChar() {
		return (thisChar);
	}

	public int getCount() {
		return (occurrenceCount);
	}

	// Mutators / Setters:
	public void setChar(char charToSet) {
		thisChar = charToSet;
	}

	public void setCount(int occurToSet) {
		occurrenceCount = occurToSet;
	}
}

class SortByChar implements Comparator<CharInt> {
	@Override
	public int compare(CharInt ci1, CharInt ci2) {
		// Subtracts two chars from each other
		return (ci1.getChar() - ci2.getChar()); // Sets the natural order from A to Z
	}
}

class SortByCount implements Comparator<CharInt>{
	// Lower occurrenceCount goes first
	@Override
	public int compare(CharInt ci1, CharInt ci2) {
		// Subtracts the occurrence counts of the two CharInts.
		if(ci1.getCount() > ci2.getCount()){
			return(1); // Greater than
		}
		// In the event that two things have the same count:
		// The lower letter comes first
		else if (ci1.getCount() == ci2.getCount()) {
			if (ci1.getChar() > ci2.getChar())
				return(1); // Greater than if the counts are equal (alphabetical)
			else {
				return(-1);
			}
		}
		else {
			return(-1); // Less than
		}
	}
}
