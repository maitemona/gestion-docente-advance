<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
<c:set scope="request" var="seccion" value="Curso ${curso.nombre}"/>
<jsp:include page="../includes/header.jsp" />
<main class="container-fluid">
	<section class="row">
		<header class="col-xs-12"><h2>Datos del curso</h2></header>
		
			<div class="col-xs-12">
				<p>Nombre: ${curso.nombre}</p>
				<p>
					F.Inicio: <fmt:formatDate pattern="dd/MM/yyyy" value="${curso.fInicio}" /></p>
				<p>
					F.Fin: <fmt:formatDate pattern="dd/MM/yyyy" value="${curso.fFin}" />
				 </p>
				<p>Horas: ${curso.nHoras}</p>
				<p>Temario: ${curso.temario}</p>
				<p>Precio: ${curso.precio}</p>
				<p>Cliente: ${curso.cliente.nombre}</p>
			</div>
			<section class="col-xs-12">
				<header><h3>Listado de alumnos</h3></header>
					<c:forEach var="alumno" items="${curso.alumnos}">
						<div>
							<a href="<c:url value='/cursos/${curso.codigo}/alumnos/${alumno.codigo}'/>">${alumno.nombre} ${alumno.apellidos} ${alumno.email}</a>
						</div>
					</c:forEach>
		</section>
			
	</section>
</main>
<%@include file="../includes/footer.html" %>