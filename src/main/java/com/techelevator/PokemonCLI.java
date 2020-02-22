package com.techelevator;

import java.util.Scanner;

import org.apache.commons.dbcp.BasicDataSource;

import com.techelevator.pokemon.model.Battle;

public class PokemonCLI {

	private Scanner scan;
	private Battle battle;
	private boolean battleTypeHasBeenChosen;
	private int battleTypeAsInt = 0;
	private String battleTypeChoice;
	
	private static final int COM_VS_COM_BATTLE = 1;
	private static final int TRAINER_VS_COM_BATTLE = 2;
	private static final int TRAINER_VS_TRAINER_BATTLE = 3;
	
	public static void main(String[] args) throws InterruptedException {
		PokemonCLI application = new PokemonCLI();
		application.setUp();
		application.run();
	}
	
	private void setUp() {
		scan = new Scanner(System.in);
		battle = new Battle();
		battleTypeHasBeenChosen = false;
		
		BasicDataSource 
		
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("postgres1");
		
//		campgroundDao = new JDBCCampgroundDAO(dataSource);
//		siteDao = new JDBCSiteDAO(dataSource);
//		reservationDao = new JDBCReservationDAO(dataSource);
//		parkDao = new JDBCParkDAO(dataSource);	
	}
	
	private void run() {
		while (!battleTypeHasBeenChosen) {
			promptForBattleType();
			checkIfBattleTypeIsValid();
		}
		
		choosePokemon();
		printBattleAnnouncement();
		printBattle();
		
		System.out.println();
		System.out.println(battle.getwinner());
		
		scan.close();
	}
		
	private void promptForBattleType() {
		System.out.println("What kind of battle do you want to do?");
		System.out.println("1. Computer vs. Computer\n2. You vs. Computer\n3. You vs. Friend");
		battleTypeChoice = scan.nextLine();
	}
	
	private void checkIfBattleTypeIsValid() {
		try {
			battleTypeAsInt = Integer.parseInt(battleTypeChoice);
			if (battleTypeAsInt >= 1 && battleTypeAsInt <= 3) {
				battleTypeHasBeenChosen = true;
				battle.setTypeOfBattle(battleTypeAsInt);
			} else {
				System.out.println("Not a valid battle type chosen, choose again.\n");
			} 
		} catch (NumberFormatException e) {
			System.out.println("Not a valid battle type chosen, choose again.\n");
		}
	}
	
	private void choosePokemon() {
		for (int i = 1; i < 3; i++) {
			if (battleTypeAsInt == COM_VS_COM_BATTLE) {
				comVsComOptions(i);
				if (!battle.pokemonCreated()) {
					i--;
				}
			}
			if (battleTypeAsInt == TRAINER_VS_COM_BATTLE && i == 1) {
				trainerVsComTrainerPick(i);
				if (!battle.pokemonCreated()) {
					i--;
				}
			} else if (battleTypeAsInt == TRAINER_VS_COM_BATTLE && i == 2) {
				System.out.println("Your opponent is choosing...");
				System.out.println(battle.chooseComPokemon());
			}
			if (battleTypeAsInt == TRAINER_VS_TRAINER_BATTLE) {
				trainerVsTrainerPick(i);
				if (!battle.pokemonCreated()) {
					i--;
				}
			}
			System.out.println();
		}
	}
	
	private void getPokemonChoice() {
		System.out.print("(B)bulasaur, (C)harmander, (S)quirtle, (E)evee, or (P)ikachu? ");
		String input = scan.nextLine().toUpperCase();
		System.out.println(battle.createPokemon(battle, input));
	}
	
	private void comVsComOptions(int i) {
		System.out.println("\nChoose pokemon number " + i + "!");
		getPokemonChoice();
	}
	
	private void trainerVsComTrainerPick(int i) {
		System.out.println("\nTrainer, choose your pokemon!");
		getPokemonChoice();
	}
	
	private void trainerVsTrainerPick(int i) {
		System.out.println("Trainer " + i + ", choose your pokemon!");
		getPokemonChoice();
	}
	
	private void printBattleAnnouncement() {
		scan.nextLine();
		System.out.println(battle.announceBattle());
		System.out.println();
		scan.nextLine();
		System.out.println(battle.whoGoesFirst());
	}
	
	private void printBattle() {
		while (!battle.isBattleOver()) {
			battle.setWhoIsAttacking();
			if (battle.getPokemonAttacking().hasTrainer()) {
				System.out.print(battle.displayMoves());
				seeIfMoveIsValid();
			} else {
				scan.nextLine();
				System.out.println("\n" + battle.comPokemonTurn());
			}
		}
	}
	
	private void seeIfMoveIsValid() {
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
	}

}
