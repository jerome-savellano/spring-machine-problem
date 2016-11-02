<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty rejectedProducts}">
	<div class="panel panel-danger" id="panel">
		<div class="panel-heading">
			<strong>Checkout failed!</strong> We have insufficient stock for the product/s below.
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
					<th>Stock left</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rejectedProducts}" var="product"
					varStatus="status">
					<tr class="text-align-center">
						<td>${product.getCartProduct().getProduct().getName()}</td>
						<td>${product.getCartProduct().getQuantity()}</td>
						<td style="color: red;">${product.getInventoryProduct().getStock()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>