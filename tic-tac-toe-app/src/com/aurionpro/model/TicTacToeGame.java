package com.aurionpro.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.Sides;

import javafx.util.Pair;

public class TicTacToeGame {
	private Deque<Player> players;
	private Board gameBoard;
	Scanner scanner = new Scanner(System.in);
	
	public TicTacToeGame(int sizeOfGrid) {
		initializationGame(sizeOfGrid);
	}
	
	public Deque<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Deque<Player> players) {
		this.players = players;
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	public void initializationGame(int sizeOfGrid) {
		players = new LinkedList<>();
		PlayingPieceX crossPiece = new PlayingPieceX();
		Player player1 = new Player("Player 'X' ", crossPiece);

		PlayingPieceO noughtsPiece = new PlayingPieceO();
		Player player2 = new Player("Player 'O' ", noughtsPiece);

		players.add(player1);
		players.add(player2);
		gameBoard = new Board(sizeOfGrid);

	}

	public String startGame() {
		int size = gameBoard.getSize();
		boolean noWinner = true;
		while (noWinner) {
			Player playerTurn = players.removeFirst();

			List<Pair<Integer, Integer>> freeSpaces = gameBoard.getFreeCells();
			if (freeSpaces.isEmpty()) {
				noWinner = false;
				continue;
			}

			System.out.print(playerTurn.getName() + "enter your move (row[1-" + size + "] column[1-" + size + "]) : ");

			int inputRow = scanner.nextInt() - 1;
			int inputColumn = scanner.nextInt() - 1;
			
			boolean pieceAddedSucessfully = gameBoard.addPiece(inputRow, inputColumn, playerTurn.getPlayingPiece());

			if (!pieceAddedSucessfully) {
				System.out.println("This move at (" + inputRow + "," + inputColumn + ") is not valid try again");
				players.addFirst(playerTurn);
				continue;
			}
			System.out.println();
			gameBoard.printBoard();
			players.addLast(playerTurn);
			boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.getPlayingPiece().getPieceType());
			if (winner) {
				return playerTurn.getName() + " Won !";
			}
		}
		return "tie";

	}

	public boolean isThereWinner(int row, int column, char pieceType) {
		int size = gameBoard.getSize();
		PlayingPiece[][] board = gameBoard.getBoard();
		boolean rowMatch = true;
		boolean columnMatch = true;
		boolean diagonalMatch = true;
		boolean antiDiagonalMatch = true;

		for (int i = 0; i < size; i++) {
			if (board[row][i] == null || board[row][i].getPieceType() != pieceType) {
				rowMatch = false;
				break;
			}
		}
		for (int i = 0; i < size; i++) {
			if (board[i][column] == null || board[i][column].getPieceType() != pieceType) {
				columnMatch = false;
				break;
			}
		}
		for (int i = 0, j = 0; i < size; i++, j++) {
			if (board[i][j] == null || board[i][j].getPieceType() != pieceType) {
				diagonalMatch = false;
				break;
			}
		}
		for (int i = 0, j = size - 1; i < size; i++, j--) {
			if (board[i][j] == null || board[i][j].getPieceType() != pieceType) {
				antiDiagonalMatch = false;
				break;
			}
		}
		return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
	}

}
