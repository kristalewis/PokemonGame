package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.pokemon.model.Eevee;

public class EeveeTest {

	private Eevee eevee;
	
	private static final String TEST_EEVEE_NAME = "Eevee";
	
	@Before
	public void setUp() {
		eevee = new Eevee();
	}
	
	@Test
	public void eevee_toString_returns_eevee() {
		String result = eevee.toString();
		assertEquals(TEST_EEVEE_NAME, result);
	}
	
}
