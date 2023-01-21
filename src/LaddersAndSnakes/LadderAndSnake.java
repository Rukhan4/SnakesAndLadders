package LaddersAndSnakes;
import java.util.Arrays;
import java.util.Scanner;

//Written by: Surur Khan -- 40232573
//Class: COMP249
//Assignment #1 
//Due Date: Friday February 3rd

public class LadderAndSnake {
	/*
	 * @author Surur Khan
	 * @date: 16/01/2023
	 * @since 1.0
	 * Description: Simulates a game of snakes and ladders allowing 2 players to interactively compete. 
	 * Contains the functionality for the game.
	 */
	String[][] board;
	int playerCount;

	
	/**
	 * default constructor for playercount
	 */
	public LadderAndSnake() {
		this.playerCount = 0;
		
	}
	
	/**
	 * parameterized constructor to initialize playercount and gameplay board
	 * @param playerCount initializes the amount of players in the game
	 * @param board sets the 10 by 10 snakes and ladders board
	 */
	public LadderAndSnake(int playerCount, String[][] board) {
		if (playerCount > 2) {
			System.out.println("Initialization was attempted for x member of players; however, this is only" + 
                               " expected for an extended version the game. Value will be set to 2.");
		}
		else if (playerCount < 2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
			System.exit(0);
		}
		this.playerCount = 2;
		this.board = board;
	}
	
	/**
	 * 
	 * @param player initializes the board and updates player's current position on the board
	 * @return void updated board display in console
	 */
	public void setBoard(Player player) {
		int count = 0;
		for (int i=0; i < board.length; i++) {
			for (int j=0; j < board.length; j++) {
				count++;
				if (i % 2 == 0) {
					board[i][j] = "" + count;
				}
				else if (i % 2 != 0) {
					board[i][9-j] = "" + count;
				}
				}
			}
		
		int[] tenPositions = {10,20,30,40,50,60,70,80,90,100};
		// setting ladders
		board[0][0] = "L1B"; // 1
		board[3][2] = "L1T"; // 38
		board[0][3] = "L2B"; // 4
		board[1][6] = "L2T"; // 14
		board[0][8] = "L3B"; // 9
		board[3][9] = "L3T"; // 31
		board[2][7] = "L4B"; // 28
		board[8][3] = "L4T"; // 84
		board[3][4] = "L5B"; // 36
		board[4][3] = "L5T"; // 44
		board[2][0] = "L6B"; // 21
		board[4][1] = "L6T"; // 42
		board[5][9] = "L7B"; // 51
		board[6][6] = "L7T"; // 67
		board[7][9] = "L8B"; // 71
		board[9][9] = "L8T"; // 91
		board[7][0] = "L9B"; // 80
		board[9][0] = "L9T"; // 100
		
		// setting snakes
		board[0][5] = "S1B"; // 6
		board[1][4] = "S1H"; // 16
		board[1][1] = "S2B"; // 19
		board[7][1] = "S2H"; // 79
		board[2][9] = "S3B"; // 30
		board[4][7] = "S3H"; // 48
		board[2][3] = "S4B"; // 24
		board[9][5] = "S4H"; // 95
		board[5][0] = "S5B"; // 60
		board[6][3] = "S5H"; // 64
		board[6][7] = "S6B"; // 68
		board[9][7] = "S6H"; // 93
		board[7][4] = "S7B"; // 76
		board[9][3] = "S7H"; // 97
		board[7][2] = "S8B"; // 78
		board[9][2] = "S8H"; // 98
		

		if (player.getPosition() < 10) { // row 0
			board[0][player.getPosition() - 1] = player.getName();
		} 
		
		else if (player.getPosition() == 100) { // WINNER
			System.out.println("PLAYER " + player.getName() + " HAS WON!!!!!!!!!!!");
			board[9][0] = player.getName();
			System.out.println("\n" + Arrays.deepToString(board).replace("], ", "]\n"));
			System.exit(0);
		}
		
		else if (player.getPosition() == 10 || player.getPosition() == 20 || player.getPosition() == 30 || player.getPosition() == 40 || player.getPosition() == 50 || player.getPosition() == 60 || player.getPosition() == 70 || player.getPosition() == 80 || player.getPosition() == 90) { // special case if player is on a 10's column
			System.out.println("on a multiple of 10");
			for (int i = 0; i < tenPositions.length; i++) {
				if (player.getPosition() == tenPositions[i]) { 
					if ((player.getPosition() / 10) % 2 == 0){ // rows 2 4 6 8
						board[(player.getPosition() / 10) - 1][0] = player.getName();	
					} 
					else if ((player.getPosition() / 10) % 2 != 0) { // rows 1 3 5 7 9
						board[(player.getPosition() / 10) - 1][9] = player.getName();
					} 
				}
			} // % = LAST / = FIRST
		}
		
		else if ((player.getPosition() / 10) % 2 == 0){ // rows 2 4 6 8
			board[player.getPosition() / 10][(player.getPosition() % 10) - 1] = player.getName();	
		} 
		
		else if ((player.getPosition() / 10) % 2 != 0) { // rows 1 3 5 7 9
			board[player.getPosition() / 10][10 - (player.getPosition() % 10)] = player.getName();
		} 
		
		// display current board
		System.out.println("\n" + Arrays.deepToString(board).replace("], ", "]\n"));
	}
	
	/**
	 * @param player the current player used to get their position
	 * @return void the updated player position if they land on a snake or ladder
	 */
	public void ladderOrSnake(Player player) {
		int[][] ladders = { {1,38}, {4,14}, {9,31}, {36,44}, {21,42}, {51,67}, {71,91}, {28,84}, {80,100} };
		int[][] snakes = { {16,6}, {48,30}, {64,60}, {79,19}, {93,68}, {95,24}, {97,76}, {98,78} };
		
		// landing on a ladder
		for (int i = 0; i < ladders.length; i++) {
			if (player.getPosition() == ladders[i][0]) {
				System.out.println("\n" + "Wow! " + player.getName() + " landed on a ladder!");
				player.setPosition(ladders[i][1]);
				setBoard(player);
			}
		}
		
		// landing on a snake
		for (int i = 0; i < snakes.length; i++) {
			if (player.getPosition() == snakes[i][0]) {
				System.out.println("\n" + "Oh No! " + player.getName() + " landed on a snake!");
				player.setPosition(snakes[i][1]);
				setBoard(player);
			}
		}
	}

	/**
	 * @param player retrieves player object to get their current position in case they step over 100
	 * @return void sets player position back by how much they crossed over 100
	 */
	public void backtrack(Player player) { 
		if (player.getPosition() >= 100) {
			int newPos =  (player.getPosition() + player.getRoll()) - 100;
			System.out.println(player.getName() + " overstepped the finish line by " + (100 - newPos) + " steps!" );
			System.out.println(player.getName() + " 's new position is: " + newPos);
			player.setPosition(newPos);
		}
	}
	
	/**
	 * Rolls the dice between 1 and 6 inclusively for gameplay
	 * @return int representing dice roll
	 */
	public int flipDice() {
		return (int)(Math.random()*6) + 1;
	}
	
	/**
	 * @param player1 player2 determine order of plays by rolling each player's dice
	 * @return void sets the player order to play
	 */
	public void playerOrder(Player player1, Player player2) {
		System.out.println("\n" + "Now Deciding who plays first");
		boolean roll = true;
		int attempt = 0;
		while (roll) { // keep rolling until tie broken
			player1.roll = flipDice();
			player2.roll = flipDice();
			System.out.println(player1.getName() + " rolled " + player1.getRoll());
			System.out.println(player2.getName() + " rolled " + player2.getRoll());
			attempt++;
			
			// tie occurs
			if (player1.getRoll() == player2.getRoll()) {
				System.out.println("Tie! Rolling Again ~");
				continue;
			}
				// tie broken. Determine player order
				roll = false;
				if (player1.getRoll() > player2.getRoll()) {
					System.out.println(player1.getName() + " goes first ");
				}
				else {
					System.out.println(player2.getName() + " goes first ");
				}
				System.out.println("Deciding the player order took " + attempt + " roll(s)" + "\n");
			}
	}
	
	/**
	 * Controls flow of while loop to allow user to press enter to continue game
	 */
	public void promptEnterKey(){
		   System.out.println("\n" + "Press \"ENTER\" to continue...");
		   Scanner scanner = new Scanner(System.in);
		   scanner.nextLine();
		}
	
	/**
	 * contains core mechanics for gameplay
	 * 1) Player order sets
	 * 2) flip's the winner's dice
	 * 3) check if the player crossed 100
	 * 4) advance player position
	 * 5) check if the player is on a snake or ladder
	 * 6) check if the player is on the same position as the opponent
	 * 7) check if the player has won (got to tile 100 exactly)
	 * 8) Repeats steps 2-7 for the opponent's turn
	 *
	 */
	public void play() {
		Scanner in = new Scanner(System.in);
		Player playerOne = new Player();
		Player playerTwo = new Player();
		System.out.println("Please enter player names below: ");

		System.out.print("Player 1: ");
		playerOne.setName(in.next());
		playerOne.setPosition(0);

		System.out.print("Player 2: ");
		playerTwo.setName(in.next());
		playerTwo.setPosition(0);
		
		// determine order of play
		playerOrder(playerOne, playerTwo);
		
		int currentTurn = 0;
		if (playerOne.getRoll() > playerTwo.getRoll()) {
			currentTurn = 1; // player 1 goes first
		}
		else {
			currentTurn = 0; // player 2 goes first
		}
		
		boolean playing = true;
		while (playing) {
			if (currentTurn == 1) { // player 1 goes
				currentTurn = 0;
				System.out.println(playerOne.getName() + "'s TURN"); 
				playerOne.setRoll(flipDice()); // player one rolls dice and sets the roll value
				System.out.println(playerOne.getName() + " rolled a " + playerOne.getRoll());
				playerOne.setPosition(playerOne.getPosition() + playerOne.getRoll()); // change player 1 position	
				backtrack(playerOne); // check if player 1 crossed 100
				setBoard(playerOne); // set player one position on board
				ladderOrSnake(playerOne); // check if player 1 is on a snake or ladder
				playerOne.samePosition(playerTwo); // check if player 1 and player 2 position same
				promptEnterKey();
				continue;
			}
			else { // player 2 goes
				currentTurn = 1;
				System.out.println(playerTwo.getName() + "'s TURN");
				playerTwo.setRoll(flipDice()); // player two rolls dice and sets the roll value
				System.out.println(playerTwo.getName() + " rolled a " + playerTwo.getRoll());
				playerTwo.setPosition(playerTwo.getPosition() + playerTwo.getRoll()); // change player 2 position
				backtrack(playerTwo); // check if player 2 crossed 100
				setBoard(playerTwo); // set player two on board
				ladderOrSnake(playerTwo); // check if player 2 is on a snake or ladder
				playerTwo.samePosition(playerOne); // check if player 2 and player 1 position same
				promptEnterKey();
			}
		}
		in.close();
	}
}

