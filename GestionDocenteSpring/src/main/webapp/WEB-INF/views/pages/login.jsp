<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
	<section>
		<c:url  var ="loginURL" value="/login.html" />
		<form:form action="${loginURL}" method="post">
			<div class="input-group input-sm">
				<label>
					Usuario:
				</label>
				<input type="text" id="userID" name="userID" required>
			</div>
			<div class="input-group input-sm">
				<label>
					Password:
				</label>
				<input type="password" id="password" name="password" required>
			</div>
			<input type="submit" value="Log IN" class="btn btn-block bg-primary" />
		</form:form>
	</section>
</main>

