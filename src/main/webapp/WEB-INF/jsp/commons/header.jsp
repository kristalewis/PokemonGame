<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<c:url value="/css/style.css" var="stylesheet" />
		<link href="${stylesheet}" rel="stylesheet">
		<title>Pokemon Battle Simulator</title>
	</head>
	
	<c:url var="homeUrl" value="/"/>
	<a href="${homeUrl}">
		<header class="header">
			<h2>Pokemon Battle Simulator</h2>
			<c:url value="/img/banner.jpg" var="bannerImage"/>
			<img src="${bannerImage}"/>
		</header>
	</a>
	<body>
		<div class="main">
	
	
	
	
	
