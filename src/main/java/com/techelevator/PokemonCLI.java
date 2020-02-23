package com.techelevator;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.pokemon.model.Battle;
import com.techelevator.pokemon.model.Move;
import com.techelevator.pokemon.model.MoveDAO;
import com.techelevator.pokemon.model.Pokemon;
import com.techelevator.pokemon.model.PokemonDAO;
import com.techelevator.pokemon.model.jdbc.JDBCMoveDAO;
import com.techelevator.pokemon.model.jdbc.JDBCPokemonDAO;

public class PokemonCLI {

	private Scanner scan;
	private Battle battle;
	private boolean battleTypeHasBeenChosen;
	private int battleTypeAsInt = 0;
	private String battleTypeChoice;
	private boolean pokemonMade;
	
	private PokemonDAO pokemonDAO;
	private MoveDAO moveDAO;
	
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
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/pokemon");
		dataSource.setUsername("postgres");
		dataSource.setPassword(System.getenv("DB_PASSWORD"));
		
		pokemonDAO = new JDBCPokemonDAO(dataSource);	
		moveDAO = new JDBCMoveDAO(dataSource);
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
				battle.setTypeOfBattle(COM_VS_COM_BATTLE);
				comVsComOptions(i);
				if (!pokemonMade) {
					i--;
				}
			}
			if (battleTypeAsInt == TRAINER_VS_COM_BATTLE && i == 1) {
				battle.setTypeOfBattle(TRAINER_VS_COM_BATTLE);
				trainerVsComTrainerPick(i);
				if (!pokemonMade) {
					i--;
				}
			} else if (battleTypeAsInt == TRAINER_VS_COM_BATTLE && i == 2) {
				System.out.println("Your opponent is choosing...");
				Pokemon comPokemon = pokemonDAO.getPokemonById(battle.chooseComPokemon());
				battle.addPokemonToBattle(comPokemon);
				System.out.println(comPokemon.getName()+  ", the " + pokemonDAO.getPokemonType(comPokemon) + " type pokemon was chosen!");
			}
			if (battleTypeAsInt == TRAINER_VS_TRAINER_BATTLE) {
				battle.setTypeOfBattle(TRAINER_VS_TRAINER_BATTLE);
				trainerVsTrainerPick(i);
				if (!pokemonMade) {
					i--;
				}
			}
			System.out.println();
		}
	}
	
	private void getPokemonChoice() {
		List<Pokemon> pokemon = pokemonDAO.getAllPokemon();
		for (Pokemon p : pokemon) {
			System.out.format("%d. %s\n", p.getPokemonId(), p.getName());
		}
		try {
			int userPokemonChoice = Integer.parseInt(scan.nextLine());
			pokemonMade = false;
			for (Pokemon p : pokemon) {
				if (userPokemonChoice == p.getPokemonId()) {
					createAndSetUpPokemon(userPokemonChoice);
					pokemonMade = true;
				}
			}
			if(!pokemonMade) {
				System.out.println("Not a valid pokemon, try again.");
			}	
		} catch (NumberFormatException e) {
			System.out.println("Not a valid pokemon, choose again.");
		}
	}
	
	private void createAndSetUpPokemon(int userPokemonChoice) {
		Pokemon chosenPokemon = pokemonDAO.getPokemonById(userPokemonChoice);
		battle.addPokemonToBattle(chosenPokemon);
		battle.setIfPokemonHasTrainer(chosenPokemon);
		battle.setTrainerOrComNumber(chosenPokemon);
		System.out.println(chosenPokemon.getName() + ", the " + 
						   pokemonDAO.getPokemonType(chosenPokemon) + " type pokemon was chosen!");
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
				List<Move> moves = moveDAO.getPokemonMoves(battle.getPokemonAttacking()); 
				scan.nextLine();
				System.out.print(battle.displayMoves(moves));
				seeIfMoveIsValid(moves);
			} else {
				scan.nextLine();
				Move moveUsed = moveDAO.getRandomComMove(battle.getPokemonAttacking());
				System.out.println(battle.pokemonTurn(moveUsed));
			}
		}
	}
	
	private void seeIfMoveIsValid(List<Move> moves) {
		try {
			boolean moveChosen = false;
			int parsedMoveInput = Integer.parseInt(scan.nextLine());
			if (parsedMoveInput >= 1 && parsedMoveInput <= 4) {
				moveChosen = true;
				System.out.print(battle.pokemonTurn(moves.get(parsedMoveInput - 1)));
			}
			if (!moveChosen) {
				System.out.println("Not a valid move choice, choose again.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Not a valid move choice, choose again.");
		}
	}

}
