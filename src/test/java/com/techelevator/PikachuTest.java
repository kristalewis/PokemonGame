package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.pokemon.model.Pikachu;

public class PikachuTest {

	private Pikachu pikachu;
	
	private static final String TEST_PIKACHU_NAME = "Pikachu";
	
	@Before
	public void setUp() {
		pikachu = new Pikachu();
	}
	
	@Test
	public void pikachu_toString_returns_pikachu() {
		String result = pikachu.toString();
		assertEquals(TEST_PIKACHU_NAME, result);
	}
	
}
