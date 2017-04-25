<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section class="row">
<form:form action="save" method="POST" commandName="cliente">
	<c:if test="${!empty cliente}">
		<form:hidden path="codigo" />
	</c:if>
   
     <table>
    <tr>
        <td>
			<form:label path="nombre">Nombre</form:label>
			<form:input path="nombre" />
			<form:errors path="nombre"  />
        </td> 
    </tr>
   
    
    <tr>
       <td>
	       <form:label path="email">Email</form:label>
	       <form:input path="email" />
	       <form:errors path="email"  />
       </td>
    </tr>
   
    <tr>
        <td>
	        <form:label path="telefono">Teléfono</form:label>
	        <form:input path="telefono"  pattern="[0-9]{9}" />
	        <form:errors path="telefono"  />
        </td>
    </tr>
    <tr>
        <td>
	        <form:label path="direccion">Dirección</form:label>
	        <form:input path="direccion" />
        </td>
    </tr>
    <tr>
	    <td>
			<select name="selector">
			<option selectd value= 1>DNI</option>
			<option value= 2>CIF</option>
			</select>
			
			<form:label path="identificador">Identificador</form:label>
      		<form:input path="identificador" /><form:errors path="identificador"  />
	 	</td>
 	<tr>
    <tr>
        <td colspan="2">
            <c:set var="men" value="Crear"/>
            <c:if test="${cliente.codigo > 0}">
            	 <c:set var="men" value="Editar"/>
            </c:if>
            <input type="submit" value = "${men}" />
        </td>
    </tr>
</table>  

</form:form>
</section>