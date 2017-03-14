package bp;

import java.util.concurrent.Semaphore;

public class Game implements Runnable {
	private int score;
	private static int highScore;
	int threadsToThrow = Runtime.getRuntime().availableProcessors();
	private static Semaphore s = new Semaphore(1);

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int pScore) {
		score = pScore;
	}

	/**
	 * @return the highScore
	 */
	public static int getHighScore() {
		return highScore;
	}

	/**
	 * @param highScore
	 *            the highScore to set
	 */
	public static void setHighScore(int pHighScore) {
		highScore = pHighScore;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
			// lock the processor
			s.acquire();
			if (score > highScore) {
				Thread.sleep(100);
				highScore = score;
			}
			// unlock the processor
			s.release();
		} catch (InterruptedException e) {
		}
	}
}
