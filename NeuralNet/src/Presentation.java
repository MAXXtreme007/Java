import java.util.Scanner;

public class Presentation {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		Perceptron p;
		String numOfInputsString;
		int numOfInputs = 0;

		boolean goAgain = false;
		do {
			goAgain = false;
			System.out.print("How many inputs would you like? ");

			numOfInputsString = keyboard.nextLine();

			try {
				numOfInputs = Integer.valueOf(numOfInputsString);
			} catch (NumberFormatException e) {
				goAgain = true;
				System.out.println("Please enter a number. ");
			}
		} while (goAgain);

		p = new Perceptron(numOfInputs);
		
		System.out.println("\nRESULTS:\n");

		int rows = (int) Math.pow(2, numOfInputs);
		boolean[][] table = new boolean[rows][numOfInputs];
		for (int i = 0; i < rows; i++) {
            for (int j = numOfInputs - 1; j >= 0; j--) {
                table[i][j] = intToBool(i / (int) Math.pow(2, j)%2);
            }
        }
		
		boolean expectedValue = false;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < numOfInputs; j++) {
				if (j == 0) {
					expectedValue = !table[i][j];
				} else {
					expectedValue = !(expectedValue && table[i][j]);
				}
				p.getSynapse(j).setActivated(table[i][j]);
			}
			System.out.println(expectedValue);
			while (p.isFiring() != expectedValue) {
				p.train(expectedValue);
			}
			System.out.println(p.toString());
		}
		keyboard.close();
	}

	public static boolean intToBool(int value) {
		if (value == 0) {
			return false;
		} else {
			return true;
		}
	}
}