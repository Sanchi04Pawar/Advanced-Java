package com.lostfound;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeleteItemsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.deleteItem(id);  // delete from DB

        // Redirect to viewitems page
        response.sendRedirect("viewitem");
    }
}
