package ui;

import bp.Game;

public class Console {

	public static void main(String[] args) throws InterruptedException {
		Game myGame1 = new Game();
		myGame1.setScore(10);
		Thread myThread1 = new Thread(myGame1);
		
		Game myGame2 = new Game();
		myGame2.setScore(30);
		Thread myThread2 = new Thread(myGame2);
		
		Game myGame3 = new Game();
		myGame3.setScore(20);
		Thread myThread3 = new Thread(myGame3);
		
		myThread1.start();
		myThread2.start();
		myThread3.start();
		
		myThread1.join();
		myThread2.join();
		myThread3.join();
		
		System.out.println("Game high score: " + Game.getHighScore());
	}
}
