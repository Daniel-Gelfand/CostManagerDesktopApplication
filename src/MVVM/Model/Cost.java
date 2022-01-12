package MVVM.Model;

import java.sql.*;
import java.sql.Date;
import java.util.*;


/**
 * 'Cost' :
 *
 * This Class encapsulate all details about Cost.
 * When the client wants to add cost, he needs to fill out a form with the expense information.
 *
 * Methods:
 *  (1) Cost(String usernames, String categories, String description, double costAmount, String currency, Date date),
 *  (2) getUsernames(),
 *  (3) setUsernames(String usernames),
 *  (4) getCategories(),
 *  (5) setCategories(String categories),
 *  (6) getDescription(),
 *  (7) setDescription(String description),
 *  (8) getCostAmount(),
 *  (9) setCostAmount(double costAmount),
 *  (10) getCurrency(),
 *  (11) setCurrency(String currency),
 *  (12) getDate(),
 *  (13) setDate(Date date).
 */
public class Cost {

    // These members are appropriate to Cost.
    private String usernames;
    private String 	categories;
    private String description;
    private double costAmount;
    private String currency;
    private Date date;



    /**
     * 'Cost' is a constructor.
     * In this constructor we set all details about specific cost.
     * @param usernames username
     * @param categories categories
     * @param description description of cost
     * @param costAmount cost amount
     * @param currency currency
     * @param date date
     */
    public Cost(String usernames, String categories, String description, double costAmount, String currency, Date date) {
        setUsernames(usernames);
        setCategories(categories);
        setDescription(description);
        setCostAmount(costAmount);
        setCurrency(currency);
        setDate(date);
    }



    /**
     * 'getUsernames' is a method that get usernames.
     * @return username
     */
    public String getUsernames() {
        return usernames;
    }



    /**
     * 'setUsernames' is a method that set usernames.
     * @return usernames
     */
    public void setUsernames(String usernames) {
        this.usernames = usernames;
    }



    /**
     * 'getCategories' is a method that get categories.
     * @return categories
     */
    public String getCategories() {
        return categories;
    }


    /**
     * 'setCategories' is a method that set categories.
     * @return categories
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }



    /**
     * 'getDescription' is a method that get descriptions.
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * 'setDescription' is a method that set cost description.
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }



    /**
     * 'getCostAmount' is a method that get cost amount.
     * @return costAmount
     */
    public double getCostAmount() {
        return costAmount;
    }



    /**
     * 'setCostAmount' is a method that set cost amount.
     * @param costAmount costAmount
     */
    public void setCostAmount(double costAmount) {
        this.costAmount = costAmount;
    }



    /**
     * 'getCurrency' is a method that get currency.
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }



    /**
     * 'setCurrency' is a method that set currency.
     * @param currency currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }



    /**
     * 'getDate' is a method that get date.
     * @return date
     */
    public Date getDate() {
        return date;
    }



    /**
     * 'setDate' is a method that set date.
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
