<!DOCTYPE html>
<html lang="en">
<%@ page import="iti.jets.model.Category" %>
<%@ page import="iti.jets.model.Customer" %>
<jsp:directive.include file="/resources/head.html"/>
<style>
    input[type=file]::file-selector-button {
        border-radius: .2em;
        background-color: #b67093;
        transition: 1s;
    }

    input[type=file]::file-selector-button:hover {
        background-color: #d4efef;
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

<!-- Customers Start -->
<div class="container-fluid pt-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">All Customers</span></h2>
    </div>
    <div class="row px-xl-5 pb-3">
        <div class="col-lg-12">
            <c:if test="${customertList != null}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Customer ID</th>
                        <th>Name</th>
                        <th>Username</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${customertList}" var="customer">
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.name}</td>
                            <td>${customer.username}</td>
                            <td>
                                <a href="/shomya/customerOrder?customerId=${customer.id}" class="btn btn-sm btn-primary">
                                    <i class="fas fa-eye"></i> View Details
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>
<!-- Customers End -->

<jsp:include page="/resources/jsp/footer.jsp" />
</body>
</html>
