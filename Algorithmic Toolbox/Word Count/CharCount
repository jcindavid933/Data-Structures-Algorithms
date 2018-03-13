import java.util.Scanner;
import java.util.TreeSet;

public class CharCount {

// Data Fields
// Establishes the three sets of data
	private TreeSet<CharInt> charSet;
	private TreeSet<CharInt> countSet;
	private static TreeSet<CharInt> outputSet = new TreeSet<CharInt>(new CharInt());

// Default Constructor
	public CharCount() {
		charSet = new TreeSet<CharInt>(new CharInt());
		countSet = new TreeSet<CharInt>(new CharInt());
	}

// Parameterized Constructor
	public CharCount(Scanner caughtScanner) {
		charSet = new TreeSet<CharInt>(new CharInt());
		countSet = new TreeSet<CharInt>(new CharInt());
		caughtScanner.useDelimiter("");
		while (caughtScanner.hasNext()) {
// Takes off the first letter of the Scanner as a String, makes it an uppercase, and then converts it to a char
			char randomChar = caughtScanner.next().toUpperCase().charAt(0);
			CharInt randomCharInt = new CharInt(0, randomChar);
// Attempt to add a new CharInt to the set
			charSet.add(randomCharInt);
// If there is a match, it increments that CharInt occurrenceCounter by 1.
			for (CharInt x : charSet) {
				if (x.getChar() == randomChar) {
					x.setCount(x.getCount()+1);
				} 
			}
		} 
		
// We can now populate the TreeSet that sorts by occurrence counts
		for (CharInt x : charSet) {
			countSet.add(new CharInt(x.getCount(), x.getChar()));
		}
	}
	
// Methods
	public int size() {
		return(countSet.size());
	}

	@Override
	public String toString() {
// In the event that there are no characters to display
		if (countSet.isEmpty()) {
			return "{ }";
		}
// If there are characters in the set
		else {
			String stringOutput = "{ ";
			for (CharInt x : countSet) {
// Append all String representations of the CharInt to the overall String
				stringOutput = stringOutput + x + ", ";
			}
			StringBuilder randomStringBuilder = new StringBuilder(stringOutput);
// Reduce the String length by 2.
			randomStringBuilder.setLength(randomStringBuilder.length()-2);
// Add the closing curly brace to the string
			stringOutput = randomStringBuilder.toString() + " }";
			return(stringOutput);
		}
	}

// Returns the full TreeSet of CharInts contained within a CharCount object
	public CharCount getCounts() {
		CharCount returnCollection = new CharCount();
// Reads fields from the database and uses this to construct a new CharCount object
		for ( CharInt x : countSet ) {
			returnCollection.countSet.add(new CharInt(x.getCount(), x.getChar()));
		}
		return (returnCollection);
	}

// Returns the top or bottom values of the sorted set proportional to the received argument
	public CharCount getCounts(int someInt) {
		CharCount returnCollection = new CharCount();
// First, clear the scratchpad
		outputSet.clear();
// Then populate the scratchpad with new CharInts
		for (CharInt x : countSet ) {
			outputSet.add(new CharInt(x.getCount(), x.getChar()));
		}
// If postive:
		if (someInt > 0 && someInt <= countSet.size()) {
			for (int i = 0; i < someInt; i++) {
				returnCollection.countSet.add(outputSet.pollFirst());
			}
		}
// If negative:
		else if (someInt < 0 && Math.abs(someInt) <= countSet.size()) {
			for (int i = someInt; i < 0; i++) {
				returnCollection.countSet.add(outputSet.pollLast());
			}
		}
// If the argument exceeds the size of the set
		else if (Math.abs(someInt) > countSet.size()) {
			returnCollection.countSet = outputSet;
		}
		return (returnCollection);
	}
	
// Parameterized getCount method
// Returns the integer count of the char
	public int getCounts(char theChar) {
// Uppercase the requested letter
		char randomChar = Character.toUpperCase(theChar);
// Iterate over all CharInts
		for (CharInt x : countSet) {
			if (x.getChar() == randomChar) {
// Return the count of the char that matches
				return(x.getCount());
			}
		}
// Otherwise
		return(0);
	}
}

// END OF CODE
