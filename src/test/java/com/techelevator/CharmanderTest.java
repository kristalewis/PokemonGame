package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.techelevator.pokemon.model.Charmander;

public class CharmanderTest {

	private Charmander charmander;
	
	private static final String TEST_CHARMANDER_NAME = "Charmander";
	
	@Before
	public void setUp() {
		charmander = new Charmander();
	}
	
	@Test
	public void charmander_toString_returns_charmander() {
		String result = charmander.toString();
		assertEquals(TEST_CHARMANDER_NAME, result);
	}
	
}
