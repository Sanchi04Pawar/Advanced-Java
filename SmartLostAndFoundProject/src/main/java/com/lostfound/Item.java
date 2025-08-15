package com.lostfound;

import java.sql.Date;

public class Item {
  private int id;
private String type;
private String category;
private String description;
private String location;
private Date date;
private int userId;

    public Item() {}

    public Item(String type, String category, String description, String location, Date date, int userId) {
    this.type = type;
    this.category = category;
    this.description = description;
    this.location = location;
    this.date = date;
    this.userId = userId;
}


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
