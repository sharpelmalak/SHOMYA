<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.persistence.model.Order" %>
<%@ page import="iti.jets.persistence.model.Customer" %>
<jsp:directive.include file="/resources/head.html" />
<style>
  .details, .orders {
    margin-bottom: 40px;
    background-color: #f8f9fa;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }

  .details h4, .orders h4 {
    font-size: 24px;
    font-weight: bold;
    color: #343a40;
    margin-bottom: 20px;
  }

  .details p {
    margin: 10px 0;
    font-size: 16px;
    color: #555;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    overflow: hidden;
  }

  table th, table td {
    padding: 15px;
    text-align: left;
    border-bottom: 1px solid #ddd;
    font-size: 16px;
  }

  table th {
    background-color: #343a40;
    color: white;
  }

  table tr:hover {
    background-color: #f1f1f1;
  }

  .btn-primary {
    background-color: #b67093;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    transition: background-color 0.3s ease;
  }

  .btn-primary:hover {
    background-color: #b67093;
  }
</style>

<body>
<%@include file="/resources/jsp/header.jsp"%>

<!-- Customer Details Start -->
<div class="container-fluid pt-5">
  <c:if test="${userRole == EnumHelper.getAdminRole()}">
    <div class="text-center mb-4">
      <h2 class="section-title px-5">
        <span class="px-2">Customer Details</span>
      </h2>
    </div>

    <!-- Customer Information Section -->
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
  </c:if>
    <!-- Orders Section -->
    <div class="orders">
<c:if test="${userRole == EnumHelper.getAdminRole()}">
      <h4>Customer Orders</h4>
</c:if>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>Order ID</th>
          <th>Order Date</th>
          <th>Total Price</th>
          <th>Details</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${orderList != null}">
          <c:forEach items="${orderList}" var="order">
            <tr>
              <td>${order.id}</td>
              <td>${order.orderDate}</td>
              <td>$${order.totalPrice}</td>
              <td>
                <a href="/shomya/app/vieworder?orderId=${order.id}">
                  <button type="button" class="btn btn-primary">View Details</button>
                </a>
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
