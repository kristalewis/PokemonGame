package com.techelevator.pokemon.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.pokemon.model.Battle;
import com.techelevator.pokemon.model.Pokemon;
import com.techelevator.pokemon.model.PokemonDAO;
import com.techelevator.pokemon.move.model.Move;
import com.techelevator.pokemon.move.model.MoveDAO;

@Controller
public class PokemonController {
	
	@Autowired
	private PokemonDAO pokemonDao;
	
	@Autowired
	private MoveDAO moveDao;
	
	@GetMapping(path="/")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping(path="/battle")
	public String getBattleChoicePage(ModelMap mm, HttpSession session) {
		session.invalidate();
		mm.put("battleTypes", Battle.BATTLE_TYPES);
		return "battle";
	}
	
	@PostMapping(path="/battle")
	public String submitBattleChoice(@RequestParam String battleChoice, HttpSession session) {
		for (String battleType : Battle.BATTLE_TYPES) {
			if (battleChoice.equals(battleType)) {
				Battle battle = new Battle();
				if (battleChoice.equals("Computer Vs. Computer")) {
					battle.setTypeOfBattle(Battle.COM_VS_COM);
				} else if (battleChoice.equals("Trainer Vs. Computer")) {
					battle.setTypeOfBattle(Battle.TRAINER_VS_COM);
				} else {
					battle.setTypeOfBattle(Battle.TRAINER_VS_TRAINER);
				}
				session.setAttribute("battle", battle);
				return "redirect:/choosepokemon";
			}
		}
		return "/battle";
	}
	
	@GetMapping(path="/choosepokemon")
	public String getChoosePokemonPage(ModelMap mm) {
		mm.put("pokemon", pokemonDao.getAllPokemon());
		return "choosepokemon";
	}
	
	@PostMapping(path="/choosepokemon")
	public String submitPokemonChoices(@RequestParam String firstPokemon, 
						@RequestParam String secondPokemon, HttpSession session,
						RedirectAttributes ra) {
		Battle battle = (Battle)session.getAttribute("battle");
		setUpPokemonAndBattle(pokemonDao.getPokemonById(Integer.parseInt(firstPokemon)), battle);
		setUpPokemonAndBattle(pokemonDao.getPokemonById(Integer.parseInt(secondPokemon)), battle);
		ra.addFlashAttribute("battleAnnouncement", battle.announceBattle());
		ra.addFlashAttribute("whoGoesFirst", battle.whoGoesFirst());
		battle.setWhoIsAttacking();
		session.setAttribute("battle", battle);
		return "redirect:/fightbattle";
	}

	@GetMapping(path="/fightbattle")
	public String beginBattle() {
		return "fightbattle";
	}
	
	@PostMapping(path="/fightbattle")
	public String battleMove(@RequestParam(required = false) String moveUsed, 
								RedirectAttributes ra, HttpSession session) {
		Battle battle = (Battle)session.getAttribute("battle");
		Move move;
		if (moveUsed != null) {
			move = moveDao.getMoveByName(moveUsed);
			ra.addFlashAttribute("pokemonTurn", battle.pokemonTurn(move));
		} else {
			move = moveDao.getRandomComMove(battle.getPokemonAttacking());
			ra.addFlashAttribute("pokemonTurn", battle.pokemonTurn(move));
		}
		battle.setWhoIsAttacking();
		session.setAttribute("battle", battle);
		return "redirect:/fightbattle";
	}
	
	@GetMapping(path="/pokedex")
	public String getPokedexPage(@RequestParam(required = false) String pokemon) {
		return "pokedex";
	}
	
	private void setUpPokemonAndBattle(Pokemon pokemon, Battle battle) {
		battle.addPokemonToBattle(pokemon);
		battle.setIfPokemonHasTrainer(pokemon);
		pokemon.setIsTrained();
		battle.setTrainerOrComNumber(pokemon);
		pokemon.setMoves(moveDao.getPokemonMoves(pokemon));
	}
	
}
