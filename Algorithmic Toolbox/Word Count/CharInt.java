import java.util.Comparator;

public class CharInt implements Comparable<CharInt>, Comparator<CharInt> {
	// FIELDS
  private int count;
	private char ascii;

	// CONSTRUCTORS
	public CharInt () {
		this(0,(char)0);
	}

	public CharInt(int n, char c) {
		count = n;
		ascii = c;
	}

	// for Comparable interface
	public int compareTo(CharInt other) {
		if(count > other.count)
			return 1;
		else if (count < other.count)
			return -1;
		else { // counts are the same
			return compare(this, other);
		}
	}

	// ACCESSORS
	public String toString() {
		return ascii +"="+ count;
	}

	public char getChar() {
		return ascii;
	}

	public int getCount() {
		return count;
	}

	// only one extra method for the comparator
	public int compare(CharInt one, CharInt two) {
		return one.ascii - two.ascii;
	}

	// MODIFIERS
	public void setChar(char c) {
		ascii = c;
	}

	public void setCount(int n) {
		count = n;
	}

	public void addOne() {
		count++;
	}
}
