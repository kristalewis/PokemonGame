package com.techelevator;

import java.util.Scanner;

public class PokemonCLI {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Battle battle = new Battle();
		
		for(int i = 1; i < 3; i++) {
			System.out.println("Choose pokemon number " + i + "!");
			System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee? ");
			String input = scan.nextLine();
			if (battle.createBattlePokemon(input).equals("")) {
				i--;
				System.out.println("That's not a valid pokemon, choose again!");
			}
			
			System.out.println();
		}
		
		System.out.println(battle.announceBattle());
		System.out.println();
		System.out.println(battle.whoGoesFirst());
		
		while (!battle.isOver) {
			battle.battle();
			if (battle.turn.length() > 0) {
				System.out.println(battle.turn);
			}
		}
		
		System.out.println();
		System.out.println(battle.getwinner());
		
		scan.close();
	}

}
