<h2>Add Lost/Found Item</h2>
<form action="<%= request.getContextPath() %>/additem" method="post">
    Type: 
    <select name="type" id="type" onchange="toggleCategory(this.value)">
        <option value="lost">Lost</option>
        <option value="found">Found</option>
    </select><br>

    <!-- Category: Default input field for Lost -->
    <div id="categoryInput">
        Category: <input type="text" name="category" required><br>
    </div>

    <!-- Hidden Dropdown for Found category (based on lost items) -->
    <div id="categoryDropdown" style="display:none;">
        Category:
        <select name="category">
            <%
                List<com.lostfound.Item> lostItems = (List<com.lostfound.Item>) request.getAttribute("lostItems");
                if (lostItems != null) {
                    for (com.lostfound.Item item : lostItems) {
            %>
                <option value="<%= item.getCategory() %>"><%= item.getCategory() %> - <%= item.getDescription() %></option>
            <%
                    }
                }
            %>
        </select><br>
    </div>

    Description: <textarea name="description" required></textarea><br>
    Location: <input type="text" name="location" required><br>
    Date: <input type="date" name="date" required><br>
    <input type="submit" value="Add Item">
</form>

<script>
    function toggleCategory(value) {
        if (value === "found") {
            document.getElementById("categoryInput").style.display = "none";
            document.getElementById("categoryDropdown").style.display = "block";
        } else {
            document.getElementById("categoryInput").style.display = "block";
            document.getElementById("categoryDropdown").style.display = "none";
        }
    }
</script>
