package com.lostfound;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();

        System.out.println("Received email: " + email);
        System.out.println("Received password: " + password);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println("Login successful!");
            response.sendRedirect("dashboard.jsp");
        } else {
            System.out.println("Login failed: Invalid credentials");
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
