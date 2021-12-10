package MVVM.Model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Cost {

    private String usernames;
    private String 	categories;
    private String description;
    private String costAmount;
    private String currency;
    private Date date;

    public Cost(String usernames, String categories, String description, String costAmount, String currency, Date date) {
        this.usernames = usernames;
        this.categories = categories;
        this.description = description;
        this.costAmount = costAmount;
        this.currency = currency;
        this.date = date;
    }


    public String getUsernames() {
        return usernames;
    }

    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(String costAmount) {
        this.costAmount = costAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
