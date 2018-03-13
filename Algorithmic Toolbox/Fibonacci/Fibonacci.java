import java.math.BigInteger;

public class Fibonacci{
	public static void main(String[] args){
		int test = 45;
		BigInteger test2 = new BigInteger("45"); // in case of overload
		System.out.println(theBigFib(test)); // a fast recursive version
		System.out.println(theBigFib(test2)); // overload
		System.out.println(fibonacci(test)); // slow version
	}
	
	public static in fibonacci(int n){
		if (n<=2) {
			return 1;
		}
		else {
			return fibonacci(n-1) + fibonacci(n-2);
		}
	}
	
	// Method that accepts an integer representing the recursion depth
	public static BigInteger theBigFib(int n) {
		BigInteger theNumber = BigInteger.valueOf(n);
		return (theBigFib(theNumber));
	}

	// theBigFib method accepts a BigInteger and performs the initial setup of the Fibonacci sequence
	public static BigInteger theBigFib(BigInteger n) {
		if (n.intValue() <= 2) {
			// Returns 1 as a BigInteger.
			BigInteger defaultReturn = new BigInteger("1");
			return (defaultReturn);
		}
		else {
			BigInteger last = new BigInteger("1");
			BigInteger last2 = new BigInteger("1");
			// Subtract 2 layers to represent the 2 seed values of the sequence
			return(theBigFibHelper(n.intValue()-2, last, last2));
		}
	}

	// A Helper Method calculates the Fibonacci value.
	public static BigInteger theBigFibHelper(int depth, BigInteger last, BigInteger last2) {
		// In the case that the sequence runs out of depth
		if (depth == 0) {
			// Return the last calculated Fibonacci value
			return (last);
		}
		// Get the new current value and the new prior value
		BigInteger newLast2 = last;
		BigInteger current = last.add(last2);
		return (theBigFibHelper(depth-1, current, newLast2));
	}
}
// END OF CODE
