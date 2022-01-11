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


        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ){
        } catch (Exception e)
        {
            throw new CostManagerExceptions("Problem With Connect To DataBase {PHPAdmin}");
        }
    }

    // This method set new account in database.
    @Override
    public void setupNewAccount(Account account) throws CostManagerExceptions {


        try(
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ) {

            String query = "insert into accounts_db" + "(usernames,passwords)"
                    + "values (?,?)";

            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            preparedStmt.setString(1, account.getUsername());
            preparedStmt.setString(2, account.getPassword());
            preparedStmt.execute();

        } catch (SQLException e) {
            throw  new CostManagerExceptions("The username already exist", e);
        }
    }

    // This method search account in database and returns true or false in accordance.
    @Override
    public boolean loginToAccount(Account account) throws CostManagerExceptions {
        boolean isRegistered = false;
        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();

                ){
            ResultSet resultSet;
            if (account.getUsername() != null && account.getPassword() != null) {

                String sql = "Select * from accounts_db Where usernames='" + account.getUsername() + "' and passwords='" + account.getPassword() + "'";
                resultSet = statement.executeQuery(sql);

                if (resultSet.next())
                {
                    //in this case enter when at least one result comes it means user is valid
                    isRegistered = true;
                }
                else
                {
                    //in this case enter when  result size is zero  it means user is invalid
                    isRegistered = false;
                }
            }
        }
        catch (SQLException e)
        {
            throw new CostManagerExceptions("The username or password are wrong! please try again.",e);
        }

        return isRegistered;
    }

    // This method not in use yet.
    @Override
    public void addCategory(Category category, Account account) throws CostManagerExceptions {

        try(
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ) {

            String query = "insert into categories_dbtest" + "(usernames, categories)" + "values (?,?)";
            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            System.out.println("Inserted To DB!");
            preparedStmt.setString(1, account.getUsername());
            preparedStmt.setString(2, category.getCategory());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new CostManagerExceptions("The category already exist!",e);
        }
    }

    @Override
    public LinkedList<Category> getCategories(Account account) throws CostManagerExceptions {


        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ){

                    ResultSet resultSet;
                    LinkedList<Category> categories = new LinkedList<>();
                    System.out.println("Connected To DB!");
                    String sql = "SELECT categories FROM categories_dbtest WHERE usernames='" + account.getUsername() + "'";
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                         Category category = new Category(resultSet.getString("categories"));
                        System.out.println(category.getCategory());

                         categories.add(category);
                    }

                 return categories;
        }
        catch (SQLException e) {
            throw new CostManagerExceptions("Problem with get report", e);
        }
    }

    // This method add new cost to the database.
    @Override
    public void addNewCost(Cost cost) throws CostManagerExceptions{

        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ){

            String query = "insert into main_db" + "(usernames,categories,description,cost,currency,date)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            preparedStmt.setString(1, cost.getUsernames());
            preparedStmt.setString(2, cost.getCategories());
            preparedStmt.setString(3, cost.getDescription());
            preparedStmt.setDouble(4, cost.getCostAmount());
            preparedStmt.setString(5, cost.getCurrency());
            preparedStmt.setDate(6, cost.getDate());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new CostManagerExceptions("Problem with add new cost!",e);
        }
    }

    // This method returns reports about costs by selected dates.
    @Override
    public LinkedList<Cost> getReport(Account account, Date start, Date end) throws CostManagerExceptions {
        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ){

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

            return costs;
        }
        catch (SQLException e) {
            throw new CostManagerExceptions("Problem with get report", e);
        }
    }
}
