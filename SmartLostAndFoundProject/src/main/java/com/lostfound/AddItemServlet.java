package com.lostfound;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/additem")
public class AddItemServlet extends HttpServlet {

    // Handle POST: Add item to DB
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String type = request.getParameter("type");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String location = request.getParameter("location");
        Date date = Date.valueOf(request.getParameter("date"));

        Item item = new Item(type, category, description, location, date, user.getId());
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.addItem(item);

        response.sendRedirect(request.getContextPath() + "/viewitem"); // Redirect to view all items
    }

    // Handle GET: Show add item page with lost items
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ItemDAO itemDAO = new ItemDAO();
        List<Item> lostItems = itemDAO.getLostItems(); // Get list of lost items
        request.setAttribute("lostItems", lostItems);

        RequestDispatcher dispatcher = request.getRequestDispatcher("additem.jsp");
        dispatcher.forward(request, response);
    }
}
