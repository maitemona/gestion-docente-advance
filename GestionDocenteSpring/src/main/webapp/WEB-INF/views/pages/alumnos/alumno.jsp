<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<section class="row">
	<form:form action="save" method="POST" commandName="alumno">
		<c:if test="${!empty alumno}">
			<form:hidden path="codigo" />
		</c:if>
	   
	     <table>
	    <tr>
	        <td><form:label path="nombre" cssClass="sr-only">Nombre</form:label></td>
	        <td><form:input path="nombre" palceholder="Introduzca su nombre"/>
	        	<form:errors path="nombre" cssClass="text-danger" /></td> 
	    </tr>
	    <tr>
	        <td><form:label path="apellidos">Apellidos</form:label></td>
	        <td><form:input path="apellidos" /><form:errors path="apellidos"  /></td>
	    </tr>
	     <tr>
	        <td><form:label path="dni">DNI</form:label></td>
	        <td><form:input path="dni" /><form:errors path="dni"  /></td>
	    </tr>
	    <tr>
	       <td><form:label path="email">Email</form:label></td>
	       <td><form:input path="email" /><form:errors path="email"  /></td>
	    </tr>
	    <tr>
	        <td><form:label path="fNacimiento">Fecha Nacimiento</form:label></td>
	        <td><form:input path="fNacimiento" placeholder="dd/mm/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}" /><form:errors path="fNacimiento"  /></td>
	    </tr>
	    <tr>
	        <td><form:label path="telefono">Teléfono</form:label></td>
	        <td><form:input path="telefono" pattern="[0-9]{9}" />
	        <form:errors path="telefono"  /></td>
	    </tr>
	    <tr>
	        <td><form:label path="direccion">Dirección</form:label></td>
	        <td><form:input path="direccion" /></td>
	    </tr>
	     <tr>
	        <td><form:label path="poblacion">Población</form:label></td>
	        <td><form:input path="poblacion" /></td>
	    </tr>
	    
	       <tr>
	        <td><form:label path="codigopostal">Código Postal</form:label></td>
	        <td><form:input path="codigopostal" /></td>
	    </tr>
	    <tr>
	        <td><form:label path="nHermanos">Nº de Hermanos</form:label></td>
	        <td><form:input path="nHermanos" /><form:errors path="nHermanos"  /></td>
	    </tr>
	
	    <tr>
	        <td colspan="2">
	            <c:set var="men" value="Crear"/>
	            <c:if test="${alumno.codigo > 0}">
	            	 <c:set var="men" value="Editar"/>
	            </c:if>
	            <input type="submit" value = "${men}" />
	        </td>
	    </tr>
	</table>  
	
	</form:form>
</section>
