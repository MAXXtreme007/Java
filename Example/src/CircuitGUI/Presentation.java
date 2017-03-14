package CircuitGUI;

import java.util.Scanner;

public class Presentation {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Circuit main = new Circuit();

		while (main.getVoltage() == 0) {
			System.out.println("Please enter the voltage of the circuit");
			try {
				main.setVoltage(keyboard.nextInt());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		while (main.getAmperage() == 0) {
			System.out.println("Please enter the amperage of the circuit");
			try {
				main.setAmperage(keyboard.nextInt());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		while (main.getResistance() == 0) {
			System.out.println("Please enter the resistance of the circuit");
			try {
				main.setResistance(keyboard.nextInt());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println(main.calculateVoltage());
		System.out.println(main.calculateAmperage());
		System.out.println(main.calculateResistance());
		keyboard.close();
	}
}
