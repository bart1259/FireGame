package game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Score {
	
	//Salt for the hashing algorithm
	private static final long SALT = -901577920135L;
	
	//The loaded high score
	private static int highScore = -1;

	//The current score
	private static int score = 0;
	private static int coinsCollected = 0;
	private static float timeSurvived = 0;
	private static int wavesSurvived = 0;
	
	//Resets the score
	public static void resetScore() {
		score = 0;
		timeSurvived = 0;
		coinsCollected = 0;
	}
	
	//Loads the highscore from the file
	public static void loadHighScore() {
		try {
			//loads the file
			@SuppressWarnings("resource")
			Scanner file = new Scanner(new FileReader("res/highScore.txt"));
			//Gets the suspected highscore
			int h = Integer.parseInt(file.nextLine());

			//Gets the suspected highscores hash
			String fileHash = file.nextLine();
			
			//Tests to see if the hash is real
			boolean fakeHash = !(hash(h) == Long.parseLong(fileHash));
			
			if(fakeHash) {
				//If it is fake then we reset the score
				highScore = -1;
			}
			else {
				//Else the score is real and we set it to the high score
				highScore = h;
			}
			
		} catch (FileNotFoundException | NoSuchElementException e) {
			//If we cannot find the file or the file is not formatted correctly, the score is reset
			highScore = -1;
		}
	}
	
	public static void saveHighScore() {
		try {
			//A new file is made
			FileOutputStream output = new FileOutputStream("res/highScore.txt", false);
			
			PrintWriter out = new PrintWriter(output);
			
			//We print the highscore and the highscore hash
			out.println(highScore );
			out.print(hash(highScore));
		
			out.close();
			
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//The hasing algorithm
	private static long hash(long l) {
		
		long ret = l + SALT;
		ret = ret * 135789215043780L;
		ret = ~ret;
		ret = ret >> 2;
		
		return ret;
	}
	
	//Calculates and returns the score
	public static int getScore(boolean lastCheck) {
		score = Math.round(Math.max(coinsCollected,1) * timeSurvived * wavesSurvived);
		if(lastCheck && score > highScore) {
			highScore = score;
			saveHighScore();
		}
		return score;
	}
	
	//Accessors
	
	public static void collectedCoin(int worth) {
		coinsCollected += worth;
	}
	
	public static void setWavesSurvived(int waves) {
		wavesSurvived = Math.max(1, waves);
	}
	
	public static void timeSurvived(float time) {
		timeSurvived = time;
	}
	
	public static int getHighScore() {
		return highScore;
	}
	
	public static int getCoinsCollected() {
		return coinsCollected;
	}

	public static float getTimeSurvived() {
		return timeSurvived;
	}

	public static int getWavesSurvived() {
		return wavesSurvived;
	}
}
