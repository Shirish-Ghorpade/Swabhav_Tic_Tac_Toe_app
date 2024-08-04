package com.aurionpro.model;

import java.util.List;

import javafx.util.Pair;

public class TicTacToeFacade {
	private TicTacToeGame game;
	private int sizeOfGrid;
	public TicTacToeFacade(int sizeOfGrid) {
		this.game = new TicTacToeGame(sizeOfGrid);
	}

	public void initializeGame() {
		game.initializationGame(sizeOfGrid);
	}

	public String startGame() {
		return game.startGame();
	}
}
