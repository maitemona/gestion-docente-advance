<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="row">
	<c:choose>
		<c:when test="${not empty alumno}">
			<div>
				<p>${alumno.nombre} ${alumno.email}</p>
			</div>
			<c:choose>
			
				<c:when test="${not empty alumno.cursos}" >
					<table>
						<thead>
							<tr>
								<th>Nombre</th>
								<th>Horas</th>	
								<th>Precio</th>		
							</tr>
						</thead>
						<c:forEach var="curso" items="${alumno.cursos}">
						<tbody>
							<tr>
								<td>${curso.value.nombre}</td>
								<td>${curso.value.nHoras}</td>
								<td>${curso.value.precio}</td>
							</tr>
						</tbody>
						
						</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
					<p>El alumno no ha asistido ningun curso.</p>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
			No se han encontrado datos del alumno.
			</c:otherwise>
	</c:choose>
</section>