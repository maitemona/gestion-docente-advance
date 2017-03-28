<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Datos del curso</title>
</head>
<body>
<c:if test="${!empty curso}">
	<h2>Detalle del curso</h2>
		<p>Nombre: ${curso.nombre}</p>
			<p>F.Inicio: <fmt:formatDate pattern="dd/MM/yyyy" 
	            value="${curso.fInicio}" /></p>
			<p>F.Fin: <fmt:formatDate pattern="dd/MM/yyyy" 
	            value="${curso.fFin}" /></p>
				<h2>Listado de Alumnos  a esta curso</h2>
		<!-- 	<c:forEach var="alumno" items="${modulo.imparticion.alumnos}">
				${alumno.nombre} <a href="../alumnos/${alumno.codigo}">ir a</a>
				</br>	
			</c:forEach> -->
  	<c:forEach var="alumno" items="${curso.alumnos}">
			<div>
				${alumno.nombre}
				 <a href="<c:url value='/cursos/${curso.codigo}/alumnos/${alumno.codigo}'/>">ir a</a>
			</div>
		</c:forEach>
		

</c:if>
</body>
</html>