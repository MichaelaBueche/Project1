package project1;

/**
 * @author Michaela Bueche
 *
 */
public class Board {
	protected final int BOARD_ROW = 8;//number of rows in board
	protected final int BOARD_COLUMN = 8;//number of columns in board
	private int[][] board = new int[BOARD_ROW][BOARD_COLUMN];//2 dimensional matrix of pieces
	private int[] directions = new int[BOARD_ROW];//how many pieces need to be flipped in each direction
	private final String verLine = "|";//vertical line

	/**
	 * @param print
	 * creates a new board with a white piece at (4,4) and (5,5) and a black piece at (4,5) and (5,4)
	 * if beginning of game prints the board
	 */
	public Board(boolean print) {
		for (int row = 0; row < BOARD_ROW; row++) {//go through each row of board
			for (int column = 0; column < BOARD_COLUMN; column++) {//go through each column of board
				board[row][column] = 0;//set all board elements to 0
			}
		}
		board[3][3] = 2;//(4,4)=white
		board[3][4] = 1;//(4,5)=black
		board[4][3] = 1;//(5,4)=black
		board[4][4] = 2;//(5,5)=white
		if(print==true)
			printBoard();//if beginning of game print the board
	}

	/**
	 * prints formatted board
	 */
	public void printBoard() {
		int count = 0;
		System.out.printf("%7d %3d %3d %3d %3d %3d %3d %3d", ++count, ++count, ++count, ++count, ++count, ++count,
				++count, ++count);//prints 1-8 across top of board
		count = 0;
		for (int i = 0; i < BOARD_ROW; i++) {
			System.out.print("\n    ");
			for (int j = 0; j < BOARD_COLUMN * 4 + 1; j++) {
				System.out.print("-");//print row of -
			}
			System.out.printf("\n    |   |   |   |   |   |   |   |   |");
			System.out.printf("\n%2d  |", ++count);
			for (int m = 0; m < BOARD_COLUMN; m++) {
				if (board[i][m] == 1)
					System.out.printf("%2s%2s", "B", verLine);
				else if (board[i][m] == 2)
					System.out.printf("%2s%2s", "W", verLine);
				else
					System.out.printf("%4s", verLine);
			}//prints squares with B or W if there is a piece or blank
			System.out.printf("\n    |   |   |   |   |   |   |   |   |");
		}//repeats for each row 1-8
		System.out.print("\n    ");
		for (int k = 0; k < BOARD_COLUMN * 4 + 1; k++) {
			System.out.print("-");//print row of -
		}
		System.out.println();
	}

	/**
	 * @param row
	 * @param column
	 * @param color
	 * tests the different cases of the spaces surrounding (row,column)
	 * @return if the spaces encircling (row,column) are all the same color as (row,column) or empty
	 */
	public boolean isSameColor(int row, int column, int color) {
		if (row == 0 && column == 0) {
			if ((board[0][1] == color || board[0][1] == 0)
					&& (board[1][1] == color || board[1][1] == 0)
					&& (board[1][0] == color || board[1][0] == 0))
				return true;
			else
				return false;
		} //checks if (row,column) is top left corner and if surrounding is same color or empty
		else if (row == BOARD_ROW - 1 && column == 0) {
			if ((board[BOARD_ROW - 2][0] == color || board[BOARD_ROW - 2][0] == 0)
					&& (board[BOARD_ROW - 2][1] == color || board[BOARD_ROW - 2][1] == 0)
					&& (board[BOARD_ROW - 1][1] == color || board[BOARD_ROW - 1][1] == 0))
				return true;
			else
				return false;
		} //checks if (row,column) is top right corner and if surrounding is same color or empty
		else if (row == 0 && column == BOARD_COLUMN - 1) {
			if ((board[0][BOARD_COLUMN - 2] == color || board[0][BOARD_COLUMN - 2] == 0)
					&& (board[1][BOARD_COLUMN - 2] == color || board[1][BOARD_COLUMN - 2] == 0)
					&& (board[1][BOARD_COLUMN - 1] == color || board[1][BOARD_COLUMN - 1] == 0))
				return true;
			else
				return false;
		} //checks if (row,column) is bottom left corner and if surrounding is same color or empty
		else if (row == BOARD_ROW - 1 && column == BOARD_COLUMN - 1) {
			if ((board[BOARD_ROW - 2][BOARD_COLUMN - 1] == color || board[BOARD_ROW - 2][BOARD_COLUMN - 1] == 0)
					&& (board[BOARD_ROW - 2][BOARD_COLUMN - 2] == color || board[BOARD_ROW - 2][BOARD_COLUMN - 2] == 0)
					&& (board[BOARD_ROW - 1][BOARD_COLUMN - 2] == color || board[BOARD_ROW - 1][BOARD_COLUMN - 2] == 0))
				return true;
			else
				return false;
		} //checks if (row,column) is bottom right corner and if surrounding is same color or empty
		else if (row == 0 && column != 0 && column != BOARD_COLUMN - 1) {
			if ((board[row][column - 1] == color || board[row][column - 1] == 0 )
					&& (board[row + 1][column - 1] == color || board[row + 1][column - 1] == 0)
					&& (board[row + 1][column] == color || board[row + 1][column] == 0)
					&& (board[row + 1][column + 1] == color || board[row + 1][column + 1] == 0)
					&& (board[row][column + 1] == color || board[row][column + 1] == 0)) 
				return true;
			else
				return false;
		} //checks if (row,column) is top edge and if surrounding is same color or empty
		else if (row == BOARD_ROW && column != 0 && column != BOARD_COLUMN - 1) {
			if ((board[row][column - 1] == color || board[row][column - 1] == 0 )
					&& (board[row - 1][column - 1] == color || board[row - 1][column - 1] == 0)
					&& (board[row - 1][column] == color || board[row - 1][column] == 0)
					&& (board[row + 1][column - 1] == color || board[row + 1][column - 1] == 0)
					&& (board[row][column + 1] == color || board[row][column + 1] == 0))
				return true;
			else
				return false;
		} //checks if (row,column) is bottom edge and if surrounding is same color or empty
		else if (column == 0 && row != 0 && row != BOARD_ROW-1) {
			if ((board[row - 1][column] == color || board[row - 1][column] == 0)
					&& (board[row - 1][column + 1] == color || board[row - 1][column + 1] == 0)
					&& (board[row][column + 1] == color || board[row][column + 1] == 0)
					&& (board[row + 1][column + 1] == color || board[row + 1][column] == 0)
					&& (board[row + 1][column] == color || board[row + 1][column] == 0))
				return true;
			else
				return false;
		} //checks if (row,column) is left edge and if surrounding is same color or empty
		else if (column == BOARD_COLUMN-1 && row != 0 && row != BOARD_ROW-1) {
			if ((board[row - 1][column] == color || board[row - 1][column] == 0)
					&& (board[row - 1][column - 1] == color || board[row - 1][column - 1] == 0)
					&& (board[row][column - 1] == color || board[row][column - 1] == 0)
					&& (board[row + 1][column - 1] == color || board[row + 1][column - 1] == 0)
					&& (board[row + 1][column] == color || board[row + 1][column] == 0))
				return true;
			else
				return false;
		} //checks if (row,column) is right edge and if surrounding is same color or empty
		else if(row!=0&&row!=BOARD_ROW-1&&column!=0&&column!=BOARD_COLUMN-1) {
			if((board[row][column-1]==color || board[row][column-1] == 0)
					&& (board[row+1][column-1]==color || board[row+1][column-1] == 0)
					&& (board[row+1][column]==color || board[row+1][column] == 0)
					&& (board[row+1][column+1]==color || board[row+1][column+1] == 0)
					&& (board[row][column+1]==color || board[row][column+1] == 0)
					&& (board[row-1][column+1]==color || board[row-1][column+1] == 0)
					&& (board[row-1][column]==color || board[row-1][column] == 0)
					&& (board[row-1][column-1]==color || board[row-1][column-1] == 0))
				return true;
			else 
				return false;
		} //checks if (row,column) is not on any edge and if surrounding is same color or empty
		else
			return false;
	}

	/**
	 * @param row
	 * @param column
	 * @param color
	 * checks if the move is valid in any of the 8 directions
	 * @return if the move is valid
	 */
	public boolean isValid(int row, int column, int color) {
		int otherColor = 0;
		if(color == 1)
			otherColor=2;
		else otherColor=1;//make otherColor opposite of given color
		if (board[row][column] != 0)
			return false;//check if spot is empty
		if (isSameColor(row, column, 0) == true)
			return false;//check if surrounded by empty spaces
		if (isSameColor(row, column, color) == true)
			return false;//check if surrounded by same color or same color and empty spaces
		for (int i = column - 1; i > 0; i--) {
			if (board[row][i] == otherColor) {
				if (board[row][i-1] == color)
					return true;
				else 
					continue;
			} else 
				break;
		}//check if spaces left of (row,column) are the opposite color and if the same color is on the other side
		for (int i = row + 1; i < BOARD_ROW - 1; i++) {
			if (board[i][column] == otherColor) {
				if (board[i+1][column] == color)
					return true;
				else 
					continue;
			} else
				break;
		}//check if spaces down from (row,column) are the opposite color and if the same color is on the other side
		for (int i = column + 1; i < BOARD_COLUMN - 1; i++) {
			if (board[row][i] == otherColor) {
				if (board[row][i+1] == color)
					return true;
				else
					continue;
			} else
				break;
		}//check if spaces right of (row,column) are the opposite color and if the same color is on the other side
		for (int i = row - 1; i > 0; i--) {
			if (board[i][column] == otherColor) {
				if (board[i-1][column] == color) 
					return true;
				else
					continue;
			} else
				break;
		}//check if spaces up from (row,column) are the opposite color and if the same color is on the other side
		for (int i = row - 1, j = column - 1; i > 0 && j > 0; i--, j--) {
			if (board[i][j] == otherColor) {
				if (board[i-1][j-1] == color) 
					return true;
				else
					continue;
			} else
				break;
		}//check if spaces diagonally up left from (row,column) are the opposite color and if the same color is on the other side
		for (int i = row + 1, j = column - 1; i < BOARD_ROW - 1 && j > 0; i++, j--) {
			if (board[i][j] == otherColor) {
				if (board[i+1][j-1] == color)
					return true;
				else
					continue;
			} else
				break;
		}//check if spaces diagonally down left from (row,column) are the opposite color and if the same color is on the other side
		for (int i = row - 1, j = column + 1; i > 0 && j < BOARD_COLUMN - 1; i--, j++) {
			if (board[i][j] == otherColor) {
				if (board[i-1][j+1] == color)
					return true;
				else
					continue;
			} else
				break;
		}//check if spaces diagonally up right from (row,column) are the opposite color and if the same color is on the other side
		for (int i = row + 1, j = column + 1; i < BOARD_ROW - 1 && j < BOARD_COLUMN - 1; i++, j++) {
			if (board[i][j] == otherColor) {
				if (board[i+1][j+1] == color)
					return true;
				else
					continue;
			} else
				break;
		}//check if spaces diagonally down right from (row,column) are the opposite color and if the same color is on the other side
		return false;
	}

	/**
	 * @param row
	 * @param column
	 * @param color
	 * places a piece on the board at (row,column)
	 */
	public void place(int row, int column, int color) {
		board[row][column] = color;// put piece at (row,column)
	}

	/**
	 * @param row
	 * @param column
	 * @param color
	 * @return if there are pieces that need to be flipped
	 */
	public boolean needFlip(int row, int column, int color) {
		int otherColor = 0;
		if(color == 1)
			otherColor=2;
		else otherColor=1;//makes otherColor opposite of given color
		directions = new int[BOARD_ROW];//how many in each direction need to be flipped
		boolean ret = false;//whether or not pieces need to be flipped
		for (int i = column-1; i >= 0; i--) {
			// left
			if (i < 0)
				break;//ensures not going off board
			if (board[row][i] == color) {
				if (directions[0] > 0) {
					ret = true;
					break;
				}
				else
					break;
			}//checks if pieces need to be flipped
			else if (board[row][i] == otherColor) {
				if (i == 0) {
					directions[0] = 0;
					break;
				} else
					directions[0]++;
			} //checks how many pieces need to be flipped
			else {
				directions[0] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		for (int i = row+1; i < BOARD_ROW; i++) {
			// down
			if (i >= BOARD_ROW)
				break;//ensures not going off board
			if (board[i][column] == color) {
				if (directions[1] > 0) {
					ret = true;
					break;
				}
				else
					break;
			} //checks if pieces need to be flipped
			else if (board[i][column] == otherColor) {
				if (i == BOARD_ROW - 1) {
					directions[1] = 0;
					break;
				} else
					directions[1]++;
			} //checks how many pieces need to be flipped
			else {
				directions[1] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		for (int i = column+1; i < BOARD_COLUMN; i++) {
			// right
			if (i >= BOARD_COLUMN)
				break;//ensures not going off board
			if (board[row][i] == color) {
				if (directions[2] > 0) {
					ret = true;
					break;
				}
				else
					break;
			} //checks if pieces need to be flipped
			else if (board[row][i] == otherColor) {
				if (i == BOARD_COLUMN - 1) {
					directions[2] = 0;
					break;
				} else
					directions[2]++;
			} //checks how many pieces need to be flipped
			else {
				directions[2] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		for (int i = row-1; i >= 0; i--) {
			// up
			if (i < 0)
				break;//ensures not going off board
			if (board[i][column] == color) {
				if (directions[3] > 0) {
					ret = true;
					break;
				}
				else
					break;
			} //checks if pieces need to be flipped
			else if (board[i][column] == otherColor) {
				if (i == 0) {
					directions[3] = 0;
					break;
				} else
					directions[3]++;
			} //checks how many pieces need to be flipped
			else {
				directions[3] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		for (int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--) {
			// up left
			if (i < 0 || j < 0)
				break;//ensures not going off board
			if (board[i][j] == color) {
				if (directions[4] > 0) {
					ret = true;
					break;
				}
				else
					break;
			} //checks if pieces need to be flipped
			else if (board[i][j] == otherColor) {
				if (i == 0 || j == 0) {
					directions[4] = 0;
					break;
				} else
					directions[4]++;
			} //checks how many pieces need to be flipped
			else {
				directions[4] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		for (int i = row+1, j = column-1; i < BOARD_ROW && j >= 0; i++, j--) {
			// down left
			if (i >= BOARD_ROW || j < 0)
				break;//ensures not going off board
			if (board[i][j] == color) {
				if (directions[5] > 0) {
					ret = true;
					break;
				}
				else
					break;
			} //checks if pieces need to be flipped
			else if (board[i][j] == otherColor) {
				if (i == BOARD_ROW - 1 || j == 0) {
					directions[5] = 0;
					break;
				} else
					directions[5]++;
			} //checks how many pieces need to be flipped
			else {
				directions[5] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		for (int i = row-1, j = column+1; i >= 0 && j < BOARD_COLUMN; i--, j++) {
			// up right
			if (i < 0 || j >= BOARD_COLUMN)
				break;//ensures not going off board
			if (board[i][j] == color) {
				if (directions[6] > 0) {
					ret = true;
					break;
				}
				else
					break;
			} //checks if pieces need to be flipped
			else if (board[i][j] == otherColor) {
				if (i == 0 || j == BOARD_COLUMN - 1) {
					directions[6] = 0;
					break;
				} else
					directions[6]++;
			} //checks how many pieces need to be flipped
			else {
				directions[6] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		for (int i = row+1, j = column+1; i < BOARD_ROW && j < BOARD_COLUMN; i++, j++) {
			// down right
			if (i >= BOARD_ROW || j >= BOARD_COLUMN)
				break;//ensures not going off board
			if (board[i][j] == color) {
				if (directions[7] > 0) {
					ret = true;
					break;
				}
				else
					break;
			} //checks if pieces need to be flipped
			else if (board[i][j] == otherColor) {
				if (i == BOARD_ROW - 1 || j == BOARD_COLUMN - 1) {
					directions[7] = 0;
					break;
				} else
					directions[7]++;
			} //checks how many pieces need to be flipped
			else {
				directions[7] = 0;
				break;//reset directions element for next call of needFlip
			}
		}
		return ret;//whether or not flip needs to be called
	}

	/**
	 * @param row
	 * @param column
	 * @param color
	 * flips the pieces that need flipping determined by needFlip()
	 */
	public void flip(int row, int column, int color) {
		for (int i = column-1; i >= column - directions[0]; i--) {
			// left
			board[row][i] = color;
		}//flips all necessary pieces left of (row,column)
		for (int i = row+1; i <= row + directions[1]; i++) {
			// down
			board[i][column] = color;
		}//flips all necessary pieces down from (row,column)
		for (int i = column+1; i <= column + directions[2]; i++) {
			// right
			board[row][i] = color;
		}//flips all necessary pieces right of (row,column)
		for (int i = row-1; i >= row - directions[3]; i--) {
			// up
			board[i][column] = color;
		}//flips all necessary pieces up from (row,column)
		for (int i = row-1, j = column-1; i >= row - directions[4] && j >= column - directions[4]; i--, j--) {
			// up left
			board[i][j] = color;
		}//flips all necessary pieces diagonally up left from (row,column)
		for (int i = row+1, j = column-1; i <= row + directions[5] && j >= column - directions[5]; i++, j--) {
			// down left
			board[i][j] = color;
		}//flips all necessary pieces diagonally down left from (row,column)
		for (int i = row-1, j = column+1; i >= row - directions[6] && j <= column + directions[6]; i--, j++) {
			// up right
			board[i][j] = color;
		}//flips all necessary pieces diagonally up right from (row,column)
		for (int i = row+1, j = column+1; i <= row + directions[7] && j <= column + directions[7]; i++, j++) {
			// down right
			board[i][j] = color;
		}//flips all necessary pieces diagonally down right from (row,column)
		printBoard();//print the updated board
	}

	/**
	 * @param color
	 * prints whoever is winner if there is a winner
	 * otherwise exits the method
	 * @return if there is a winner
	 */
	public boolean isWinner(int color) {
		int blackSum = 0;//number of black pieces on board
		int whiteSum = 0;//number of white pieces on board
		for (int i = 0; i < BOARD_ROW; i++) {
			for (int j = 0; j < BOARD_COLUMN; j++) {//loop through board
				if (isValid(i, j, color) == true)
					return false;//if value moves remain for that color no winner
				else {
					if (board[i][j] == 1)
						blackSum++;
					if (board[i][j] == 2)
						whiteSum++;
				}//sum up the black and white pieces
			}
		}
		if (whiteSum > blackSum) {
			System.out.println("Game over. White won.");
			return true;//white wins, there is a winner
		} else if (blackSum > whiteSum) {
			System.out.println("Game over. Black won.");
			return true;//black wins, there is a winner
		} else {
			System.out.println("Game over. It was a tie.");
			return true;//whiteSum=blackSum, tie, game ends
		}
	}
}