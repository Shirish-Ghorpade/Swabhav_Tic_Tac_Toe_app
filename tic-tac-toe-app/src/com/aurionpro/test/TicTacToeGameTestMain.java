package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.TicTacToeFacade;

public class TicTacToeGameTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the size of grid : ");
		Scanner scanner = new Scanner(System.in);
		int sizeOfGrid = scanner.nextInt();
		TicTacToeFacade game = new TicTacToeFacade(sizeOfGrid);
		System.out.println(game.startGame());
	}
}
