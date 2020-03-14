<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/commons/header.jsp" />

	<h4>Choose your battle type</h4>
	
	<div id="battleTypeFormDiv">
		<c:url value="/" var="$battleChoiceUrl"/>
		<form id="battleTypeForm" action="${battleChoiceUrl}" method="POST">
			<c:forEach items="${battleTypes}" var="battleType">
				<div class="form-check disabled">
					<input type="radio" value="${battleType}" name="battleChoice" 
						   id="${battleType}" class="form-check-input" required="required"/>
					<label class="form-check-label battleRadioLabel" for="${battleType}">${battleType}</label>
				</div>
			</c:forEach>
		<button type="submit" class="btn btn-secondary">Enter Arena</button>
		</form>
	</div>
	
<c:import url="/WEB-INF/jsp/commons/footer.jsp" />