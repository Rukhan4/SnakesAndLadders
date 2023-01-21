//Written by: Surur Khan -- 40232573
//Class: COMP249
//Assignment #1 
//Due Date: Friday February 3rd

package LaddersAndSnakes;
import java.util.Scanner;


public class PlayLadderAndSnake {
	/*
	 * @author Surur Khan
	 * @date: 16/01/2023
	 * @since 1.0
	 * Description: Simulates a game of snakes and ladders allowing 2 players to interactively compete. 
	 * Contains the functionality for the game.
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[][] board = new String[10][10];
		
		System.out.print("Welcome to Snakes and Ladders! How many players are present?: ");
		LadderAndSnake game = new LadderAndSnake(in.nextInt(), board);

		game.play();
		in.close();
	}
}
