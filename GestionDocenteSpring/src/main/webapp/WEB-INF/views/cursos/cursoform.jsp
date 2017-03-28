<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:message var="men" code="form.crear" text="nombre" />
<c:if test="${curso.codigo > 0}" >
	<spring:message var="men"  code="form.editar" text="nombre" />
</c:if>  
<spring:message var="seccion" code="curso.titulo" text="curso" />
<c:set scope="request" var="seccion" value="${men} ${seccion}"/>
<jsp:include page="../includes/header.jsp" />
<main>
<form:form action="save" method="post" modelAttribute="curso" cssClass="form-horizontal">
		<c:if test="${!empty curso}">
			<form:hidden path="codigo"/>
		</c:if>
		<div class="form-group">
			<form:label cssClass="control-label hidden-xs col-sm-2" path="nombre">
				<spring:message code="curso.nombre" />:
			</form:label>
			<div class="col-sm-7">
				<form:input placeholder="Introduzca su nombre" path="nombre" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="nombre" cssClass="text-danger" />
			</div>
		</div>
	<div class="form-group">
			<form:label cssClass="control-label hidden-xs col-sm-2" path="temario">
				<spring:message code="curso.temario" />:
			</form:label>
			<div class="col-sm-7">
				<form:input placeholder="Introduzca su temario" path="nombre" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="temario" cssClass="text-danger" />
			</div>
		</div>
			<div class="form-group">
			<form:label cssClass="control-label hidden-xs col-sm-2" path="nHoras">
				<spring:message code="curso.nHoras" />:
			</form:label>
			<div class="col-sm-7">
				<form:input placeholder="Introduzca su nº horas" path="nHoras" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="nHoras" cssClass="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label hidden-xs col-sm-2" path="fInicio">
				<spring:message code="curso.fInicio" />:
			</form:label>
			<div class="col-sm-7">
				<form:input  placeholder="dd/MM/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" path="fInicio" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="fInicio" cssClass="text-danger" />
			</div>
		</div>
		<div class="form-group">
			<form:label cssClass="control-label hidden-xs col-sm-2" path="fFin">
				<spring:message code="curso.fFin" />:
			</form:label>
			<div class="col-sm-7">
				<form:input  placeholder="dd/MM/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" path="fFin" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="fFin" cssClass="text-danger" />
			</div>
		</div>
		
		<div class="form-group">
		<input type="submit" value="${men}">
		</div>
	</form:form>
	
</main>
<%@include file="../includes/footer.html" %>