<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<head>
    <jsp:directive.include file="/resources/head.html"/>
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
    <form method="post" action="/shomya/app/registration">
        <!-- Name -->
        <div class="control-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="Name" maxlength="20" minlength="4" required/>
            <small class="form-text text-danger" id="nameError"></small>
        </div>

        <!-- Username -->
        <div class="control-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="Username" maxlength="20" minlength="4" onblur="checkUsername()" required />
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
            <input type="text" class="form-control" name="address" id="address" placeholder="Address" maxlength="30" minlength="4" required/>
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
            <input type="text" class="form-control" name="job" id="job" maxlength="20" minlength="4" placeholder="Job" />
            <small class="form-text text-danger" id="jobError"></small>
        </div>

        <div class="control-group position-relative">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" maxlength="20" minlength="4" placeholder="Password" onblur="checkPassword()" required/>
            <small class="form-text text-danger" id="passwordError"></small>
            <!-- Show/Hide Password Icon -->
            <i class="fa fa-eye position-absolute" id="togglePassword" style="cursor: pointer; right: 10px; top: 38px;"></i>
        </div>

        <!-- Confirm Password -->
        <div class="control-group position-relative">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" maxlength="20" minlength="4" placeholder="Confirm Password" onchange="checkPassword()" required/>
            <small class="form-text text-danger" id="confirmPasswordError"></small>
            <!-- Show/Hide Confirm Password Icon -->
            <i class="fa fa-eye position-absolute" id="toggleConfirmPassword" style="cursor: pointer; right: 10px; top: 38px;"></i>
        </div>
        <div class="form-group">
            <label for="choose_categories">Select Your Interests</label>
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
            <button id= "submitButton" class="btn btn-primary py-2 px-4" type="submit" disabled>Register</button>
        </div>
    </form>
</div>
<jsp:directive.include file="/resources/script.html"/>

<script>





    // Toggle password visibility for the password field
    const togglePassword = document.querySelector('#togglePassword');
    const passwordField = document.querySelector('#password');

    togglePassword.addEventListener('click', function () {
        const type = passwordField.type === 'password' ? 'text' : 'password';
        passwordField.type = type;
        this.classList.toggle('fa-eye-slash');
    });

    // Toggle password visibility for the confirm password field
    const toggleConfirmPassword = document.querySelector('#toggleConfirmPassword');
    const confirmPasswordField = document.querySelector('#confirmPassword');

    toggleConfirmPassword.addEventListener('click', function () {
        const type = confirmPasswordField.type === 'password' ? 'text' : 'password';
        confirmPasswordField.type = type;
        this.classList.toggle('fa-eye-slash');
    });




    var isUserFound = false
    var isEmailFound = false
    var isPassCorrect = false
    var isCorrectDate = false


    document.getElementById('date').addEventListener('input', function() {
        // Get the value of the date input
        const inputDate = new Date(this.value);
        // Get the current date without the time component
        const today = new Date();
        today.setHours(0, 0, 0, 0);

        // Check if the input date is in the future
        if (inputDate > today) {
            // Show error message
            document.getElementById('dateError').textContent = 'Birthdate cannot be in the future.';
            // Set input to invalid
            this.setCustomValidity('Invalid date');
            isCorrectDate = false
        } else {
            // Clear error message
            document.getElementById('dateError').textContent = '';
            // Set input to valid
            this.setCustomValidity('');
            isCorrectDate = true
        }
        updateSubmitButton()
    });





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
            isPassCorrect = false
            updateSubmitButton()
            return false; // Prevent form submission
        }
        isPassCorrect = true
        updateSubmitButton()
        return true; // Allow form submission if everything is okay
    }

    function updateSubmitButton()
    {
        if(isEmailFound && isUserFound && isPassCorrect && isCorrectDate)
        {
            document.getElementById("submitButton").disabled = false;
        }
    }


    function check(param) {
        var emailError = document.getElementById('emailError');
        var usernameError = document.getElementById('usernameError');

        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/shomya/app/checkunique?' + param, true);


        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                const status = xhr.status;
                if (status === 0 || (status >= 200 && status < 400)) {

                    var data = xhr.responseText.trim();
                    console.log("in ajax"+data);
                    if(data === "email_found"){
                        emailError.textContent = "Email is already taken.";
                        isEmailFound = false

                    }
                    else if(data==="email_notfound"){
                        emailError.textContent = "";
                        isEmailFound = true
                    }
                    else if(data==="user_found"){
                        usernameError.textContent = "Username is already taken.";
                        isUserFound = false
                    }
                    else {
                        usernameError.textContent = "";
                        isUserFound = true
                    }
                    updateSubmitButton()
                }
            }
        };
        xhr.send(null);
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
