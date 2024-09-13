<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
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
            max-width: 600px;
            margin: 30px auto;
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
        .control-group label {
            margin-bottom: 5px;
            color: #333;
            display: block;
        }
        .help-block {
            font-size: 0.875rem;
            color: #dc3545;
        }
    </style>

</head>

<body>
<div class="container">
    <h2 class="text-center">Registration Form</h2>
    <form method="post" action="/shomya/registration">
        <!-- Name -->
        <div class="control-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Name" required/>
            <small class="form-text text-danger" id="nameError"></small>
        </div>

        <!-- Username -->
        <div class="control-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Username" onblur="checkUsername()" required />
            <small class="form-text text-danger" id="usernameError"></small>
        </div>

        <!-- Email -->
        <div class="control-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" name="email" id="email" placeholder="Email" onblur="checkEmail()" required />
            <small class="form-text text-danger" id="emailError"></small>
        </div>

        <!-- Date -->
        <div class="control-group">
            <label for="date">Birthdate</label>
            <input type="date" class="form-control" name="date" id="date" placeholder="Birthdate" required />
            <small class="form-text text-danger" id="dateError"></small>
        </div>

        <!-- Address -->
        <div class="control-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" name="address" id="address" placeholder="Address" required/>
            <small class="form-text text-danger" id="addressError"></small>
        </div>

        <!-- Credit Limit -->
        <div class="control-group">
            <label for="creditLimit">Credit Limit</label>
            <input type="number" class="form-control" name="creditLimit" id="creditLimit" placeholder="Credit Limit" min="0" required/>
            <small class="form-text text-danger" id="creditLimitError"></small>
        </div>

        <!-- Job -->
        <div class="control-group">
            <label for="job">Job</label>
            <input type="text" class="form-control" name="job" id="job" placeholder="Job" />
            <small class="form-text text-danger" id="jobError"></small>
        </div>

        <!-- Password -->
        <div class="control-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Password" required/>
            <small class="form-text text-danger" id="passwordError"></small>
        </div>

        <!-- Confirm Password -->
        <div class="control-group">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" onchange="checkPassword()" required/>
            <small class="form-text text-danger" id="confirmPasswordError"></small>
        </div>

        <div class="form-group">
            <label for="choose_categories">Choose Categories</label>
            <c:forEach var="category" items="${categories}">
                <div class="form-check" id="choose_categories">
                    <input class="form-check-input" type="checkbox" name="categories" value="${category.id}" id= "${category.id}">
                    <label class="form-check-label" for="${category.id}">
                            ${category.name}
                    </label>
                </div>
            </c:forEach>
        </div>

        <!-- Submit Button -->
        <div>
            <button class="btn btn-primary py-2 px-4" type="submit">Register</button>
        </div>
    </form>
</div>

<script>


    function checkPassword() {
        // Get the password and confirm password fields
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        // Get the error message elements
        var passwordError = document.getElementById("passwordError");
        var confirmPasswordError = document.getElementById("confirmPasswordError");

        // Clear previous error messages
        passwordError.textContent = "";
        confirmPasswordError.textContent = "";

        // Check if passwords match
        if (password !== confirmPassword) {
            confirmPasswordError.textContent = "Passwords do not match!";
            return false; // Prevent form submission
        }
        return true; // Allow form submission if everything is okay
    }



    function check(param) {
        var emailError = document.getElementById('emailError');
        var usernameError = document.getElementById('usernameError');

        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/shomya/check_unique?' + param, true);


        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                const status = xhr.status;
                if (status === 0 || (status >= 200 && status < 400)) {

                    var data = xhr.responseText.trim();
                    console.log("in ajax"+data);
                    if(data === "email_found"){
                        emailError.textContent = "Email is already taken.";
                    }
                    else if(data==="email_notfound"){
                        emailError.textContent = "";
                    }
                    else if(data==="user_found"){
                        usernameError.textContent = "Username is already taken.";
                    }
                    else {
                        usernameError.textContent = "";
                    }
                }
            }
        };
        xhr.send();
    }

    function checkUsername(){
        var username = document.getElementById('username').value;
        if (username) {
            console.log("username="+ encodeURIComponent(username))
            check("username="+ encodeURIComponent(username));
        }
    }

    function checkEmail() {
        var email = document.getElementById('email').value;
        if (email) {
            console.log("email="+encodeURIComponent(email));
            check("email="+encodeURIComponent(email));
        }
    }
</script>
</body>
</html>
