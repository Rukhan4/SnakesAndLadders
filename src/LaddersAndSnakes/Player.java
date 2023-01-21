package LaddersAndSnakes;

public class Player {
	/*
	 * @author Surur Khan
	 * @date: 16/01/2023
	 * @since 1.0
	 * Description: Contains the player object methods to update the snakes and ladders gameplay
	 */
	int pos;
	String name;
	int roll;
	int order;
	LadderAndSnake game = new LadderAndSnake();
	
	/** 
	 * Default construtor to initialize player attributes
	 */
	public Player() {
		pos = 0;
		name = "";
		roll = 0;
	}
	
	/**
	 * 
	 * @param position setter for position
	 */
	public void setPosition(int position) {
		this.pos = position;
	}
	
	/**
	 * 
	 * @return player object position
	 */
	public int getPosition() {
		return this.pos;
	}
	
	/**
	 * 
	 * @param newName sets the player name
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * 
	 * @return the player object's name
	 */
	public String getName() { 
		return this.name;
	}
	
	/**
	 * 
	 * @param roll sets the player roll value from 1 to 6
	 */
	public void setRoll(int roll) {
		this.roll = game.flipDice();
	}
	
	/**
	 * 
	 * @return the player object's roll score
	 */
	public int getRoll() {
		return this.roll;
	}
	
	/**
	 * @param player takes in a player object to determine if the other is on the same position
	 * @return void manipulates the player position to 0
	 */
	public void samePosition(Player player) {
		if (this.getPosition() == player.getPosition()) {
			System.out.println("\n" + player.getName() + " gets pushed back to tile 0!");
			player.setPosition(0);
		}
	}
	
}
