<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
<title>Create product</title>
</head>
<body>
	<div class="container-fluid" style="padding: 2%;">
		<div class="row">
			<div class="col-md-12" style="padding-left: 15%; padding-right: 15%;">
				<h2 class="page-header">
					Create product <a href="${pageContext.request.contextPath}/logout"
						class="btn btn-warning btn-xs">Logout</a>
				</h2>
				<form:form
					action="${pageContext.request.contextPath}/${user.getUserType().getType()}/createProduct"
					method="post" modelAttribute="inventoryProductHelper">
					<spring:bind path="name">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="Name">Name </label>
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name" cssClass="text-danger" />
						</div>
					</spring:bind>
					<spring:bind path="upc">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="UPC">UPC </label>
							<form:input path="upc" cssClass="form-control" />
							<form:errors path="upc" cssClass="text-danger" />
						</div>
					</spring:bind>
					<spring:bind path="categoryName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="category">Category</label>
							<form:select cssClass="form-control" path="categoryName">
								<option selected disabled value="">SELECT CATEGORY</option>
								<c:forEach items="${categories}" var="item">
									<option>${item.getName()}</option>
								</c:forEach>
							</form:select>
							<form:errors path="categoryName" cssClass="text-danger"></form:errors>
						</div>
					</spring:bind>
					<div class="form-group">
						<label for="description">Description</label>
						<form:textarea cssClass="form-control" rows="5" path="description"></form:textarea>
					</div>
					<spring:bind path="price">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="price">Price</label>
							<%-- <form:input path="price" cssClass="form-control" /> --%>
							<input class="form-control" />
							<form:errors path="price" cssClass="text-danger"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="stock">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="stock">Stock</label>
							<form:input path="stock" cssClass="form-control" />
							<form:errors path="stock" cssClass="text-danger"></form:errors>
						</div>
					</spring:bind>
					<div class="form-inline">
						<button type="submit" class="form-group btn btn-success">Create
							product</button>
						<a
							href="${pageContext.request.contextPath}/${user.getUserType().getType()}"
							class="form-group btn btn-secondary"> Back to Home</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>