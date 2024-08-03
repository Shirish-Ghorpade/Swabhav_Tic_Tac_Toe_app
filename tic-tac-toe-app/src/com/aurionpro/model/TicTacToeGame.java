package com.aurionpro.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.sql.rowset.RowSetWarning;

import javafx.util.Pair;

public class TicTacToeGame {
	Deque<Player> players;
	Board gameBoard;
	Scanner scanner = new Scanner(System.in);

	public TicTacToeGame() {
		initializationGame();
	}

	public void initializationGame() {
		players = new LinkedList<>();
		PlayingPieceX crossPiece = new PlayingPieceX();
		Player player1 = new Player("Player 'X' ", crossPiece);

		PlayingPieceO noughtsPiece = new PlayingPieceO();
		Player player2 = new Player("Player 'O' ", noughtsPiece);

		players.add(player1);
		players.add(player2);
		
		System.out.println("Enter the size of grid : ");
		int sizeOfGrid = scanner.nextInt(); 
		gameBoard = new Board(sizeOfGrid);

	}

	public String startGame() {
		boolean noWinner = true;
		while (noWinner) {
			Player playerTurn = players.removeFirst();

			List<Pair<Integer, Integer>> freeSpaces = gameBoard.getFreeCells();
			if (freeSpaces.isEmpty()) {
				noWinner = false;
				continue;
			}
			
			System.out.print(playerTurn.getName() + "enter your move (row[1-"+gameBoard.size+"] column[1-"+ gameBoard.size+"]) : ");

			int inputRow = scanner.nextInt() - 1;
			int inputColumn = scanner.nextInt() - 1;

			if (inputRow >= gameBoard.size || inputColumn >= gameBoard.size) {
				System.out.println("This move at (" + inputRow + "," + inputColumn + ") is not valid try again");
				players.addFirst(playerTurn);
				continue;
			}

			boolean pieceAddedSucessfully = gameBoard.addPiece(inputRow, inputColumn, playerTurn.playingPiece);

			if (!pieceAddedSucessfully) {
				System.out.println("This move at (" + inputRow + "," + inputColumn + ") is not valid try again");
				players.addFirst(playerTurn);
				continue;
			}
			System.out.println();
			gameBoard.printBoard();
			players.addLast(playerTurn);
			boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.playingPiece.pieceType);
			if (winner) {
				return playerTurn.name + " Won !";
			}
		}
		return "tie";

	}

	public boolean isThereWinner(int row, int column, PieceType pieceType) {
		boolean rowMatch = true;
		boolean columnMatch = true;
		boolean diagonalMatch = true;
		boolean antiDiagonalMatch = true;

		for (int i = 0; i < gameBoard.size; i++) {
			if (gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
				rowMatch = false;
				break;
			}
		}
		for (int i = 0; i < gameBoard.size; i++) {
			if (gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != pieceType) {
				columnMatch = false;
				break;
			}
		}
		for (int i = 0, j = 0; i < gameBoard.size; i++, j++) {
			if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
				diagonalMatch = false;
				break;
			}
		}
		for (int i = 0, j = gameBoard.size - 1; i < gameBoard.size; i++, j--) {
			if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
				antiDiagonalMatch = false;
				break;
			}
		}
		return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
	}

}
