<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" %>
<html>
<head>
	<title>Inicio</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<header>
	<h1>${nombreApp} - ${seccion}</h1>
	<nav>
		<ul>
			<li>
				<a href="<c:url value='/alumnos'/>">
					<spring:message code="menu.alumnos" text="G.Alumnos" />
				</a>
			</li>
			<li>
				<a href="<c:url value='/profesores'/>">
					<spring:message code="menu.profesores" text="G.Profesores" />
				</a>
			</li>
			<li><a href="<c:url value='/clientes'/>">
				<spring:message code="menu.clientes" text="G.Clientes" />
				</a>
			</li>
			<li>
				<a href="<c:url value='/cursos'/>">
				 G.Cursos
				</a>
			</li>
		</ul>
	</nav>
	<a href="?locale=es">				
		<spring:message code="idioma.castellano" text="castellano"/>
	</a>
	<a href="?locale=en">
		<spring:message code="idioma.ingles" text="ingles"/>
	</a>
	<a href="?locale=eu">
		<spring:message code="idioma.euskera" text="euskera"/>
	</a>
</header>