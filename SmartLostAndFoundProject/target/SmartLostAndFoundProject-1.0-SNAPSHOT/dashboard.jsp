<%@ page session="true" %>
<%
    com.lostfound.User user = (com.lostfound.User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Welcome, <%= user.getName() %></h2>
    
    <a href="<%= request.getContextPath() %>/additem.jsp" class="btn-blue">Add Item</a> |
    <a href="<%= request.getContextPath() %>/viewitem" class="btn-cyan">View Items</a> |
    <a href="<%= request.getContextPath() %>/logout" class="btn-green">Logout</a>
</body>
</html>
