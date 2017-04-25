<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<nav >
	<ul>
		<li>
			<a href="<c:url value='/alumnos'/>">
				<spring:message code="menu.alumnos" text="G.Alumnos" />
			</a>
		</li>
		<li>
			<a href="<c:url value='/profesores'/>">
				<spring:message code="menu.profesores" text="G.Profesores" />
			</a>
		</li>
		<li><a href="<c:url value='/clientes'/>">
			<spring:message code="menu.clientes" text="G.Clientes" />
			</a>
		</li>
		<li>
			<a href="<c:url value='/cursos'/>">
			 G.Cursos
			</a>
		</li>
		
	</ul>
</nav>
<div class="btn-group">
	<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Idiomas<span class="caret"></span></button>
	<ul class="dropdown-menu" role="menu">
		 <li><a  href="?locale=es">				
			<spring:message code="idioma.castellano" text="castellano"/>
			</a>
		</li>
		<li>
			<a href="?locale=en">
				<spring:message code="idioma.ingles" text="ingles"/>
			</a>
		</li>
		<li>
			<a href="?locale=eu">
				<spring:message code="idioma.euskera" text="euskera"/>
			</a>
		</li>
	</ul>
</div>
<div>
 <sec:authorize access="isAnonymous()">
      <form method="POST" action="<c:url value='/login'/>">
          Username: <input name="userId" type="text" value="${SPRING_SECURITY_LAST_USERNAME}" /> 
          Password: <input name="password" type="password" />
          <!-- 
          <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
           --> 
          <input type="submit" value="Login" />
      </form>
 	</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <a class="btn btn-default" href="<c:url value="/logout" />">Logout</a>
</sec:authorize>
</div>
<div class="conatiner">
	<c:if test="${not empty mensaje}">
		<div class="${mensaje.type.styles}">
			<a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>
			<strong>${mensaje.msg}</strong>
		</div>
	</c:if>
</div>
