package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.pokemon.move.model.Move;

public class MoveTest {

	private Move normalMove;
	private Move fireMove;
	private Move grassMove;
	private Move waterMove;
	private Move electricMove;
	private List<Move> moves = new ArrayList<Move>();
	private static final String TEST_NORMAL_MOVE_NAME = "Normal Move";
	private static final String TEST_FIRE_MOVE_NAME = "Fire Move";
	private static final String TEST_GRASS_MOVE_NAME = "Grass Move";
	private static final String TEST_WATER_MOVE_NAME = "Water Move";
	private static final String TEST_ELECTRIC_MOVE_NAME = "Electric Move";
	private static final String TEST_NORMAL_TYPE = "normal";
	private static final String TEST_FIRE_TYPE = "fire";
	private static final String TEST_GRASS_TYPE = "grass";
	private static final String TEST_WATER_TYPE = "water";
	private static final String TEST_ELECTRIC_TYPE = "electric";
	private static final int TEST_DAMAGE = 3;
	
//	@Before
//	public void setUp() {
//		normalMove = new Move(TEST_NORMAL_MOVE_NAME, TEST_NORMAL_TYPE, TEST_DAMAGE);
//		moves.add(normalMove);
//		fireMove = new Move(TEST_FIRE_MOVE_NAME, TEST_FIRE_TYPE, TEST_DAMAGE);
//		moves.add(fireMove);
//		grassMove = new Move(TEST_GRASS_MOVE_NAME, TEST_GRASS_TYPE, TEST_DAMAGE);
//		moves.add(grassMove);
//		waterMove = new Move(TEST_WATER_MOVE_NAME, TEST_WATER_TYPE, TEST_DAMAGE);
//		moves.add(waterMove);
//		electricMove = new Move(TEST_ELECTRIC_MOVE_NAME, TEST_ELECTRIC_TYPE, TEST_DAMAGE);
//		moves.add(electricMove);
//	}
//	
//	@Test
//	public void getName_returns_Tackle() {
//		String result = moves.get(0).getName();
//		assertEquals(TEST_NORMAL_MOVE_NAME, result);
//	}
//	
//	@Test
//	public void getElementType_returns_fire_for_fireMove() {
//		String nameResult = moves.get(1).getName();
//		String elementResult = moves.get(1).getElementType();
//		assertEquals(TEST_FIRE_MOVE_NAME, nameResult);
//		assertEquals(TEST_FIRE_TYPE, elementResult);
//	}
//	
//	@Test
//	public void getDamage_returns_3() {
//		int result = moves.get(0).getDamage();
//		assertEquals(TEST_DAMAGE, result);
//	}
//	
//	@Test
//	public void isAttackLoweringMove_for_Growl_returns_true() {
//		Move move = new Move("Growl", "normal", 0);
//		assertTrue(move.isAttackLoweringMove());
//	}
//	
//	@Test
//	public void isAttackLoweringMove_for_Tackle_returns_false() {
//		Move move = new Move("Tackle", "normal", 0);
//		assertFalse(move.isAttackLoweringMove());
//	}
//	
//	@Test
//	public void isDefenseLoweringMove_returns_true_for_Tail_Whip() {
//		Move move = new Move("Tail Whip", "normal", 0);
//		assertTrue(move.isDefenseLoweringMove());
//	}
//	
//	@Test
//	public void isDefenseLoweringMove_returns_true_for_Leer() {
//		Move move = new Move("Leer", "normal", 0);
//		assertTrue(move.isDefenseLoweringMove());
//	}
//
//	@Test
//	public void isDefenseLoweringMove_returns_false_for_Tackle() {
//		Move move = new Move("Tackle", "normal", 0);
//		assertFalse(move.isDefenseLoweringMove());
//	}
//	
//	
//	
//	
	
}
