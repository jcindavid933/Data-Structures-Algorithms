// Implement a "bogus" sorting algorithm called bogo sort that uses your shuffling algorithm to sort an array of numbers.
// Using the System clock (milliseconds) to demonstrate the run-time complexity, O(N), O(N^2), O(N^N), O(1), etc...
// for the "bogus" sorting algorithm.

import java.util.*;
import java.io.*; 
import java.nio.file.*;

public class BogoSort {
	
// DATA FIELDS
	static final boolean DEBUG = true; 
	static final String FILENAME = "bogosort.txt";
	static final int TRIALS = 5; // 5 data points
	static final int ARRAYSIZE_LOW = 6; // size of the array to be sorted (low)
	static final int ARRAYSIZE_HIGH = 6; // size of the array to be sorted (high)
	static final int MAX_RANDOM_VALUE = 278; // This is the maximum range of value to be placed within array
	
	static long trialID;
	static long timeStart;
	static long timeStop;
	static long milliTimeDelta;
	static long nanoTimeDelta;

// METHODS
	public static void main(String[] args) {
		for (int i = 0; i < TRIALS; i++) {
			int[] bogoArray = generateArray();

// Start/stop timer
			startTimer();
			bogoArray = bogoSort(bogoArray);
			stopTimer();

// Testing
			System.out.print( (DEBUG) ? ("Sorted array: \n") : ("") );
			for (int x : bogoArray) {
				System.out.print( (DEBUG) ? (x + ", ") : ("") );
			}

// Write trial information
			appendToOutput(bogoArray);
		} 
	}

// Appends the relevant information from the trial run
	static void appendToOutput(int[] sortedArray) {
		String arrayContents = "" + sortedArray[0]; // Prints the first index value
		for (int i = 1 ; i < sortedArray.length ; i++) {
			arrayContents = arrayContents + (" - " + sortedArray[i]);
		}
		
		String outputString = trialID + ", " + sortedArray.length + ", " + nanoTimeDelta + ", " + arrayContents + "\n";
		try {
			Files.write(Paths.get(FILENAME), outputString.getBytes(), StandardOpenOption.APPEND);
		}

		catch (IOException e) { // when catching the exception	
			System.out.println("Error: File not found.");	
		}
	}
	
	static int[] bogoSort(int[] unsortedArray) {
		while (isNotSorted(unsortedArray)) {
			shuffle(unsortedArray);
		}
		int[] sortedArray = unsortedArray;
		System.out.print( (DEBUG) ? ("Sorted \n") : ("") );
		return (sortedArray);
	}

// Creates an array of numbers of size between ARRAYSIZE_HIGH and ARRAYSIZE_LOW
	static int[] generateArray() {
		Random randomizer = new Random();

		int size = randomizer.nextInt(ARRAYSIZE_HIGH - ARRAYSIZE_LOW + 1) + ARRAYSIZE_LOW;
		int[] returnArray = new int[size];
		System.out.print( (DEBUG) ? ("\n\nGenerated array length: " + returnArray.length + "\n") : ("") );
		
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = randomizer.nextInt(MAX_RANDOM_VALUE);
		}
		return (returnArray);
	}

// Checks if the received array is sorted.
	static boolean isNotSorted(int[] arrayToCheck) {
		if (arrayToCheck.length <= 2 ) { // Arrays of length 1 or 2 are sorted by default.
			return (false);
		}

		if (isSortedLowToHigh(arrayToCheck)) {
			return (false);
		}
		
		else if ( isSortedHighToLow(arrayToCheck) ){
			return (false);
		}

		else {
			return (true);
		}
	}

// Checks if a received array is sorted from high to low.
	static boolean isSortedHighToLow(int[] arrayToCheck ) {
		for (int i = 0;i < arrayToCheck.length - 1;i++) {
			if(arrayToCheck[i] < arrayToCheck[i + 1]) {
				return false;
			}
		}
		return true;
	}

// Checks if a received array is sorted from low to high
	static boolean isSortedLowToHigh(int[] arrayToCheck) {
		for (int i = 0 ; i < arrayToCheck.length - 1 ; i++) {
			if ( arrayToCheck[i] > arrayToCheck[i + 1] ) {
				return false;
			}
		}
		return true;
	}	

// Shuffles the values in the array
	static int[] shuffle(int[] unshuffledArray) {
		int index;
		int temp;
		Random random = new Random();

		for (int i = unshuffledArray.length - 1; i > 0; i--) { 	// Takes a random value from the unshuffled array
			index = random.nextInt(i + 1);
			temp = unshuffledArray[index];

			unshuffledArray[index] = unshuffledArray[i]; // moves it to the current iteration index
			unshuffledArray[i] = temp;
		}
		
		int[] reshuffledArray = unshuffledArray;
		return (reshuffledArray);
	}

// Sets the start point for the System timer
	static void startTimer() {
	System.out.print( (DEBUG) ? ("Starting timer\n") : ("") );
		trialID = System.nanoTime();
		timeStart = System.nanoTime();
		
	}

// Stops the System timer
	static void stopTimer() {
		System.out.print( (DEBUG) ? ("Stopping timer\n") : ("") );
		timeStop = System.nanoTime();
		nanoTimeDelta = timeStop - timeStart;
		milliTimeDelta = nanoTimeDelta / 1000000; // Divide by 1 000 000 for milliseconds
		System.out.print( (DEBUG) ? ("Elapsed nanoTime = " + nanoTimeDelta + "\n") : ("") );
		System.out.print( (DEBUG) ? ("Elapsed milliTimeDelta = " + milliTimeDelta + "\n") : ("") );
	}
}
// END OF CODE
