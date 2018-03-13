import java.util.Scanner;
import java.util.TreeSet;

public class CharCount {

	// DATA FIELDS
	private TreeSet<CharInt> charCompSet;
	private TreeSet<CharInt> countCompSet;
	private static TreeSet<CharInt> outputSet = new TreeSet<CharInt>(new SortByCount());

	// CONSTRUCTOR:
	public CharCount() {
		charCompSet = new TreeSet<CharInt>(new SortByChar());
		countCompSet = new TreeSet<CharInt>(new SortByCount());
	}

	// PARAMETERIZED CONSTRUCTORS:
	// Constructor used by external class. Generates a CharCount object using a Scanner for input.
	public CharCount(Scanner caughtScanner) {
		charCompSet = new TreeSet<CharInt>(new SortByChar());
		countCompSet = new TreeSet<CharInt>(new SortByCount());
		caughtScanner.useDelimiter("");
		while (caughtScanner.hasNext()) {
		// Snips off the first letter of the Scanner as a String, uppercase it, and convert it to a char.
			char randomChar = caughtScanner.next().toUpperCase().charAt(0);
			CharInt randomCharInt = new CharInt(randomchar, 0);
			charCompSet.add(randomCharInt);
			for (CharInt x : charCompSet) {
				if (x.getChar() == randomChar) {
					x.setCount(x.getCount()+1);
				}
			}
		}
		// Occurrence counts in hand, we can now populate the TreeSet that sorts by occurrence counts
		for (CharInt x : charCompSet) {
			countCompSet.add(new CharInt(x.getChar(), x.getCount()));
		}
	}
	// Count comparison set has been created at this point.
	
	// METHODS:
	public int size() {
		return(countCompSet.size());
	}

	// All calls to CharCount class objects to convert to Strings will reference this behavior.
	@Override
	public String toString() {
		// In the event that there are no characters to display
		if ( countCompSet.isEmpty() ) {
			return "{ }";
		}
		// If there are characters in the set:
		else {
			// Goal is "{ CHAR1=COUNT1, CHAR2=COUNT2 }"
			String stringOutput = "{ ";
			for (CharInt x : countCompSet) {
			// Append all String representations of the CharInt to the overall String.
				stringOutput = stringOutput + x + ", ";
			}
			StringBuilder someStringBuilder = new StringBuilder(stringOutput);
			someStringBuilder.setLength(someStringBuilder.length()-2);
			stringOutput = someStringBuilder.toString() + " }";
			return(stringOutput);
		}
	}

	// Returns the full TreeSet of CharInts contained within a CharCount object.
	public CharCount getCounts() {
		CharCount returnCollection = new CharCount();
		for ( CharInt x : countCompSet ) {
			returnCollection.countCompSet.add(new CharInt(x.getChar(), x.getCount()));
		}
		return (returnCollection);
	}

	// Returns the top or bottom values of the sorted set proportional to the received argument.
	// Returns the full set if the argument asks for more values than are present in the list.
	public CharCount getCounts(int randomInt) {
		CharCount returnCollection = new CharCount();
		outputSet.clear();
		for (CharInt x : countCompSet ) {
			outputSet.add(new CharInt(x.getChar(), x.getCount()));
		}
		// If positive:
		if (randomInt > 0 && randomInt <= countCompSet.size()) {
			for (int i = 0; i < randomInt; i++) {
				returnCollection.countCompSet.add(outputSet.pollFirst());
			}
		}
		// If negative
		else if (randomInt < 0 && Math.abs(randomInt) <= countCompSet.size()) {
			for (int i = randomInt; i < 0; i++) {
				returnCollection.countCompSet.add(outputSet.pollLast());
			}
		}
		// If the argument exceeds the size of the set:
		else if (Math.abs(randomInt) > countCompSet.size()) {
			returnCollection.countCompSet = outputSet;
		}
		return (returnCollection);
	}

	// Returns the integer count of the char
	public int getCounts(char theChar) {
		// Uppercase the requested letter
		char randomChar = Character.toUpperCase(theChar);
		for (CharInt x : countCompSet) {
			if (x.getChar() == randomChar) {
				return(x.getCount());
			}
		}
		return(0);
	}
}

// END OF CODE
