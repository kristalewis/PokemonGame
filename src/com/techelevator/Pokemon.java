package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Pokemon {

	private String name;
	private int hP;
	private String type;
	List <Move> moves = new ArrayList<Move>();
	private List<String> weaknesses = new ArrayList <String>();
	private List<String> strengths = new ArrayList <String>();
	private int attackStatChange = 0;
	private boolean hasTrainer = false;
	private int trainer;
	
	
	public Pokemon () {
	}
	
	public Pokemon (String name, int hP, String type) {
		this.name = name;
		this.hP = hP;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public int gethP() {
		return hP;
	}
	
	public void sethP(int hP) {
		this.hP = hP;
	}
	
	public String getType() {
		return type;
	}
	
	public void setHasTrainer (boolean isHuman) {
		this.hasTrainer = isHuman;
	}
	
	public boolean hasTrainer () {
		return this.hasTrainer;
	}
	public void setTrainerOrCom(int trainer) {
		this.trainer = trainer;
	}
	
	public int getTrainerOrCom() {
		return trainer;
	}
	
	public int getAttackStatChange() {
		return attackStatChange;
	}
	
	public void setAttackStatChange(int attackStatChange) {
		this.attackStatChange += attackStatChange;
	}
	
	public void setWeaknesses(String[] weaknesses) {
		for(int i = 0; i < weaknesses.length; i++) {
			this.weaknesses.add(weaknesses[i]);
		}
	}
	
	public void setStrengths(String[] strengths) {
		for(int i = 0; i < strengths.length; i++) {
			this.strengths.add(strengths[i]);
		}
	}
	
	Random rand = new Random();
	int randomMove;
	public int pickMove() {
		return rand.nextInt(4);
	}
	
	public List<String> getWeaknesses() {
		return weaknesses;
	}

	public List<String> getStrengths() {
		return strengths;
	}
	
	public void createMoveList(String[] moveNames, String[] moveTypes, int[] moveValues) {
		for(int i = 0; i < 4; i++) {
			Move currentMove = new Move(moveNames[i], moveTypes[i], moveValues[i]);
			moves.add(currentMove);
		}
	}
	
	
	
}
