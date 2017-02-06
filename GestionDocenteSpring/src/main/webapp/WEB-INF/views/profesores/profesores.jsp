<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
	<h1>Gestion Docente - Listado Profesores</h1>
</header>
<main>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>	
				<th></th>		
			</tr>
		</thead>
		<tbody>
	<c:choose>
		<c:when test="${not empty listadoProfesores}"><!-- Cuando la lista tiene datos -->
			<c:forEach var="profesor" items="${listadoProfesores}">
				<tr>
					<td>${profesor.nombre}</td>
					<td>${profesor.apellidos}</td>
					<td><a href="">Editar</a></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise><!-- Cuando la lista NO tiene datos -->
			<tr>
				<td colspan="3">No se han encontrado profesores en la Base de Datos</td>
			</tr>
		</c:otherwise>
	</c:choose>
		</tbody>
	</table>
</main>
<footer>
	Ipartek S. Coop.
</footer>
</body>

</html>