<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty checkedOutProducts}">
	<div class="panel panel-success" id="panel">
		<div class="panel-heading">
			<strong>Checkout success!</strong> You have successfully purchased the product/s below.
			<button type="button" class="close" data-target="#panel"
				data-dismiss="alert">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${checkedOutProducts}" var="product"
					varStatus="status">
					<tr>
						<td>${product.getProduct().getName()}</td>
						<td>${product.getQuantity()}</td>
						<td style="color: green;">&#8369; ${product.totalPrice()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</c:if>