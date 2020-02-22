package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.pokemon.model.Bulbasaur;
import com.techelevator.pokemon.model.Charmander;
import com.techelevator.pokemon.model.Eevee;
import com.techelevator.pokemon.model.Pikachu;
import com.techelevator.pokemon.model.Pokemon;
import com.techelevator.pokemon.model.Squirtle;

public class PokemonTest {

	private List<Pokemon> pokemonList;
	private Pokemon pokemon;
	
//	private static final String TEST_BULBASAUR_NAME = "Bulbasaur";
//	private static final String TEST_CHARMANDER_NAME = "Charmander";
//	private static final String TEST_SQUIRTLE_NAME = "Squirtle";
//	private static final String TEST_EEVEE_NAME = "Eevee";
//	private static final String TEST_PIKACHU_NAME = "Pikachu";
//	private static final int TEST_BULBASAUR_HP = 24;
//	private static final int TEST_CHARMANDER_HP = 23;
//	private static final int TEST_SQUIRTLE_HP = 24;
//	private static final int TEST_EEVEE_HP = 24;
//	private static final int TEST_PIKACHU_HP = 23;
//	private static final int TEST_CHANGED_HP = 10;
//	private static final String TEST_BULBASAUR_TYPE = "grass/poison";
//	private static final String TEST_CHARMANDER_TYPE = "fire";
	
	
	
	@Before
	public void setUp() {
		pokemonList = new ArrayList<Pokemon>();
		pokemonList.add(new Bulbasaur());
		pokemonList.add(new Charmander());
		pokemonList.add(new Squirtle());
		pokemonList.add(new Eevee());
		pokemonList.add(new Pikachu());
		
		pokemon = new Pokemon();
	}
	
	@Test
	public void getName_returns_respective_names() {
		assertEquals("Bulbasaur", pokemonList.get(0).getName());
		assertEquals("Charmander", pokemonList.get(1).getName());
		assertEquals("Squirtle", pokemonList.get(2).getName());
		assertEquals("Eevee", pokemonList.get(3).getName());
		assertEquals("Pikachu", pokemonList.get(4).getName());
	}
	
	@Test
	public void getHp_returns_respective_hps() {
		assertEquals(24, pokemonList.get(0).gethP());
		assertEquals(23, pokemonList.get(1).gethP());
		assertEquals(24, pokemonList.get(2).gethP());
		assertEquals(24, pokemonList.get(3).gethP());
		assertEquals(23, pokemonList.get(4).gethP());
	}
	
	@Test
	public void setHp_sets_pokemons_hp() {
		for (int i = 0; i < pokemonList.size(); i++) {
			pokemonList.get(i).sethP(10);
		}
		assertEquals(10, pokemonList.get(0).gethP());
		assertEquals(10, pokemonList.get(1).gethP());
		assertEquals(10, pokemonList.get(2).gethP());
		assertEquals(10, pokemonList.get(3).gethP());
		assertEquals(10, pokemonList.get(4).gethP());
	}
	
	@Test
	public void getType_returns_respective_types() {
		assertEquals("grass/poison", pokemonList.get(0).getType());
		assertEquals("fire", pokemonList.get(1).getType());
		assertEquals("water", pokemonList.get(2).getType());
		assertEquals("normal", pokemonList.get(3).getType());
		assertEquals("electric", pokemonList.get(4).getType());
	}
	
	@Test
	public void setHasTrainer_and_hasTrainer_work_correctly() {
		pokemon.setHasTrainer(true);
		assertTrue(pokemon.hasTrainer());
	}
	
	@Test
	public void setTrainerOrCom_and_getTrainerOrCom_work_correctly() {
		pokemon.setTrainerOrCom(5);
		assertEquals(5, pokemon.getTrainerOrCom());
	}
	
	@Test
	public void set_attackStatChange_to_lower_value() {
		pokemon.setAttackStatChange(-2);
		assertEquals(-2, pokemon.getAttackStatChange());
	}
	
	@Test
	public void set_attackStatChange_to_higher_value() {
		pokemon.setAttackStatChange(3);
		assertEquals(3, pokemon.getAttackStatChange());
	}
	
	@Test
	public void getWeaknesses_returns_correct_weaknesses() {
		List<String> bulbasaurWeaknesses = new ArrayList<String>();
		bulbasaurWeaknesses.add("fire");
		bulbasaurWeaknesses.add("ice");
		bulbasaurWeaknesses.add("flying");
		bulbasaurWeaknesses.add("bug");
		java.util.Collections.sort(bulbasaurWeaknesses);
		List<String> pokemonWeaknesses = pokemonList.get(0).getWeaknesses();
		java.util.Collections.sort(pokemonWeaknesses);
		for(int i = 0; i < pokemonWeaknesses.size(); i++) {
			assertEquals(bulbasaurWeaknesses.get(i), pokemonWeaknesses.get(i));
		}
	}

	@Test
	public void get_Strengths_returns_correct_strengths() {
		List<String> bulbasaurstrengths = new ArrayList<String>();
		bulbasaurstrengths.add("water");
		bulbasaurstrengths.add("grass");
		bulbasaurstrengths.add("electric");
		bulbasaurstrengths.add("ground");
		java.util.Collections.sort(bulbasaurstrengths);
		List<String> pokemonStrengths = pokemonList.get(0).getStrengths();
		java.util.Collections.sort(pokemonStrengths);
		for(int i = 0; i < pokemonStrengths.size(); i++) {
			assertEquals(bulbasaurstrengths.get(i), pokemonStrengths.get(i));
		}
	}
	
	@Test
	public void pickMove_returns_0_1_2_or_3() {
		int result = pokemon.pickMove();
		assertTrue(0 <= result && result <= 3);
	}
	
	
}
