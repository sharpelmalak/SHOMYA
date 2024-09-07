<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>View Profile</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Stylesheet -->
    <link href="<%= request.getContextPath() %>/resources/css/style.css" rel="stylesheet">

    <script>
        function viewProfile() {
            var username = document.getElementById("username").value;
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "<%= request.getContextPath() %>/ViewProfileServlet?username=" + username, true);

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        var profile = JSON.parse(xhr.responseText);
                        document.getElementById("profileInfo").innerHTML = `
                            <h4>Name: ${profile.name}</h4>
                            <h4>Username: ${profile.username}</h4>
                            <h4>Email: ${profile.email}</h4>
                            <h4>Birthdate: ${profile.birthdate}</h4>
                            <h4>Job: ${profile.job}</h4>
                            <h4>Credit Limit: ${profile.creditLimit}</h4>
                            <h4>Address: ${profile.address}</h4>
                        `;
                    } else {
                        alert("Error retrieving profile.");
                    }
                }
            };

            xhr.send();
        }
    </script>
</head>

<body>

    <!-- View Profile Form -->
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-6">
                <h2 class="text-center mb-4">View Profile</h2>

                <!-- Username Input -->
                <div class="form-group mb-3">
                    <input type="text" class="form-control" id="username" placeholder="Enter Username" required>
                </div>

                <!-- Button to Fetch Profile -->
                <div class="text-center">
                    <button class="btn btn-primary py-2 px-4" onclick="viewProfile()">View Profile</button>
                </div>

                <!-- Display Profile Info -->
                <div class="mt-4" id="profileInfo"></div>
            </div>
        </div>
    </div>

    <!-- Footer (Optional) -->
    <footer class="bg-dark text-white mt-5 py-4">
        <div class="container text-center">
            <p>&copy; 2024 EShopper. All Rights Reserved.</p>
        </div>
    </footer>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/js/bootstrap.bundle.min.js"></script>

</body>

</html>
