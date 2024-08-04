package com.aurionpro.model;

import java.util.List;

import javafx.util.Pair;

public class TicTacToeFacade {
	private TicTacToeGame game;

	public TicTacToeFacade() {
		this.game = new TicTacToeGame();
	}

	public void initializeGame() {
		game.initializationGame();
	}

	public String startGame() {
		return game.startGame();
	}

	public void printBoard() {
		game.getGameBoard().printBoard();
	}

	public List<Pair<Integer, Integer>> getFreeCalls() {
		return game.getGameBoard().getFreeCells();
	}

	public boolean addPiece(int row, int column, Player player) {
		return game.getGameBoard().addPiece(row, column, player.getPlayingPiece());
	}

}
