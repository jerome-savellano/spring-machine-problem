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
<title>${product.getName()}</title>
</head>
<body>
	<!-- <div class="container-fluid">
		<div class="row">
			<div class="col-md-12" style="padding: 5%;"></div>
		</div>
	</div> -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6" style="padding: 2%;">
				<form action="${pageContext.request.contextPath}/logout">
					<h1 class="page-header">
						${product.getName()}! <input type="submit"
							class="btn btn-warning btn-xs" value="Logout">
					</h1>
				</form>
				<dl>
					<dt>
						<h4>
							<strong>Product price</strong>
						</h4>
					</dt>
					<dd>
						<h4>
							<i><span style="color: green;">&#8369;
									${product.getPrice()}</span>
						</h4>
						</i>
					</dd>
					<dt>
						<h4>
							<strong>UPC</strong>
						</h4>
					</dt>
					<dd>
						<h4>
							<i>${product.getUpc()}</i>
						</h4>
					</dd>
					<dt>
						<h4>
							<strong>Category</strong>
						</h4>
					</dt>
					<dd>
						<h4>
							<i>${category}</i>
						</h4>
					</dd>
					<dt>
						<h4>
							<strong>Description</strong>
						</h4>
					</dt>
					<dd>
						<h5>
							<i>${product.getDescription()}</i>
						</h5>
					</dd>
					<dt>
						<h4>
							<strong>Quantity on cart</strong>
						</h4>
					</dt>
					<dd>
						<h4>
							<i>${quantity}</i>
						</h4>
					</dd>
				</dl>
				<form class="form-inline" action="productCart" method="post">
					<input type="hidden" name="upc" value="${product.getUpc()}">
					<div class="form-group">
						<label for="quantity">Qty:</label> <input type="number"
							class="form-control" name="quantity" min="1" max="100"
							data-bind="value:replyNumber" required>
					</div>
					<button type="submit" class="btn btn-primary">Add to cart</button>
				</form>
				<form action="${pageContext.request.contextPath}/customer" style="padding-top: 3%;">
					<button type="submit" class="btn btn-warning">Back to Home</button>
				</form>
			</div>
			<div class="col-md-6" style="padding: 2%;">
				<jsp:include page="cart.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>