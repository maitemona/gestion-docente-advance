<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<jsp:include page="../includes/header.jsp" />
<main class="container-fluid">
	<section class= "row">
		<header class="col-xs-12"><h2>Listas de alumnos</h2></header>
		<a class="btn btn-info" href="alumnos/addAlumno">Crear Alumno</a>
		<a class="btn btn-danger" href="#">Borrar Alumnos</a>
		<div class="col-xs-12">
			<!-- Si se anidan las columnas se tiene que meter otro row -->
			<div class="row">
				<div class="col-xs-1"><input id="selectall" type="checkbox"></div>
				<div class="col-xs-2">Nombre</div>
				<div class="col-xs-3">Apellidos</div>
				<div class="col-xs-2">Email</div>
				<div class="col-xs-4"></div>
			</div>
			<c:choose>
				<c:when test="${not empty listadoAlumnos}">
					<c:forEach var="alumno" items="${listadoAlumnos}">
						<div class="row">
							<div class="col-xs-1">
								<input type ="checkbox" value="${alumno.codigo}">
							</div>
							<div class="col-xs-2">${alumno.nombre}</div>
							<div class="col-xs-3">${alumno.apellidos}</div>
							<div class="col-xs-2">${alumno.email}</div>
							<div class="col-xs-4">
								<a href="alumnos/${alumno.codigo}">Editar</a>
								<a href="alumnos/verInforme/${alumno.codigo}">Ver Informe</a>
								<a href="alumnos/deleteAlumno/${alumno.codigo}">Borrar</a>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise><!-- Cuando la lista NO tiene datos -->
				<div class="row">No se han encontrado alumnos en la Base de Datos</div>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
</main>	
<footer>
	Ipartek S. Coop.
</footer>
</body>
</html>