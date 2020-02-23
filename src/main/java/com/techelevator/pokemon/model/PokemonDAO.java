package com.techelevator.pokemon.model;

import java.util.List;

public interface PokemonDAO {

	public List<Pokemon> getAllPokemon();
	public Pokemon getPokemonById(int pokemonId);
	public String getPokemonType(Pokemon pokemon);
	
}
