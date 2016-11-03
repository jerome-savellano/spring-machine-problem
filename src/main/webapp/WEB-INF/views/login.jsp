<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<fmt:setLocale value="${pageContext.request.locale}" />
<fmt:setBundle basename="com.qbryx.properties.language" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
html, body, .container-table {
	height: 100%;
	width: 100%;
}

.container-table {
	display: table;
}

.vertical-center-row {
	display: table-cell;
	vertical-align: middle;
}
</style>
</head>
<body>
	<div class="container container-table" id="login">
		<div class="row vertical-center-row">
			<div class="col-md-4 col-md-offset-4">
				<div
					style="background-color: #297f56; padding: 15px; border-radius: 5%;">
					<h1 style="text-align: center; color: white;">QBRYX BRYKaBRAX</h1>
					<c:if test="${not empty username}">
						<div class="alert alert-danger">
							<strong>Oops!</strong> User does not exist. Your
							username/password might be incorrect.
						</div>
					</c:if>
					<form:form modelAttribute="user" action="processLogin"
						method="post">
						<c:set var="usernameError">
							<form:errors path="username" />
						</c:set>
						<c:set var="passwordError">
							<form:errors path="password" />
						</c:set>
						<c:if test="${not empty usernameError}">
							<div class="alert alert-danger">
								<strong>Oops!</strong> ${usernameError}
							</div>
						</c:if>
						<c:if test="${not empty passwordError}">
							<div class="alert alert-danger">
								<strong>Oops!</strong> ${passwordError}
							</div>
						</c:if>
						<div class="form-group">
							<label for="email" style="color: white;">Username</label>
							<%-- <input
								type="text" class="form-control" name="username"
								value="${username}" required> --%>
							<form:input path="username" cssClass="form-control" required="required"/>
						</div>
						<div class="form-group">
							<label for="pwd" style="color: white;">Password</label> 
							<form:input path="password" cssClass="form-control" required="required"/>
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>