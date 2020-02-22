package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.techelevator.pokemon.model.Bulbasaur;

public class BulbasaurTest {

	private Bulbasaur bulbasaur;
	
	private static final String TEST_TOSTRING_NAME = "Bulbasaur";
	
	@Before
	public void setUp() {
		bulbasaur = new Bulbasaur();
	}
	
	@Test
	public void toString_returns_bulbasaur() {
		String result = bulbasaur.toString();
		assertEquals(TEST_TOSTRING_NAME, result);
	}
	
}
