	document.addEventListener('DOMContentLoaded', () => {
		let submitButton = document.querySelector('button');
		submitButton.addEventListener('click', (event) => {
			event.preventDefault();
			searchPokedex();
		});
	});
	
	let display;
	let habitat;
	
	const searchPokedex = async() => {
		const pokemonName = document.getElementById('pokedexSearch').value.toLowerCase();
		display = document.getElementById('pokedexDesc');
		habitat = document.getElementById('habitat');
		habitat.innerText = '';
		
		if (pokemonName === '') {
			display.innerText = 'Pokemon name or number required.';
		} else {
			const response = await fetch(`https://pokeapi.co/api/v2/pokemon-species/${pokemonName}/`);
			const status = await response.status;
			if (status === 200) {
				const pokedexJson = await response.json(); //extract JSON from the http response
				processApiCall(pokedexJson);
			} else {
				display.innerText = 'Pokemon not found';
			}
		}
	}
	
	function processApiCall(pokedexJson) {
		let yellowPokemonFound = false;
		pokedexJson.flavor_text_entries.forEach(entry => {
			if (entry.language.name === 'en' && entry.version.name === 'yellow') {
				displayInformation(pokedexJson, entry);
				yellowPokemonFound = true;
				console.log(entry.flavor_text);
			}
		});
		if (!yellowPokemonFound) {
			display.innerText = 'Not a gen 1 pokemon.';
		}
	}
	
	function displayInformation(pokedexJson, entry) {
		habitat.innerText = 'Habitat : ' + pokedexJson.habitat.name;
		formatAndSaveDescription(entry);
		createPokeImage(pokedexJson.id);
	}
	
	function formatAndSaveDescription(entry) {
		let displayDescription = JSON.stringify(entry.flavor_text);
		displayDescription = displayDescription.replace(/\\n/g, ' ');
		displayDescription = displayDescription.replace(/\\f/g, ' ');
		displayDescription = displayDescription.replace(/"/g, '');
		display.innerText = displayDescription;
	}
	
	function createPokeImage(pokeID){
		let pokeImageUrl = `https://pokeres.bastionbot.org/images/pokemon/${pokeID}.png`;
		const pokemonImage = document.getElementById('pokemonImage');
		pokemonImage.setAttribute('src', pokeImageUrl);
	}


