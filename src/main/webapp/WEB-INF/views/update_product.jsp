<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>${product.getProduct().getName()}</title>
</head>
<body>
	<div class="container-fluid" style="padding: 2%;">

		<div class="row">
			<div class="col-md-12" style="padding-left: 15%; padding-right: 15%;">
				<form action="${pageContext.request.contextPath}/logout">
					<h2 class="page-header">
						Update product <input type="submit"
							class="btn btn-warning btn-xs" value="Logout">
					</h2>
				</form>
				<c:if test="${productCreated}">
					<div class="alert alert-success fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Success!</strong> Product has been updated!
					</div>
				</c:if>
				<form action="updateProduct" method="post">
					<div class="form-group">
						<label for="Name">Name </label> <input type="text" name="name"
							class="form-control" value="${product.getProduct().getName()}" required />
					</div>
					<div class="form-group">
						<label for="UPC">UPC </label> <input type="number" name="upc"
							class="form-control" value="${product.getProduct().getUpc()}"
							readonly="readonly" />
					</div>
					<div class="form-group">
						<label for="category">Category</label><input type="text" name="categoryName"
							class="form-control" value="${product.getProduct().getCategory().getName()}"
							readonly="readonly" />
					</div>
					<div class="form-group">
						<label for="description">Description</label>
						<textarea class="form-control" rows="5" name="description">${product.getProduct().getDescription()}</textarea>
					</div>
					<div class="form-group">
						<label for="price">Price </label> <input type="number" step="any"
							name="price" class="form-control" value="${product.getProduct().getPrice()}"
							required />
					</div>
					<div class="form-group">
						<label for="stock">Stock </label> <input type="number"
							name="stock" class="form-control" value="${product.getStock()}"
							required />
					</div>
					<div class="form-inline">
						<button type="submit" class="form-group btn btn-success">Update
							product</button>
						<a
							href="${pageContext.request.contextPath}/${user.getUserType().getType()}"
							class="form-group btn btn-secondary"> Back to Home</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>