<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/commons/header.jsp" />

	<h4>Let the Battle Begin!</h4>
	
	<c:if test="${pokemonTurn != null}">
		<p class="pokemonTurn">${pokemonTurn}</p>
	</c:if>
	
	
<div class="battleDiv">
	<c:choose>
		<c:when test="${sessionScope.battle.battleOver}">
			<p>${sessionScope.battle.winningPokemon.name} is the winner!</p>
			<p>Congratulations ${sessionScope.battle.winningPokemon.name}!</p>
		</c:when>
		<c:when test="${sessionScope.battle.pokemonAttacking.trained}">
			<c:set var="pokemonAttacking" value="${sessionScope.battle.pokemonAttacking}"/>
			<p>What will ${pokemonAttacking.name} do?</p>
			<c:url value="/battle" var="battleFormUrl"/>
			<form action="${battleFormUrl}" method="POST">
			<c:forEach items="${battle.pokemonAttacking.moves}" var="move">
				<div class="form-check disabled">
					<input type="radio" value="${move.name}" name="moveUsed" 
						   id="${move.name}" class="form-check-input" required="required"/>
					<label class="form-check-label" for="${move.name}">${move.name}</label>
				</div>
			</c:forEach>
			<button type="submit" class="btn btn-secondary battleButton">Attack</button>
			</form>
		</c:when>
		<c:otherwise>
			<p>${sessionScope.battle.pokemonAttacking.name} is ready to attack!</p>
			<c:url value="/battle" var="battleFormUrl"/>
			<form action="${battleFormUrl}" method="POST">
				<input type="hidden" name="pokemonAttacking" value="${sessionScope.battle.pokemonAttacking.name}"/>
				<button type="submit" class="btn btn-secondary battleButton">Do Com Move</button>
			</form>
		</c:otherwise>
	</c:choose>
</div>

<c:import url="/WEB-INF/jsp/commons/footer.jsp" />