<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/commons/header.jsp" />

<c:url value="/js/pokedex.js" var="pokedex" />
<script src="${pokedex}" type="text/javascript">
</script>

	<h4>Pokédex</h4>
		
	<div id="pokedexDiv">
		<p>Search for any generation 1 pokémon</p>
		<c:url value="/pokedex" var="pokedexSubmitUrl"/>
		<form onsubmit="return false">
			<input type="text" class="form-control" id="pokedexSearch" name="pokemon"/>
			<button class="btn btn-secondary">Submit</button>
		</form>
	</div>
	
	<div id="pokedexDisplay">
		<c:url value="/img/pokeball.png" var="pokeballImage"/>
		<img id="pokemonImage" src="${pokeballImage}"/>
		<div id="pokemonInfo">
			<p id="habitat"></p>
			<p id="pokedexDesc"></p>
		</div>
	</div>
	
<c:import url="/WEB-INF/jsp/commons/footer.jsp" />