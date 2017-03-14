package ProbabilityGUI;

import java.util.Scanner;

public class Presentation {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Probability coinFlips = new Probability();

		while (coinFlips.getNumberOfFlips() == 0) {
			System.out.println("Please enter number of flips");
			try {
				coinFlips.setNumberOfFlips(keyboard.nextInt());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		while (coinFlips.getTotalHeadsFlipped() < 0) {
			System.out.println("Please enter number of heads");
			try {
				coinFlips.setTotalHeadsFlipped(keyboard.nextInt());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(coinFlips.getProbability());
		keyboard.close();

	}

}
