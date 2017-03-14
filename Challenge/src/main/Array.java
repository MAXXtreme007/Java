package main;

public class Array {
	public static void main(final String[] args) {
		// declares an array of integers
        int[] Array;

        // allocates memory for 10 integers
        Array = new int[10];
           
        // initialize elements
        Array[0] = 100;
        Array[1] = 200;
        Array[2] = 300;
        Array[3] = 400;
        Array[4] = 500;
        Array[5] = 600;
        Array[6] = 700;
        Array[7] = 800;
        Array[8] = 900;
        Array[9] = 1000;
        
        // display numerical value for each element
        System.out.println("Element at index 0: " + Array[0]);
        System.out.println("Element at index 1: " + Array[1]);
        System.out.println("Element at index 2: " + Array[2]);
        System.out.println("Element at index 3: " + Array[3]);
        System.out.println("Element at index 4: " + Array[4]);
        System.out.println("Element at index 5: " + Array[5]);
        System.out.println("Element at index 6: " + Array[6]);
        System.out.println("Element at index 7: " + Array[7]);
        System.out.println("Element at index 8: " + Array[8]);
        System.out.println("Element at index 9: " + Array[9]);
	}
}