package com.aurionpro.model;

import java.util.List;

import javafx.util.Pair;

public class TicTacToeFacade {
	private TicTacToeGame game;

	public TicTacToeFacade() {
		super();
		this.game = new TicTacToeGame();
	}

	public void initializeGame() {
		game.initializationGame();
	}

	public String startGame() {
		return game.startGame();
	}

	public void printBoard() {
		game.gameBoard.printBoard();
	}

	public List<Pair<Integer, Integer>> getFreeCalls() {
		return game.gameBoard.getFreeCells();
	}

	public boolean addPiece(int row, int column, Player player) {
		return game.gameBoard.addPiece(row, column, player.playingPiece);
	}

}
