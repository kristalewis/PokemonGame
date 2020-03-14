package com.techelevator.pokemon.move.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.pokemon.model.Pokemon;

@Component
public interface MoveDAO {
	
	public Move getRandomComMove(Pokemon pokemon);
	public List<Move> getPokemonMoves(Pokemon pokemon);
	public Move getMoveByName(String moveUsed);
}
