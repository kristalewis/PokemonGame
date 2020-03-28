<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/commons/header.jsp" />

	<c:choose>
		<c:when test="${sessionScope.battle.typeOfBattle == 1}">
			<h4>Computer Vs. Computer Battle</h4>
		</c:when>
		<c:when test="${sessionScope.battle.typeOfBattle == 2}">
			<h4>Trainer Vs. Computer Battle</h4>
		</c:when>
		<c:otherwise>
			<h4>Trainer Vs. Trainer Battle</h4>
		</c:otherwise>
	</c:choose>
	
	<div class="choosePokemonDiv">
		<c:url value="/choosepokemon" var="pokemonChoice"/>
		<form action="${pokemonChoice}" method="POST">
		<div class="pokemonChoiceInputs">
			<div class="form-group pokemonInput">
			<c:choose>
				<c:when test="${sessionScope.battle.typeOfBattle == 1}">
					<p>Computer #1 Pok�mon</p>
				</c:when>
				<c:when test="${sessionScope.battle.typeOfBattle == 2}">
					<p>Trainer Pok�mon</p>
				</c:when>
				<c:otherwise>
					<p>Trainer #1 Pok�mon</p>
				</c:otherwise>
			</c:choose>
			<c:forEach items="${pokemon}" var="pokemon">
				<div class="form-check disabled">
					<input type="radio" value="${pokemon.pokemonId}" name="firstPokemon" 
						   id="${pokemon.name}" class="form-check-input" required="required"/>
					<label class="form-check-label" for="${pokemon.name}">${pokemon.name}</label>
				</div>
			</c:forEach>
			</div>
			<div class="form-group pokemonInput">
			<c:choose>
				<c:when test="${sessionScope.battle.typeOfBattle == 1}">
					<p>Computer #2 Pok�mon</p>
				</c:when>
				<c:when test="${sessionScope.battle.typeOfBattle == 2}">
					<p>Computer Pok�mon</p>
				</c:when>
				<c:otherwise>
					<p>Trainer #2 Pok�mon</p>
				</c:otherwise>
			</c:choose>
			<c:forEach items="${pokemon}" var="pokemon">
				<div class="form-check disabled">
					<input type="radio" value="${pokemon.pokemonId}" name="secondPokemon" 
						   id="${pokemon.name}" class="form-check-input" required="required"/>
					<label class="form-check-label" for="${pokemon.name}">${pokemon.name}</label>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="buttonDiv">
			<button type="submit" class="btn btn-secondary battleButton">Battle</button>
		</div>
		</form>
	</div>
	
<c:import url="/WEB-INF/jsp/commons/footer.jsp" />