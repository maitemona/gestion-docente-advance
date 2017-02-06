<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Docente - Listado Alumnos</title>
</head>
<body>
<header>
	<h1>Gestion Docente - Listado Alumnos</h1>
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
		<c:when test="${not empty listadoAlumnos}"><!-- Cuando la lista tiene datos -->
			<c:forEach var="alumno" items="${listadoAlumnos}">
			<tr>
				<td>${alumno.nombre}</td>
				<td>${alumno.apellidos}</td> 
				<td><a href="">Editar</a></td>
			</tr>
			</c:forEach>
		</c:when>
		<c:otherwise><!-- Cuando la lista NO tiene datos -->
			<tr>
				<td colspan="3">No se han encontrado alumnos en la Base de Datos</td>
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
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestion Docente - Listado Alumnos</title>
</head>
<body>
<header>
	<h1>Gestion Docente -Listado alumnos</h1>
</header>
<main></main>

<footer>
</footer>
</body>
>>>>>>> origin/mmonasterio
</html>