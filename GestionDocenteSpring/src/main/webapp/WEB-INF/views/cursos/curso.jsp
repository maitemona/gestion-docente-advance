<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Curso detalle</title>
</head>
<body>
<c:if test="${!empty curso}">
	<h2>Detalle del curso</h2>
	${curso.codigo}  ${curso.nombre}  ${curso.fInicio}  ${curso.fFin}
	
	<h2>Listado de alumnos asistentes a esta curso</h2>
	<c:if test="${!empty curso.modulos}">
		<c:forEach var="modul" items="${curso.modulos}">
			${modul.codigo}
			
	
		</c:forEach>
	</c:if>
</c:if>
</body>
</html>