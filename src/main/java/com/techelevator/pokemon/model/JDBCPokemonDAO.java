package com.techelevator.pokemon.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCPokemonDAO implements PokemonDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCPokemonDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		List<Pokemon> allPokemon = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM pokemon;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			allPokemon.add(createPokemonFromRow(results));
		}
		return allPokemon;
	}
	
	@Override
	public Pokemon getPokemonById(int pokemonId) {
		Pokemon pokemon = new Pokemon();
		String sql = "SELECT * FROM pokemon WHERE pokemon_id = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, pokemonId);
		while(result.next()) {
			pokemon = createPokemonFromRow(result);
		}
		return pokemon;
	}
	
	@Override
	public String getPokemonType(Pokemon pokemon) {
		String typeResult = "";
		String sql = "SELECT element_name " +
					 "FROM element " +
					 "JOIN pokemon ON element.element_id = pokemon.pokemon_type " +
					 "WHERE pokemon_id = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, pokemon.getPokemonId());
		while(result.next()) {
			typeResult = result.getString("element_name");
		}
		return typeResult;
	}

	private Pokemon createPokemonFromRow(SqlRowSet row) {
		Pokemon pokemon = new Pokemon();
		pokemon.setPokemonId(row.getInt("pokemon_id"));
		pokemon.setName(row.getString("pokemon_name"));
		pokemon.sethP(row.getInt("hP"));
		pokemon.setType(row.getString("pokemon_type"));
		setPokemonStrengths(pokemon);
		setPokemonWeaknesses(pokemon);
		return pokemon;
	}
	
	private void setPokemonWeaknesses(Pokemon pokemon) {
		List<String> weaknesses = new ArrayList<String>();
		String sql = "SELECT element.element_name " +
					 "FROM pokemon " +
					 "JOIN pokemonweaknesses ON pokemon.pokemon_id = pokemonweaknesses.pokemon_id " +
					 "JOIN element ON pokemonweaknesses.element_id = element.element_id " +
					 "WHERE pokemon.pokemon_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pokemon.getPokemonId());
		while(results.next()) {
			weaknesses.add(results.getString("element_name"));
		}
		pokemon.setWeaknesses(weaknesses);
	}	
	
	private void setPokemonStrengths(Pokemon pokemon) {
		List<String> strengths = new ArrayList<String>();
		String sql = "SELECT element.element_name " +
					 "FROM pokemon " +
					 "JOIN pokemonstrengths ON pokemon.pokemon_id = pokemonstrengths.pokemon_id " +
					 "JOIN element ON pokemonstrengths.element_id = element.element_id " +
					 "WHERE pokemon.pokemon_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pokemon.getPokemonId());
		while(results.next()) {
			strengths.add(results.getString("element_name"));
		}
		pokemon.setStrengths(strengths);
	}	
	
}
