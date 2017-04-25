<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class= "row">
	<header class="col-xs-12 col-md-12"><h2 class="text-center text-capitalize">Listas de alumnos</h2></header>
	<a class="btn btn-info" href="alumnos/addAlumno">Crear Alumno</a>
	<a class="btn btn-danger" href="#">Borrar Alumnos</a>
	<div class="col-xs-12 col-md-12">
		<!-- Si se anidan las columnas se tiene que meter otro row -->
		<div class="row">
			<div class="col-xs-1 col-md-1"><input id="selectall" type="checkbox"></div>
			<div class="col-xs-2 col-md-1">Nombre</div>
			<div class="col-xs-3 col-md-2">Apellidos</div>
			<div class="col-xs-2 col-md-2">Email</div>
			<div class="hidden-xs col-sm-1 col-md-1">Teléfono</div>
			<div class="hidden-xs hidden-sm  col-md-2">Dirección</div>
			<div class="col-xs-4  col-sm-3 col-md-2"></div>
		</div>
		<c:choose>
			<c:when test="${not empty listadoAlumnos}">
				<c:forEach var="alumno" items="${listadoAlumnos}">
					<div class="row">
						<div class="col-xs-1 col-md-1">
							<input type ="checkbox" value="${alumno.codigo}">
						</div>
						<div class="col-xs-2 col-md-1">${alumno.nombre}</div>
						<div class="col-xs-3 col-md-2">${alumno.apellidos}</div>
						<div class="col-xs-2 col-md-2">${alumno.email}</div>
						<div class="hidden-xs col-sm-1 col-md-1">${alumno.telefono}</div>
						<div class="hidden-xs hidden-sm  col-md-2">${alumno.direccion}</div>
						<div class="col-xs-4 col-sm-3 col-md-2">
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
