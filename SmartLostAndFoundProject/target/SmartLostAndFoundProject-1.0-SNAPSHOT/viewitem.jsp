<%@ page import="java.util.List" %>
<%@ page import="com.lostfound.Item" %>
<%@ page session="true" %>
<%
    com.lostfound.User user = (com.lostfound.User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<html>
<head>
    <title>All Lost & Found Items</title>
    <link rel="stylesheet" href="css/style.css">

    <!-- Bootstrap for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding: 20px;
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
        }
        h2 {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn-delete {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-delete:hover {
            background-color: #c82333;
        }
    </style>

    <script>
        function confirmDelete(form) {
            if (confirm("Are you sure you want to delete this item?")) {
                form.submit(); // Submit if user confirms
            }
            return false; // Prevent default otherwise
        }
    </script>
</head>

<body>
    <h2>All Lost & Found Items</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Category</th>
            <th>Description</th>
            <th>Location</th>
            <th>Date</th>
            <th>Action</th>
        </tr>

        <% if (items != null && !items.isEmpty()) {
            for(Item item : items) { %>
                <tr>
                    <td><%= item.getId() %></td>
                    <td><%= item.getType() %></td>
                    <td><%= item.getCategory() %></td>
                    <td><%= item.getDescription() %></td>
                    <td><%= item.getLocation() %></td>
                    <td><%= item.getDate() %></td>
                    <td>
                        <form action="deleteitem" method="post" onsubmit="return confirmDelete(this);" style="display:inline;">
                            <input type="hidden" name="id" value="<%= item.getId() %>">
                            <input type="submit" class="btn-red btn-delete" value="Delete">
                        </form>
                    </td>
                </tr>
        <%  }
        } else { %>
            <tr>
                <td colspan="7" style="text-align:center;">No items found.</td>
            </tr>
        <% } %>
    </table>
</body>
</html>
