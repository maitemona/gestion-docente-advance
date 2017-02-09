<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profesor Nuevo</title>
</head>
<body>
<form:form action="save" method="POST" commandName="profesor">
	<c:if test="${!empty profesor}">
		<form:hidden path="codigo" />
	</c:if>
   
     <table>
    <tr>
        <td><form:label path="nombre">Nombre</form:label></td>
        <td><form:input path="nombre" />
        	<form:errors path="nombre"  /></td> 
    </tr>
   <tr>
        <td><form:label path="direccion">Dirección</form:label></td>
        <td><form:input path="direccion" /></td>
    </tr>
    
    <tr>
       <td><form:label path="email">Email</form:label></td>
       <td><form:input path="email" /><form:errors path="email"  /></td>
    </tr>
   
    <tr>
        <td><form:label path="telefono">Teléfono</form:label></td>
        <td><form:input path="telefono" /></td>
    </tr>
    
    
     
  
    <tr>
        <td colspan="2">
            <c:set var="men" value="Crear"/>
            <c:if test="${profesor.codigo > 0}">
            	 <c:set var="men" value="Editar"/>
            </c:if>
            <input type="submit" value = "${men}" />
        </td>
    </tr>
</table>  

</form:form>
</body>
</html>