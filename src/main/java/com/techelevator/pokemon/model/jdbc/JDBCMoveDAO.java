package com.techelevator.pokemon.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.pokemon.model.Move;
import com.techelevator.pokemon.model.MoveDAO;
import com.techelevator.pokemon.model.Pokemon;

public class JDBCMoveDAO implements MoveDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCMoveDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Move getRandomComMove(Pokemon pokemon) {
		List<Move> moves = getPokemonMoves(pokemon);
		Random rand = new Random();
		int ramdonChoice = rand.nextInt(4);
		return moves.get(ramdonChoice);
	}
	
	@Override 
	public List<Move> getPokemonMoves(Pokemon pokemon) {
		List<Move> moves = new ArrayList<Move>();
		String sql = "SELECT move.move_id, move_name, move_element, move_type, base_damage " +
					 "FROM pokemon " +
					 "JOIN pokemonmove ON pokemon.pokemon_id = pokemonmove.pokemon_id " +
					 "JOIN move ON pokemonmove.move_id = move.move_id " +
					 "WHERE pokemon.pokemon_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pokemon.getPokemonId());
		while(results.next()) {
			moves.add(createMoveFromRow(results));
		}
		return moves;
	}
	
	private Move createMoveFromRow(SqlRowSet row) {
		Move move = new Move();
		move.setMoveId(row.getInt("move_id"));
		move.setName(row.getString("move_name"));
		move.setElementType(row.getString("move_element"));
		move.setMoveType(row.getString("move_type"));
		move.setDamage(row.getInt("base_damage"));
		setMoveElement(move);
		return move;
	}
	
	private void setMoveElement(Move move) {
		String moveElement= "";
		String sql = "SELECT element.element_name " +
					 "FROM move " +
					 "JOIN element ON move.move_element = element.element_id " +
					 "WHERE move_id = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, move.getMoveId());
		while(result.next()) {
			moveElement = result.getString("element_name");
		}
		move.setElementType(moveElement);
	}
	
	
}
