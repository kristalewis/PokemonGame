package com.techelevator;

import java.util.Scanner;

public class PokemonCLI {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner scan = new Scanner(System.in);
		Battle battle = new Battle();
		boolean battleTypeHasBeenChosen = false;
		int battleTypeAsInt = 0;
		
		while (!battleTypeHasBeenChosen) {
			System.out.println("What kind of battle do you want to do?");
			System.out.println("1. Computer vs. Computer\n2. You vs. Computer\n3. You vs. Friend");
			String battleTypeChoice = scan.nextLine();

			try {
				battleTypeAsInt = Integer.parseInt(battleTypeChoice);
				if (battleTypeAsInt >= 1 && battleTypeAsInt <= 3) {
					battleTypeHasBeenChosen = true;
					battle.setTypeOfBattle(battleTypeAsInt);
					for (int i = 1; i < 3; i++) {
						if (battleTypeAsInt == 1) {
							System.out.println("\nChoose pokemon number " + i + "!");
							System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee, or (P)ikachu? ");
							String input = scan.nextLine().toUpperCase();
							String announcement = battle.createBattlePokemon(input);
							if (announcement.equals("")) {
								i--;
								System.out.println("That's not a valid pokemon, choose again!");
							} else {
								System.out.println(announcement);
								Thread.sleep(700);
							}
						}
						if (battleTypeAsInt == 2 && i == 1) {
							System.out.println("\nTrainer, choose your pokemon!");
							System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee, or (P)ikachu? ");
							String input = scan.nextLine().toUpperCase();
							String announcement = battle.createBattlePokemon(input);
							if (announcement.equals("")) {
								i--;
								System.out.println("That's not a valid pokemon, choose again!");
							} else {
								System.out.println(announcement);
								Thread.sleep(700);
							}
						} else if (battleTypeAsInt == 2 && i == 2) {
							System.out.println("Your opponent is choosing...");
							Thread.sleep(1500);
							System.out.println(battle.chooseComPokemon());
						}
						if (battleTypeAsInt == 3) {
							System.out.println("Trainer " + i + ", choose your pokemon!");
							System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee, or (P)ikachu? ");
							String input = scan.nextLine().toUpperCase();
							String announcement = battle.createBattlePokemon(input);

							if (announcement.equals("")) {
								i--;
								System.out.println("That's not a valid pokemon, choose again!");
							} else {
								System.out.println(announcement);
								Thread.sleep(700);
							}
						}
						System.out.println();
					}
				} else {
					System.out.println("Not a valid battle type chosen, choose again.\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("Not a valid battle type chosen, choose again.\n");
			}
		}
		
		
		
		System.out.println(battle.announceBattle());
		System.out.println();
		
		Thread.sleep(1000);
		System.out.println(battle.whoGoesFirst());
		
		while (!battle.isBattleOver()) {
			battle.setWhoIsAttacking();
			Thread.sleep(2500);
			if (battle.getPokemonAttacking().hasTrainer()) {
				System.out.print(battle.displayMoves());
				Thread.sleep(2500);
				try {
					int parsedMoveInput = Integer.parseInt(scan.nextLine());
					int moveChosen = parsedMoveInput - 1;
					if (moveChosen >= 0 && moveChosen <= 3) {
						System.out.print(battle.pokemonTurn(moveChosen));
					} else {
						System.out.println("Not a valid move choice, choose again.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Not a valid move choice, choose again.");
				}
			} else {
				System.out.println("\n" + battle.comPokemonTurn());
			}
		}
		
		Thread.sleep(2500);
		System.out.println();
		System.out.println(battle.getwinner());
		
		scan.close();
	}

}
