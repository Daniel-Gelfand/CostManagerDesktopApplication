package MVVM.Model.Model;

import MVVM.Model.*;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * This Class implements IModel, responsible for transitions between database to CostManager System
 *
 */


public class CostManagerDBModel implements IModel {

    // This List not in use yet.
    private List<Category> categories = new LinkedList<>();

    // login details to sql server
    private String url = "jdbc:mysql://localhost:3306/costmanager";
    private String username = "CostManager";
    private String password = "CostManager";

    // This Ctor connect to database Sql server.
    public CostManagerDBModel() throws CostManagerExceptions {
        try {
            Connection costManagerConnection = DriverManager.getConnection(url, username, password);
            Statement statement = costManagerConnection.createStatement();
            System.out.println("Connected To DB!");
        } catch (Exception e)
        {
            throw new CostManagerExceptions("Problem With Connect To DataBase {PHPAdmin}");
        }
    }

    // This method set new account in database.
    @Override
    public void setupNewAccount(Account account) throws CostManagerExceptions {

        try {
            Connection costManagerConnection = DriverManager.getConnection(url, username, password);
            Statement statement = costManagerConnection.createStatement();
            System.out.println("Connected To DB!");
            String query = "insert into accounts_db" + "(usernames,passwords)"
                    + "values (?,?)";

            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            System.out.println("Inserted To DB!");
            preparedStmt.setString(1, account.getUsername());
            preparedStmt.setString(2, account.getPassword());
            preparedStmt.execute();
            costManagerConnection.close();

        } catch (SQLException e) {
            throw  new CostManagerExceptions("Problem With Insert New Account To DataBase!",e);
        }
    }

    // This method search account in database and returns true or false in accordance.
    @Override
    public boolean loginToAccount(Account account) throws CostManagerExceptions {
        boolean isRegistered = false;
        ResultSet resultSet = null;
        try {
            Connection costManagerConnection = DriverManager.getConnection(url, username, password);
            Statement statement = costManagerConnection.createStatement();
            System.out.println("Connected To DB!");
            if (account.getUsername() != null && account.getPassword() != null) {
                String sql = "Select * from accounts_db Where usernames='" + account.getUsername() + "' and passwords='" + account.getPassword() + "'";
                resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    //in this case enter when at least one result comes it means user is valid
                    System.out.println("Done,Loggin To Acount");
                    isRegistered = true;
                } else {
                    //in this case enter when  result size is zero  it means user is invalid
                    System.out.println("USER invalid");
                    isRegistered = false;
                }
            }
            costManagerConnection.close(); // Here?
            statement.close(); // Here?
        }
        catch (SQLException e)
        {
            throw new CostManagerExceptions("Problem To Login Account in Database!",e);
        }
        return isRegistered;
    }

    // This method not in use yet.
    @Override
    public void addCategory(Category category) throws CostManagerExceptions {
        categories.add(category);
    }

    // This method not in use yet.
    @Override
    public List<Category> getCategories() throws CostManagerExceptions {
        return categories;
    }

    // This method add new cost to the database.
    @Override
    public void addNewCost(Cost cost) throws CostManagerExceptions{

        try {
            Connection costManagerConnection = DriverManager.getConnection(url, username, password);
            Statement statement = costManagerConnection.createStatement();
            System.out.println("Connected To DB!");
            String query = "insert into main_db" + "(usernames,categories,description,cost,currency,date)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            System.out.println("Inserted To DB!");
            preparedStmt.setString(1, cost.getUsernames());
            preparedStmt.setString(2, cost.getCategories());
            preparedStmt.setString(3, cost.getDescription());
            preparedStmt.setDouble(4, cost.getCostAmount());
            preparedStmt.setString(5, cost.getCurrency());
            preparedStmt.setDate(6, cost.getDate());
            preparedStmt.execute();
            costManagerConnection.close(); // Here?
            statement.close(); // Here?

        } catch (SQLException e) {
            throw new CostManagerExceptions("Problem With add Cost To Database!",e);
        }

    }

    // This method returns reports about costs by selected dates.
    @Override
    public LinkedList<Cost> getReport(Account account, Date start, Date end) throws CostManagerExceptions {
        try {
            Connection costManagerConnection = DriverManager.getConnection(url, username, password);
            Statement statement = costManagerConnection.createStatement();
            ResultSet resultSet;
            LinkedList<Cost> costs = new LinkedList<>();

            System.out.println("Connected To DB!");
            String sql = "select * from main_db where usernames='" + account.getUsername() + "'and date >='" + start.toString() + "'and date <='" + end.toString() + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Cost cost = new Cost(resultSet.getString("usernames"), resultSet.getString("categories"), resultSet.getString("description"),
                        resultSet.getDouble("cost"), resultSet.getString("currency"), resultSet.getDate("date"));

                costs.add(cost);
            }
            costManagerConnection.close(); // Here?
            statement.close(); // Here?

            return costs;
        } catch (SQLException e) {
            throw new CostManagerExceptions("Problem with get report", e);
        }

    }



    // This method we need to delete. -------> Ask Michael Life. ViewManager?
    @Override
    public void logout() throws CostManagerExceptions {

    }

    private static void close(Connection myConn, Statement myStmt) throws SQLException {
        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }

}
