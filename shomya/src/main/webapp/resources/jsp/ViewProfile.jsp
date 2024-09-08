<%@ page import="iti.jets.service.helper.EnumHelper" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="iti.jets.model.User" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>View and Update</title>
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

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #d19c97;
            margin-bottom: 30px;
        }
        .form-control {
            border-color: #d19c97;
            border-radius: 4px;
            padding: 15px;
            margin-bottom: 20px;
        }
        .form-control:focus {
            border-color: #d19c97;
            box-shadow: 0 0 0 0.2rem rgba(209, 156, 151, 0.25);
        }
        .btn-primary {
            background-color: #d19c97;
            border-color: #d19c97;
        }
        .btn-primary:hover {
            background-color: #b1897f;
            border-color: #b1897f;
        }
        .control-group {
            margin-bottom: 20px;
        }
        .help-block {
            font-size: 0.875rem;
            color: #dc3545;
        }
    </style>
</head>

<body>
<div class="container">
    <h2 class="text-center">VIEW AND UPDATE</h2>
    <form method="post" action="/shomya/UpdateProfileServlet">
        <!-- Name -->
        <div class="control-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Name" required="required"
                   data-validation-required-message="Please enter your name" value="${user.name}" />
            <p class="help-block text-danger"></p>
        </div>

        <!-- Username -->
        <div class="control-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Username" required="required"
                   data-validation-required-message="Please enter a username" value="${user.username}" />
            <p class="help-block text-danger"></p>
        </div>

        <!-- Email -->
        <div class="control-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="Email" required="required"
                   data-validation-required-message="Please enter a valid email" value="${user.email}"/>
            <p class="help-block text-danger"></p>
        </div>

        <!-- Password -->
        <div class="control-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Password" required="required"
                   data-validation-required-message="Please enter your password" />
            <p class="help-block text-danger"></p>
        </div>

        <c:if test="${userRole == EnumHelper.getCustomerRole()}">
        <!-- Date -->
        <div class="control-group">
            <label for="date">Birthdate</label>
            <input type="date" class="form-control" name="date" id="date" placeholder="Birthdate" required="required"
                   data-validation-required-message="Please select your birthdate" value="${user.birthdate}"/>
            <p class="help-block text-danger"></p>
        </div>

        <!-- Address -->
        <div class="control-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" name="address" id="address" placeholder="Address" required="required"
                   data-validation-required-message="Please enter your address" value="${user.address}"/>
            <p class="help-block text-danger"></p>
        </div>

        <!-- Credit Limit -->
        <div class="control-group">
            <label for="creditLimit">Credit Limit</label>
            <input type="number" class="form-control" name="creditLimit" id="creditLimit" placeholder="Credit Limit" required="required"
                   min="0" data-validation-required-message="Please enter a valid credit limit" value="${user.creditLimit}" />
            <p class="help-block text-danger"></p>
        </div>

        <!-- Job -->
        <div class="control-group">
            <label for="job">Job</label>
            <input type="text" class="form-control" name="job" id="job" placeholder="Job" required="required"
                   data-validation-required-message="Please enter your job title" value="${user.job}" />
            <p class="help-block text-danger"></p>
        </div>
        </c:if>

        <c:if test="${userRole == EnumHelper.getAdminRole()}">
        <div class="control-group">
            <label for="hiredate">Hire Date</label>
            <input type="date" class="form-control" name="hiredate" id="hiredate" placeholder="Hire Date" required="required"
                   data-validation-required-message="Please select your hire date" value="${user.hireDate}"/>
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
