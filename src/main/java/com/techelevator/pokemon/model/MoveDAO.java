package com.techelevator.pokemon.model;

import java.util.List;

public interface MoveDAO {
	
	public Move getRandomComMove(Pokemon pokemon);
	public List<Move> getPokemonMoves(Pokemon pokemon);
	
}
