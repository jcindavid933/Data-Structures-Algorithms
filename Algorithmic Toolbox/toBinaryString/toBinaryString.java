// Without using the Integer Class and using recursion

public static String toBinaryString(int n) {
	return (n == 0) ? ("0") : ((n/2 != 0) ? (toBinaryString(n/2) + (n%2)) : ("" + n));
}
