<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">

<jsp:directive.include file="/resources/head.html"/>

<body>

<%@ include file="/resources/jsp/header.jsp" %>


    <c:if test="${order != null}">
    <!-- Checkout Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5">
            <div class="col-lg-8">
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Order Details</h4>
                    </div>
                    <!-- Order Information -->
                    <div class="card-body">
                        <div class="mb-3">
                            <p><strong>Order ID:</strong> #${order.id}</p>
                            <p><strong>Date:</strong> ${order.orderDate}</p>
                            <p><strong>Customer:</strong>${order.customer.name}</p>
                            <p><strong>Status:</strong> Shipped</p>
                        </div>

                        <table class="table table-bordered order-items-table">
                            <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Subtotal</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- Example item row -->
                            <c:forEach items="${order.orderItems}" var="item">
                            <tr>
                                <td>${item.product.name}</td>
                                <td>${item.quantity}</td>
                                <td>$${item.currentPrice}</td>
                                <td>$<fmt:formatNumber value="${item.currentPrice*item.quantity}" type="number" minFractionDigits="2" maxFractionDigits="2" />
                                </td>
                            </tr>
                            </c:forEach>
                            <!-- Add more items as needed -->
                            </tbody>
                        </table>
                    </div>

                    <!-- Order Totals -->
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mb-3">
                            <h6 class="font-weight-medium">Subtotal</h6>
                            <h6 class="font-weight-medium">$<fmt:formatNumber value="${order.totalPrice-10}" type="number" minFractionDigits="2" maxFractionDigits="2" />
                            </h6>
                        </div>
                        <div class="d-flex justify-content-between mb-3">
                            <h6 class="font-weight-medium">Shipping</h6>
                            <h6 class="font-weight-medium">$10.00</h6>
                        </div>
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold">$${order.totalPrice}</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Checkout End -->
    </c:if>


    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


<jsp:directive.include file="/resources/jsp/footer.jsp" />
<jsp:directive.include file="/resources/script.html"/>
</body>

</html>