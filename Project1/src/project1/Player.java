package project1;

import java.util.*;

/**
 * @author Michaela Bueche
 *
 */
public class Player {
	private int color;
	private Scanner console;
	private String response;
	private String colorName;
	private int row;
	private int column;
	private Board piece = new Board(false);

	/**
	 * @param boocol
	 * creates a new player of specified color
	 */
	public Player(int boocol) {
		color = boocol;
		if (boocol == 1)
			colorName = "black";
		else
			colorName = "white";
		ask(colorName);
	}

	/**
	 * @param cName
	 * asks what player wants to do (play, pass, or quit) and makes sure input from console is valid
	 */
	public void ask(String cName) {
		console = new Scanner(System.in);
		System.out.println("You may play 'y', pass 'p', or quit 'q'.");
		if (console.hasNext()) {//ensure valid info passed in
			response = console.next();//what player wants to do
			if (response.equals("y")) {//play
				System.out.println("Row: ");//ask for row number
				if (console.hasNextInt()) {//ensure valid info passed in
					row = console.nextInt() - 1;//makes player's row equivalent to the one displayed on board
					if (row <= piece.BOARD_ROW&&row>0) {//ensure row within board boundaries
						System.out.println("Column: ");//ask for column number
						if (console.hasNextInt()) {//ensure valid info passed in
							column = console.nextInt() - 1;//makes player's column equivalent to the one displayed on board
							if (column <= piece.BOARD_COLUMN&&column>0) {//ensure column within board boundaries
								if (piece.isValid(row, column, color) == true) {//checks if valid move
									piece.place(row, column, color);//places piece
									if (piece.needFlip(row, column, color) == true) {//checks if need to flip any pieces
										piece.flip(row, column, color);//flips pieces
									} else
										piece.printBoard();//prints board if didn't flip
									if (piece.isWinner(color) == false) {//checks if there is a winner
										if (cName.equals("black")) {
											cName = "white";
											color = 2;
										} else {
											cName = "black";
											color = 1;
										}//switches player color for other player's turn
										System.out.println("Your turn is over. It is " + cName + "'s turn.");
										ask(cName);
									}//no winner, other player's turn
									console.close();
								} else {
									if (cName.equals("black")) {
										cName = "white";
										color = 2;
									} else {
										cName = "black";
										color = 1;
									}
									System.out.println("That is an invalid move. It is " + cName + "'s turn.");
									ask(cName);
									console.close();
								}//move not valid, other player's turn
							} else {
								if (cName.equals("black")) {
									cName = "white";
									color = 2;
								} else {
									cName = "black";
									color = 1;
								}
								System.out.println("That is an invalid column. It is " + cName + "'s turn.");
								ask(cName);
								console.close();
							}//column number not valid, other player's turn
						} else {
							if (cName.equals("black")) {
								cName = "white";
								color = 2;
							} else {
								cName = "black";
								color = 1;
							}
							System.out.println("That is not a number. It is " + cName + "'s turn.");
							ask(cName);
							console.close();
						}//not a number, other player's turn
					} else {
						if (cName.equals("black")) {
							cName = "white";
							color = 2;
						} else {
							cName = "black";
							color = 1;
						}
						System.out.println("That is an invalid row. It is " + cName + "'s turn.");
						ask(cName);
						console.close();
					}//row number not valid, other player's turn
				} else {
					if (cName.equals("black")) {
						cName = "white";
						color = 2;
					} else {
						cName = "black";
						color = 1;
					}
					System.out.println("That is not a number. It is " + cName + "'s turn.");
					ask(cName);
					console.close();
				}//not a number, other player's turn
			} else if (response.equals("p")) {//pass
				if (cName.equals("black")) {
					cName = "white";
					color = 2;
				} else {
					cName = "black";
					color = 1;
				}
				System.out.println("You have passed your turn. It is " + cName + "'s turn.");
				ask(cName);
				console.close();//passed, other player's turn
			} else if (response.equals("q")) {//quit
				if (cName.equals("black"))
					cName = "white";
				else
					cName = "black";
				System.out.println("You have quit. Game over. " + cName + " has won.");
				console.close();
				return;//forfeit game, other player automatically wins
			} else {//any other response
				if (cName.equals("black")) {
					cName = "white";
					color = 2;
				} else {
					cName = "black";
					color = 1;
				}
				System.out.println("That is not an option. It is " + cName + "'s turn.");
				ask(cName);
				console.close();
			}//not an option, other player's turn
		} else {//invalid info passed in
			if (cName.equals("black")) {
				cName = "white";
				color = 2;
			} else {
				cName = "black";
				color = 1;
			}
			System.out.println("That is not an option. It is " + cName + "'s turn.");
			ask(cName);
			console.close();
		}//not an option, other player's turn
	}

	/**
	 * @param args
	 * calls Board and Player
	 */
	public static void main(String... args) {
		new Board(true);//makes new board
		new Player(1);//makes new players
	}
}