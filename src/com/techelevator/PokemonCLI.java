package com.techelevator;

import java.util.Scanner;

public class PokemonCLI {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		Battle battle = new Battle();
		
		for(int i = 1; i < 3; i++) {
			System.out.println("Choose pokemon number " + i + "!");
			System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee, or (P)ikachu? ");
			String input = scan.nextLine();
			String announcement = battle.createBattlePokemon(input);
			if (announcement.equals("")) {
				i--;
				Thread.sleep(500);
				System.out.println("That's not a valid pokemon, choose again!");
				Thread.sleep(500);
			} else {
				Thread.sleep(1000);
				System.out.println(announcement);
				if (i == 1) {
					Thread.sleep(1500);
				}
			}
			
			System.out.println();
		}
		
		Thread.sleep(1500);
		System.out.println(battle.announceBattle());
		System.out.println();
		
		Thread.sleep(1500);
		System.out.println(battle.whoGoesFirst());
		
		while (!battle.isBattleOver()) {
			Thread.sleep(3000);
			System.out.print(battle.pokemonTurn());
		}
		
		Thread.sleep(3000);
		System.out.println();
		System.out.println(battle.getwinner());
		
		scan.close();
	}

}
