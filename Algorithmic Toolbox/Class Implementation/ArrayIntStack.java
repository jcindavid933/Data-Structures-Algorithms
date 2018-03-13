import java.util.*;

/**
 * The <code>ArrayIntStack</code> class represents a "Last-In-First-Out" (LIFO) stack of objects.
 * It utilizes an 'int' array ( <code>int[]</code> ) with methods that allow the array to be
 * accessed as a stack.
 * <p>
 * Primarily, <code>push()</code>, <code>peek()</code>, and <code>pop()</code> methods are provided
 * to allow users to operate on the head of the ArrayIntStack.
 * <p>
 * After initiation, the ArrayIntStack will be empty, and constructors will be provided to immediately populate
 * the ArrayIntStack with 'int' values.
 * <p>
 *
 * @author  Wonsik Nam
 * @since   November 7th, 2017
 */

public class ArrayIntStack implements Iterable<Integer> {
/**
 * The default maximum capacity of the <code>ArrayIntStack</code>.
 */
		
	public static final int DEFAULT_MAX_CAPACITY = 20; // Maximum capacity of the stack
			
	/**
	 * An array representing the internal <code> int </code> stack.
	 */
			
	private int[] thisStack; // Array representing the integer stack
	private int size = 0; // Current number of elements in the stack

	// DEFAULT CONSTRUCTOR - Refers to ArrayIntStack(int) constructor, passing the maximum capacity as an argument
	/**
	 * Creates an empty <code>ArrayIntStack</code>, deferring to <code>ArrayIntStack(int)</code> constructor, passing the max capacity as an argument.
	 */
	public ArrayIntStack() {	
		this (DEFAULT_MAX_CAPACITY);
	}

	private ArrayIntStack(int x) {		
	// Catches case exception for negative stack sizes
		if (x < 0) {			
			throw new IllegalArgumentException("Capacity must be at least 0: " + size);
		}
		thisStack = new int[x];
	}

	// Constructor for collections of objects
	/**
	 * Creates a loaded <code>ArrayIntStack</code>, deferring to <code>ArrayIntStack(int)</code> constructor, passing the max capacity or the element length.
	 * @param variable: A variable number of integer parameters.
	 * 
	 */
	public ArrayIntStack(int... variable) {			
	// Construct an ArrayIntStack with either twice the received argument length or the default maximum, whichever is larger		
		this(Math.max(DEFAULT_MAX_CAPACITY, variable.length * 2));
		for (int n : variable) {	
			add(n);		
		}
	}
	
	/**
	 * Creates a loaded <code>ArrayIntStack</code>, deferring to the <code>ArrayIntStack(int)</code> constructor, passing the max capacity or twice the parameter length.
	 * @param variable: A variable number of integer parameters.
	 * @return stack: An ArrayIntStack loaded with all integers contained within variable
	 */
	public static ArrayIntStack withValues(int... variable) {			
		ArrayIntStack stack = new ArrayIntStack(Math.max(DEFAULT_MAX_CAPACITY, variable.length * 2));	
		for (int n : variable) {			
			stack.add(n);		
		}
		return (stack);
	}
	
	/**
	 * Creates an <code>ArrayIntStack</code> loaded with the passed parameter element, deferring to <code>ArrayIntStack(int)</code> constructor and utilizes add method after initial stack creation.
	 *
	 * @param element: Integer used to seed a new ArrayIntStack.
	 */
	public ArrayIntStack(int element, boolean notCapacity) {				
	// Default constructor
		this();
		add(element);
	}
	
	/**
	 * Creates a loaded <code>ArrayIntStack</code>, deferring to <code>ArrayIntStack(int)</code> constructor, passing the max capacity or twice the parameter length.
	 * 
	 * @param elements: The collection of integers used to make a new ArrayIntStack.
	 */
	public ArrayIntStack(Collection<Integer> elements) {
	// Constructor that receives one int argument
		this(Math.max(DEFAULT_MAX_CAPACITY, elements.size() * 2));
		for (int n : elements) {
			add(n);
		}
	}	
	
	/**
	 * Creates a loaded <code>ArrayIntStack</code>, deferring to <code>ArrayIntStack(int)</code> constructor, passing the max capacity or twice the parameter length.
	 * @param stack: The ArrayIntStack used to create a new ArrayIntStack
	 * 
	 */
	public ArrayIntStack(ArrayIntStack stack) {
		this(Math.max(DEFAULT_MAX_CAPACITY, stack.size() * 2));
		addAll(stack);
	}

	// METHODS:
	/**
	 * Adds the passed parameter to the head of the <code>ArrayIntStack</code>, deferring to <code>add(int, int)</code>
	 * @param value: The <code>int</code> value to add to the <code>ArrayIntStack</code>.
	 */
	public void add(int value) {
		add(size, value);
	}

	/**
	 * Adds the value at the given index, where the index is the head of the <code>ArrayIntStack</code>.
	 * @param index: The index to insert the value at head of the stack.
	 * @param value: The integer value to insert.
	 *
	 */
	public void add(int index, int value) {
		checkIndex(index, 0, size);
		ensureCapacity(size + 1);
		thisStack[index] = value;
		size++;
	}
			
	/**
	 * Appends all values from a passed argument <code>ArrayIntStack</code> to the end of the current <code>ArrayIntStack</code>.
	 * @param stack1: The index to insert the value at (head of the stack by default)
	 *
	 */
	public void addAll(ArrayIntStack stack1) {
		for (int i = 0; i < stack1.size; i++) {
			add(stack1.thisStack[i]);
		}
	}

	// Verifies that the passed index argument is within the bounds
	private void checkIndex(int index) {
		checkIndex(index, 0, size - 1);
	}

	// Verifies that the passed index argument is within the bounds
	private void checkIndex(int index, int min, int max) {		
	// If the index is below the minimum or above the maximum
		if (min > index || max < index) {
			throw new ArrayIndexOutOfBoundsException("Illegal index " + index + "; must be between " + min + " and " + max + "\n"
				+ "ArrayIntStack : " + toString() + " (size=" + size + ")\n"
				+ "Capacity      : " + Arrays.toString(thisStack) + " (capacity=" + thisStack.length + ")");
		}
	}

	// Clears stack
	/**
	 * Clears the <code>ArrayIntStack</code> by resetting the '<code>size</code>' field to '0'.
	 *
	 */
	public void clear() {
		size = 0;
	} 

	// Returns true if the size of the stack is 0
	/**
	 * Reports if the <code>ArrayIntStack</code> is empty, returning <code>true</code> if it is ('<code>size</code>' equals '0') and <code>false</code> if not.
	 *
	 * @return: <code>true</code> if the stack holds no integers. Otherwise, method returns <code>false</code>.
	 */
	public boolean empty() {		
		if (size == 0) {	
			return (true);	
		}		
		return (false);
	}

	// Checks capacity against size and transfers array to a larger array if necessary
	/**
	 * Modifies the capacity of the internal array to always exceed the passed parameter.
	 * @param capacity: The minimum capacity of the ArrayIntStack to match or exceed.
	 */
	public void ensureCapacity(int capacity) {
	// If the capacity exceeds the stack length
		if (capacity > thisStack.length) {		
			int newCapacity = thisStack.length * 2 + 1;
			if (capacity > newCapacity) {
				newCapacity = capacity;
			}
			int[] newStack = new int[newCapacity];
			// Copy values across
			for (int i = 0; i < size; i++) {
				newStack[i] = thisStack[i];
			}
			thisStack = newStack;
		}
	}

	// Generates a new IntStackIterator, passing this ArrayIntStack as the argument
	/**
	 * Returns the index of the first occurrence of the passed <code>int</code> parameter or <code>-1</code> if no such element exists.
	 *
	 * @return IntStackIterator: An IntStackIterator instantiated with this <code>ArrayIntStack</code> as its constructor argument.
	 */
	@Override
	// Return type must match handle data type
	public IntStackIterator iterator() {
		return (new IntStackIterator(this));
	}

	// Looks at the object at the top of this stack without removing it from the stack
	/**
	 * Returns the value at the top of the <code>ArrayIntStack</code> without removing it.
	 *
	 * @return int: The object at the top of this <code>ArrayIntStack</code> (the last item of the <code>ArrayIntStack</code> object).
	 * @throws EmptyStackException: if this <code>ArrayIntStack</code> is empty.
	 */
	public int peek() {
	// Checks for empty stack
		if (size() == 0) {
			throw new EmptyStackException();
		}
		return (get(size() - 1));
	}

	// Removes the object at the top of this stack and returns that object as the value of this function
	/**
	 * Removes and returns the object at the top of the <code>ArrayIntStack</code>.
	 *
	 * @return returnValue: The object at the top of the <code>ArrayIntStack</code> (the last item of the <code>Vector</code> object).
	 * @throws EmptyStackException: If this <code>ArrayIntStack</code> is empty.
	 */
	public int pop() {
	// Store the value at the head of the stack
		int returnValue = peek();
		// Remove the head of the stack
		remove(size()-1);
		// Return the previous head
		return (returnValue);
	}

	// Adds the argument to the top of the stack
	/**
	 * Pushes an item onto the top of this stack, with identical behavior to the method 'ArrayIntStack.add()'.
	 *
	 * @param n: The integer to be pushed to the stack.
	 * @return n: The integer pushed to the stack.
	 * @throws StackOverflowError: if the size of the array exceeds 20 elements.
	 */
	public int push(int n) {
		if (size == 21) {
			throw new StackOverflowError("'Size' may not exceed 20 elements.");
		}
		add(n);
		return (n);
	}

	// toString() method
	/**
	 * Generates and returns a String representation of the ArrayIntStack contents
	 *
	 * @return String: A square-bracketed, comma-separated representation of the ints within the ArrayIntStack.
	 */
	public String toString() {
		String returnString = "[";
		// Iterating over the stack
		for (int i = 0 ; i < size ; i++){			
			if (i > 0){
				returnString = returnString + ", ";
			}
			// Append the stack value at the i'th index to the returnString
			returnString = returnString + thisStack[i];
		}
		// Append the final closing bracket
		returnString = returnString + "]";
		return(returnString);
	}

	// ACCESSORS/GETTERS:
	// pre: 0 <= index < size()
	/**
	 * Confirms a value exists and returns the int value at a given index.
	 *
	 * @param index: The index of the ArrayIntStack to query.
	 * @return Int: The integer stored at the passed index.
	 * @throws ArrayIndexOutOfBoundsException: if the passed index does not exist.
	 */
	public int get(int index) {
		checkIndex( index );
		return (thisStack[index]);
	}
			
	/**
	 * Returns an integer representation of the capacity of this ArrayIntStack.
	 *
	 * @return Int: The capacity of this ArrayIntStack.
	 */
	public int getCapacity() {
		return thisStack.length;
	}

	/**
	 * Returns the size of the ArrayIntStack.
	 *
	 * @return Int: The utilized size of the ArrayIntStack.
	 */
	public int size() {
		return size;
	}

	// MUTATORS/SETTERS:
	/**
	 * Confirms a value exists and returns the int value at a given index.
	 *
	 * @param index: The index of the ArrayIntStack to remove.
	 * @throws IllegalArgumentException: If remove method is called anywhere besides the head of the ArrayIntStack.
	 */
	public void remove(int index) {
		checkIndex(index);
		if (index != size-1) {
			throw new IllegalArgumentException("Only the head of the ArrayIntStack may be removed.");
		}
		// Reset the value at the index to default (0)
		thisStack[index] = 0;
		// Decrement the size by one
		size--;
	}

	//ADDITIONAL CLASSES:
	/**
	 * The <code>IntStackIterator</code> class, which implements <code>Iterator</code>, provides methods and behaviors
	 * used by the Iterator class to function properly.
	 * <p>
	 * Principally, <code>hasNext()</code>, <code>next()</code>, and <code>remove()</code> methods are provided
	 * to allow the Iterator object to "move" across the IntStackIterator's internal ArrayIntStack.
	 * <p>
	 *
	 * @author  Wonsik Nam
	 * @date   November 7th, 2017
	 */
	public static class IntStackIterator implements Iterator<Integer> {
		private ArrayIntStack stack1; // list to iterate over
		public int position; // current index position within the list
		private boolean removeOK; // whether it's okay to remove 

	/**
	 * Stores the received ArrayIntStack to the internal ArrayIntStack and sets the current index position to the head
	 *
	 * @param someStack: The ArrayIntStack passed as a parameter.
	 */
	public IntStackIterator(ArrayIntStack randomStack) {
		this.stack1 = randomStack;
		position = randomStack.size-1;
		removeOK = false;
	}

	/**
	 * Returns true if the current position is not at the foot of the ArrayIntStack. Returns false if not.
	 *
	 */
	public boolean hasNext() {
		if (position >= 0) {
			return (true);
		}
		else {
			return (false);
		}
	}

	/**
	 * Returns the value at the next index in the internal ArrayIntStack.
	 *
	 * @return result: The integer stored at the given index of the ArrayIntStack.
	 * @throws NoSuchElementException: If there is no next element to be returned.
	 */
	public Integer next() {
		if (!hasNext())
			throw new NoSuchElementException();
			int result = stack1.get(position);
			position--;
			removeOK = true;
			return result;
	}
			
	/**
	 * If the current position indicates an index which exists and the removal flag is set to true, remove the value at the head of the ArrayIntStack iterator.
	 * 
	 * @throws IllegalStateException: If the removal flag is not currently set to true the next method must be called.
	 */
	public void remove() {
		if (!removeOK)
			throw new IllegalStateException();
			stack1.remove(position - 1);
			position--;
			removeOK = false;
		}
	}
}

// END OF CODE
