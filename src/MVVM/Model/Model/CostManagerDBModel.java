package MVVM.Model.Model;

import MVVM.Model.*;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


/**
 * TEXT HERE...
 */


public class CostManagerDBModel implements IModel {

    private List<Category> categories = new LinkedList<>();

    private String url = "jdbc:mysql://localhost:3306/costmanager";
    private String username = "CostManager";
    private String password = "CostManager";
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

    @Override
    public void setupNewAccount(Account account) throws CostManagerExceptions {

        try {
            Connection costManagerConnection = DriverManager.getConnection(url, username, password);
            Statement statement = costManagerConnection.createStatement();
            System.out.println("Connected To DB!");
            String query = "insert into accounts_db" + "(usernames,passwords,emails,first_name,last_name)"
                    + "values (?,?,?,?,?)";

            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
            System.out.println("Inserted To DB!");
            preparedStmt.setString(1, account.getUsername());
            preparedStmt.setString(2, account.getPassword());
            preparedStmt.setString(3, account.getEmail());
            preparedStmt.setString(4, account.getFirstName());
            preparedStmt.setString(5, account.getLastName());
            preparedStmt.execute();
            costManagerConnection.close();

        } catch (SQLException e) {
            throw  new CostManagerExceptions("Problem With Insert New Account To DataBase!",e);
        }
    }

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
        }
        catch (SQLException e)
        {
            throw new CostManagerExceptions("Problem To Login Account in Database!",e);
        }
        return isRegistered;
    }

    @Override
    public void addCategory(Category category) throws CostManagerExceptions {
        categories.add(category);
    }

    @Override // option????????????????
    public List<Category> getCategories() throws CostManagerExceptions {
        return categories;
    }

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
            costManagerConnection.close();

        } catch (SQLException e) {
            throw new CostManagerExceptions("Problem With add Cost To Database!",e);
        }

    }

    @Override
    public Collection<Cost> getReport(Account account, Date start, Date end) throws CostManagerExceptions {
        try {
            Connection costManagerConnection = DriverManager.getConnection(url, username, password);
            Statement statement = costManagerConnection.createStatement();
            ResultSet resultSet;
            Collection<Cost> costs = new LinkedList<>();

            System.out.println("Connected To DB!");
            String sql = "select * from main_db where usernames='" + account.getUsername() + "'and date >='" + start.toString() + "'and date <='" + end.toString() + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Cost cost = new Cost(resultSet.getString("usernames"), resultSet.getString("categories"), resultSet.getString("description"),
                        resultSet.getDouble("cost"), resultSet.getString("currency"), resultSet.getDate("date"));

                costs.add(cost);
            }
            return costs;
        } catch (SQLException e) {
            throw new CostManagerExceptions("Problem with get report", e);
        }
    }

    @Override
    public void logout() throws CostManagerExceptions {

    }

}
