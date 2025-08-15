<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Lost & Found</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Move styles to external CSS ideally -->
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        form {
            max-width: 300px;
            margin: auto;
        }
        input, button {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
        }
    </style>
</head>
<body>
    <h2>Login</h2>

    <% 
        String error = request.getParameter("error");
        if (error != null) {
    %>
        <p style="color: red;"><%= error %></p>
    <% } %>

    <form action="login" method="post" autocomplete="on">
        <label for="email">Email:</label>
        <input type="text" name="email" id="email" placeholder="Enter your email" required>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" placeholder="Enter your password" required>

        <button type="submit">Login</button>
    </form>
</body>
</html>
