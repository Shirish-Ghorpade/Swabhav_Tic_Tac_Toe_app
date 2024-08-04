package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class Board {
	private int size;
	private PlayingPiece board[][];

	public Board(int size) {
		this.size = size;
		this.board = new PlayingPiece[size][size];
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public PlayingPiece[][] getBoard() {
		return board;
	}

	public void setBoard(PlayingPiece[][] board) {
		this.board = board;
	}

	public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
		if (row < 0 || col < 0 || row >= size || col >= size || board[row][col] != null) {
			return false;
		}
		board[row][col] = playingPiece;
		return true;
	}

	public List<Pair<Integer, Integer>> getFreeCells() {
		List<Pair<Integer, Integer>> freeCells = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == null) {
					Pair<Integer, Integer> rowColumn = new Pair<>(i, j);
					freeCells.add(rowColumn);
				}
			}
		}
		return freeCells;
	}

	public void printBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] != null) {
					System.out.print(" " + board[i][j].getPieceType() + " ");
				} else {
					System.out.print("   ");
				}
				if (j < size - 1)
					System.out.print(" | ");
			}
			System.out.println();
			if (i < size - 1)
				System.out.print("------------------");
			System.out.println();
		}
	}
}
