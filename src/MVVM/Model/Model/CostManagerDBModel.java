package MVVM.Model.Model;

import MVVM.Model.*;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * This Class implements IModel, responsible for transitions between {MyPhpAdmin} database to CostManager System
 * by using SQL queries and Jar Driver files.
 * Methods:
 *  (1) CostManagerDBModel(),
 *  (2) setupNewAccount(Account account),
 *  (3) loginToAccount(Account account),
 *  (4) addCategory(Category category, Account account),
 *  (5) getCategories(Account account),
 *  (6) addNewCost(Cost cost),
 *  (7) getReport(Account account, Date start, Date end).
 */


public class CostManagerDBModel implements IModel {

    // This member holds list of categories.
    private List<Category> categories = new LinkedList<>();

    // These members are account details to log in into MyPhpAdmin account.
    private String url = "jdbc:mysql://localhost:3306/costmanager";
    private String username = "CostManager";
    private String password = "CostManager";


    /**
     * 'CostManagerDBModel' is a constructor.
     * In this constructor we log in to MyPhpAdmin database
     * @throws CostManagerExceptions
     */
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


    /**
     * 'setupNewAccount' is a methoad that insert into 'accounts_db' database new account details by using
     * sql queries.
     * @param account username
     * @throws CostManagerExceptions
     */
    @Override
    public void setupNewAccount(Account account) throws CostManagerExceptions {


        try(
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ) {
            //SQL queries that insert into 'accounts_db' database new username and password.
            String query = "insert into accounts_db" + "(usernames,passwords)"
                    + "values (?,?)";

            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            // Sets to column 1 username and to column 2 password.
            preparedStmt.setString(1, account.getUsername());
            preparedStmt.setString(2, account.getPassword());
            preparedStmt.execute();

        } catch (SQLException e) {
            throw  new CostManagerExceptions("The username already exist", e);
        }
    }


    /**
     * 'loginToAccount' is a methoad that log in a specific account and check if the username and password is valid on database
     * by sql queries.
     * @param account username
     * @return true / false
     * @throws CostManagerExceptions
     */
    @Override
    public boolean loginToAccount(Account account) throws CostManagerExceptions {
        boolean isRegistered = false;
        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();

                ){
            ResultSet resultSet;
            if (account.getUsername() != null && account.getPassword() != null) {
                //SQL queries that check if username and password are exists in database.
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

    /**
     * 'addCategory' is a methoad that insert to 'categories_dbtest' database new specific category
     * by using sql queries.
     * @param category category
     * @param account username
     * @throws CostManagerExceptions
     */
    @Override
    public void addCategory(Category category, Account account) throws CostManagerExceptions {

        try(
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ) {
            //SQL queries that insert to 'categories_dbtest' category to specific username.
            String query = "insert into categories_dbtest" + "(usernames, categories)" + "values (?,?)";
            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            System.out.println("Inserted To DB!");
            // Sets to column 1 username and to column 2 category.
            preparedStmt.setString(1, account.getUsername());
            preparedStmt.setString(2, category.getCategory());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new CostManagerExceptions("The category already exist!",e);
        }
    }

    /**
     * 'getCategories' is a methoad that get all categories to specific username
     * by using sql queries.
     * @param account username
     * @return categories list
     * @throws CostManagerExceptions
     */
    @Override
    public LinkedList<Category> getCategories(Account account) throws CostManagerExceptions {


        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ){

                    ResultSet resultSet;
                    LinkedList<Category> categories = new LinkedList<>();
                    System.out.println("Connected To DB!");
                    //SQL queries that give to specific username his all categories.
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


    /**
     * 'addNewCost' is a method that adding new cost to specific username
     * by using sql queries.
     * @param cost cost details
     * @throws CostManagerExceptions
     */
    @Override
    public void addNewCost(Cost cost) throws CostManagerExceptions{

        try (
                Connection costManagerConnection = DriverManager.getConnection(url, username, password);
                Statement statement = costManagerConnection.createStatement();
                ){
            //SQL queries that insert into 'main_db' who is the username,categories,descripiton,cost amount,currency and date.
            String query = "insert into main_db" + "(usernames,categories,description,cost,currency,date)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            // Sets to column 1 username , column 2 Categories, column 3 Description, column 4 cost amount, column 5 currency,column 6 date.
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


    /**
     * 'getReport' is a method that get to specific account report about all his costs filtering by date.
     * @param account username
     * @param start from date
     * @param end to date
     * @return
     * @throws CostManagerExceptions
     */
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
