<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Page Not Found</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
            padding: 50px;
        }
        h1 {
            font-size: 50px;
            color: #dc3545;
        }
        p {
            font-size: 18px;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>404 - Page Not Found</h1>
<p>Sorry, the page you are looking for does not exist.</p>
<p><a href="${pageContext.request.contextPath}/">Go to Homepage</a></p>
</body>
</html>
