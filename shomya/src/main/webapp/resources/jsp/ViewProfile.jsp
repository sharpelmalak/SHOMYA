<%@ page import="iti.jets.service.helper.EnumHelper" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="iti.jets.model.User" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>EShopper - Bootstrap Shop Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="/shomya/resources/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/shomya/resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/shomya/resources/css/style.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <h2 class="text-center">VIEW AND UPDATE</h2>
    <form method="post" action="/shomya/UpdateProfileServlet">
        <!-- Name -->
        <div class="control-group">
            <input type="text" class="form-control" name="name" placeholder="Name" required="required"
                   data-validation-required-message="Please enter your name" value="${user.name}" />
            <p class="help-block text-danger"></p>
        </div>

        <!-- Username -->
        <div class="control-group">
            <input type="text" class="form-control" name="username" placeholder="Username" required="required"
                   data-validation-required-message="Please enter a username" value="${user.username}" />
            <p class="help-block text-danger"></p>
        </div>

        <!-- Email -->
        <div class="control-group">
            <input type="email" class="form-control" name="email" placeholder="Email" required="required"
                   data-validation-required-message="Please enter a valid email" value="${user.email}"/>
            <p class="help-block text-danger"></p>
        </div>

        <!-- Password -->
        <div class="control-group">
            <input type="password" class="form-control" name="password" placeholder="Password" required="required"
                   data-validation-required-message="Please enter your password" />
            <p class="help-block text-danger"></p>
        </div>

<c:if test="${userRole == EnumHelper.getCustomerRole()}">
        <!-- date -->
        <div class="control-group">
            <input type="date" class="form-control" name="date" placeholder="Birthdate" required="required"
                   data-validation-required-message="Please select your birthdate" value="${user.birthdate}"/>
            <p class="help-block text-danger"></p>
        </div>

        <!-- Address -->
        <div class="control-group">
            <input type="text" class="form-control" name="address" placeholder="Address" required="required"
                   data-validation-required-message="Please enter your address "  value="${user.address}"/>
            <p class="help-block text-danger"></p>
        </div>

        <!-- Credit Limit -->
        <div class="control-group">
            <input type="number" class="form-control" name="creditLimit" placeholder="Credit Limit" required="required"
                   min="0" data-validation-required-message="Please enter a valid credit limit" value="${user.creditLimit}" />
            <p class="help-block text-danger"></p>
        </div>

        <!-- Job -->
        <div class="control-group">
            <input type="text" class="form-control" name="job" placeholder="Job" required="required"
                   data-validation-required-message="Please enter your job title" value="${user.job}" />
            <p class="help-block text-danger"></p>
        </div>

        </c:if>
        <c:if test="${userRole == EnumHelper.getAdminRole()}">
        <div class="control-group">
                    <input type="date" class="form-control" name="hiredate" placeholder="hireDate" required="required"
                           data-validation-required-message="Please select your hireDate" value="${user.hireDate}"/>
                    <p class="help-block text-danger"></p>
                </div>
        </c:if>

        <!-- Submit Button -->
        <div>
            <button class="btn btn-primary py-2 px-4" type="submit">Update</button>
        </div>
    </form>
</div>
</body>

</html>
