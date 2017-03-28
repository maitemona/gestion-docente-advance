<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:message var="men" code="form.crear" text="nombre" />
<jsp:include page="../includes/header.jsp" />
<h2>Listado de cursos</h2>
<a class="btn btn-info" href="cursos/addCurso">Crear Curso</a>
<div class="conteiner">
	<div class= "row">
		<c:forEach var="curso" items="${listadoCursos}">
	</div>
		<div class="row">
			<a href="<c:url value ='/cursos/${curso.codigo}'/>">${curso.nombre}</a>
			<a  class="btn btn-info" href="cursos/${curso.codigo}">Editar Curso</a>
			<a  class="btn btn-danger" href="cursos/infome/${curso.codigo}">Ver Detalles Curso</a>
			<a  class="btn btn-info" href="cursos/deleteCurso/${curso.codigo}">Borrar</a>
		</div>
</div>
</c:forEach>




</body>
</html>