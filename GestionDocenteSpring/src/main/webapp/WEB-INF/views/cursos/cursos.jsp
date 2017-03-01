<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado de Cursos</title>
</head>
<body>
<c:forEach var="curso" items="${listadoCursos}">
	<div>
		<a href="<c:url value ='/cursos/${curso.codigo}'/>">${curso.nombre}</a>
		${curso.fInicio}
		${curso.fFin}
	</div>

</c:forEach>
</body>
</html>