package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.pokemon.model.Squirtle;

public class SquirtleTest {

	private Squirtle squirtle;
	
	private static final String TEST_SQUIRTLE_NAME = "Squirtle";
	
	@Before
	public void setUp() {
		squirtle = new Squirtle();
	}
	
	@Test
	public void squirtle_toString_returns_squirtle() {
		String result = squirtle.toString();
		assertEquals(TEST_SQUIRTLE_NAME, result);
	}
	
}
