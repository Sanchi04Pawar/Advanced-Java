package com.lostfound;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    private static final String SELECT_ALL_ITEMS = "SELECT * FROM items";

    public ItemDAO() {
        loadDBConfig();
    }

    // 🔹 Load DB credentials from config.properties
    private void loadDBConfig() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            jdbcURL = props.getProperty("db.url");
            jdbcUsername = props.getProperty("db.username");
            jdbcPassword = props.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("⚠ Could not load database configuration. Check config.properties.");
            e.printStackTrace();
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_ITEMS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setUserId(rs.getInt("user_id"));
                item.setType(rs.getString("type"));
                item.setCategory(rs.getString("category"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDate(rs.getDate("date"));
                items.add(item);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public boolean addItem(Item item) {
        boolean result = false;
        String INSERT_ITEM_SQL = "INSERT INTO items (user_id, type, category, description, location, date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement ps = conn.prepareStatement(INSERT_ITEM_SQL);
            ps.setInt(1, item.getUserId());
            ps.setString(2, item.getType());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getDescription());
            ps.setString(5, item.getLocation());
            ps.setDate(6, item.getDate());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = true;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteItem(int id) {
        try (Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            String query = "DELETE FROM items WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Item> getLostItems() {
        List<Item> lostItems = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM items WHERE type = 'lost'")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setType(rs.getString("type"));
                item.setCategory(rs.getString("category"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDate(rs.getDate("date"));
                item.setUserId(rs.getInt("user_id"));
                lostItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lostItems;
    }
}
