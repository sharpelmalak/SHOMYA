<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.model.Order" %>
<%@ page import="iti.jets.model.Customer" %>
<jsp:directive.include file="/resources/head.html"/>
<style>
    .details {
        margin-bottom: 20px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }

    table th, table td {
        padding: 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    table th {
        background-color: #f8f9fa;
        font-weight: bold;
    }

    table tr:hover {
        background-color: #f1f1f1;
    }
</style>
<body>

<%@include file="/resources/jsp/header.jsp"%>

<!-- Customer Details Start -->
<div class="container-fluid pt-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Customer Details</span></h2>
    </div>

    <div class="details">
        <h4>Customer Information</h4>
        <p><strong>ID:</strong> ${customer.id}</p>
        <p><strong>Name:</strong> ${customer.name}</p>
        <p><strong>Username:</strong> ${customer.username}</p>
        <p><strong>Email:</strong> ${customer.email}</p>
        <p><strong>Birthdate:</strong> ${customer.birthdate}</p>
        <p><strong>Job:</strong> ${customer.job}</p>
        <p><strong>Credit Limit:</strong> ${customer.creditLimit}</p>
        <p><strong>Address:</strong> ${customer.address}</p>
    </div>

    <!-- Orders Table -->
    <div class="orders">
        <h4>Customer Orders</h4>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Total Price</th>
                <th>Order Items</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${orderList != null}">
                <c:forEach items="${orderList}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.totalPrice}</td>
                        <td>
                            <c:forEach items="${order.orderItems}" var="item">
                                <p>Item ID: ${item.id}, Product Name: ${item.product.name}, Quantity: ${item.quantity}</p>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
<!-- Customer Details End -->

<jsp:include page="/resources/jsp/footer.jsp" />
</body>
</html>
