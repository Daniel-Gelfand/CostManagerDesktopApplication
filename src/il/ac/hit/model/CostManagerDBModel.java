package il.ac.hit.model;

import il.ac.hit.*;

import java.sql.*;
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
    private List<Category> categories = new LinkedList<Category>();

    // These members are account details to log in into MyPhpAdmin account.
    private String url = "jdbc:mysql://localhost:3306/costmanager";
    private String userName = "CostManager";
    private String password = "CostManager";



    private void addDefaultCategories()
    {
        Category[] defaultCategories = {new Category("Online Shopping"), new Category("Household"), new Category("Standing orders"), new Category("Recreation")};

        for (int i = 0; i < defaultCategories.length; i++) {
            categories.add(defaultCategories[i]);
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

        //SQL queries that insert into 'accounts_db' database new username and password.
        String query = "insert into accounts_db" + "(usernames,passwords)"
                + "values (?,?)";
        try(
                Connection costManagerConnection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
                ) {
            // Sets to column 1 username and to column 2 password.
            preparedStmt.setString(1, account.getUserName());
            preparedStmt.setString(2, account.getPassword());
            preparedStmt.execute();

        } catch (SQLException e) {
            // throws exception if something went wrong.
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
        //SQL queries that check if username and password are exists in database.
        String sql = "Select * from accounts_db Where usernames='" + account.getUserName() + "' and passwords='" + account.getPassword() + "'";
        try (
                Connection costManagerConnection = DriverManager.getConnection(url, userName, password);
                PreparedStatement statement = costManagerConnection.prepareStatement(sql);

                ){
            ResultSet resultSet;
            if (account.getUserName() != null && account.getPassword() != null) {

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
            // throws exception if something went wrong.
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
        //SQL queries that insert to 'categories_dbtest' category to specific username.
        String query = "insert into categories_dbtest" + "(usernames, categories)" + "values (?,?)";
        try(
                Connection costManagerConnection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
                ) {
            // Sets to column 1 username and to column 2 category.
            preparedStmt.setString(1, account.getUserName());
            preparedStmt.setString(2, category.getCategory());
            preparedStmt.execute();
        } catch (SQLException e) {
            // throws exception if something went wrong.
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
    public List<Category> getCategories(Account account) throws CostManagerExceptions {
        //SQL queries that give to specific username his all categories.
        String sql = "SELECT categories FROM categories_dbtest WHERE usernames='" + account.getUserName() + "'";

        try (
                Connection costManagerConnection = DriverManager.getConnection(url, userName, password);
                PreparedStatement statement = costManagerConnection.prepareStatement(sql);
                ){
                    ResultSet resultSet;
                    categories = new LinkedList<>();
                    addDefaultCategories();
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                         Category category = new Category(resultSet.getString("categories"));
                         categories.add(category);
                    }

                 return categories;
        }
        catch (SQLException e) {
            // throws exception if something went wrong.
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
        //SQL queries that insert into 'main_db' who is the username,categories,descripiton,cost amount,currency and date.
        String query = "insert into main_db" + "(usernames,categories,description,cost,currency,date)"
                + "values (?,?,?,?,?,?)";

        try (
                Connection costManagerConnection = DriverManager.getConnection(url, userName, password);
                PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
                ){
            // Sets to column 1 username , column 2 Categories, column 3 Description, column 4 cost amount, column 5 currency,column 6 date.
            preparedStmt.setString(1, cost.getUsernames());
            preparedStmt.setString(2, cost.getCategories());
            preparedStmt.setString(3, cost.getDescription());
            preparedStmt.setDouble(4, cost.getCostAmount());
            preparedStmt.setString(5, cost.getCurrency());
            preparedStmt.setDate(6, cost.getDate());
            preparedStmt.execute();
        } catch (SQLException e) {
            // throws exception if something went wrong.
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
        //SQL queries that select from 'main_db' the cost of username by dates.
        String sql = "select * from main_db where usernames='" + account.getUserName() + "'and date >='" + start.toString() + "'and date <='" + end.toString() + "'";

        try (
                Connection costManagerConnection = DriverManager.getConnection(url, userName, password);
                PreparedStatement statement = costManagerConnection.prepareStatement(sql);
                ){

            ResultSet resultSet;
            LinkedList<Cost> costs = new LinkedList<>();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                // create new cost by database results.
                Cost cost = new Cost(resultSet.getString("usernames"), resultSet.getString("categories"), resultSet.getString("description"),
                        resultSet.getDouble("cost"), resultSet.getString("currency"), resultSet.getDate("date"));

                costs.add(cost);
            }

            return costs;
        }
        catch (SQLException e) {
            // throws exception if something went wrong.
            throw new CostManagerExceptions("Problem with get report", e);
        }
    }
}
