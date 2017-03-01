<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" %>
<html>
<head>
	<title>Inicio</title>
</head>
<body>
<h3>
	<a href="?locale=es">				
		<spring:message code="idioma.castellano" text="castellano"/>
	</a>
	<a href="?locale=en">
		<spring:message code="idioma.ingles" text="ingles"/>
	</a>
	<a href="?locale=eu">
		<spring:message code="idioma.euskera" text="euskera"/>
	</a>
</h3>
<ul>
	<li><a href="<c:url value='/alumnos'/>">Ir a alumnos</a></li>
	<li><a href="<c:url value='/profesores'/>">Ir a Profesores</a></li>
	<li><a href="<c:url value='/clientes'/>">Ir a Clientes</a></li>
	<li><a href="<c:url value='/cursos'/>">Ir a Cursos</a></li>
</ul>
</body>
</html>
