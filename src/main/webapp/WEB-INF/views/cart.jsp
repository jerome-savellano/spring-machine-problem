<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${checkoutSuccess}">
	<div class="alert alert-success fade in">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Checkout successful!</strong> Continue to shop at QBRYX
		BRYKaBRAX!
	</div>
</c:if>
<c:choose>
	<c:when test="${empty productsInCart}">
		<h1 class="text-center text-muted">Your cart is empty. Start
			shopping!</h1>
	</c:when>
	<c:otherwise>
		<h3 class="text-success">Products in cart</h3>
		<form class="form-inline" action="${pageContext.request.contextPath}/customer/checkout" method="post">
			<div class="form-group">
				<input type="hidden" name="cartId" value="${customer.getCartId()}">
				<label for="email"><h4>Total Amount:</h4></label> <input type="text"
					class="form-control" name="totalAmount"
					value="&#8369; ${totalAmount}" disabled>
			</div>
			<input type="hidden" name="page" value="${page}">
			<button type="submit" class="btn btn-primary">Checkout</button>
		</form>
		<form action="${pageContext.request.contextPath}/customer/removeProduct" method="post">
			<input type="hidden" name="page" value="${page}">
			<table class="table">
				<thead>
					<tr>
						<th>Name</th>
						<th>Quantity</th>
						<th>Amount</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productsInCart}" var="product"
						varStatus="status">
						<input type="hidden" class="form-control" name="upc"
								value="${product.getUpc()}">
						<tr class="warning">
							<td>${product.getName()}</td>
							<td>${product.getQuantity()}</td>
							<td style="color: green;">&#8369; ${product.getTotal()}</td>
							<td><input type="submit" class="btn btn-warning btn-xs"
								value="Remove from cart"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</c:otherwise>
</c:choose>