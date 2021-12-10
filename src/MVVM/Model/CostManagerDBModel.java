package MVVM.Model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CostManagerDBModel implements IModel {

    private List<Category> categories = new LinkedList<>();
    private Connection costManagerConnection = null;
    private Statement statement = null;
    public CostManagerDBModel() {
        try {
            costManagerConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager", "CostManager", "CostManager");
            statement = costManagerConnection.createStatement();
            System.out.println("Connected To DB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setupNewAccount(Account account) {

        try {
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
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginToAccount(Account account) {
        boolean isRegistered = false;
        ResultSet resultSet = null;
        try {
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
            e.printStackTrace();
        }
        return isRegistered;
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }

    @Override // option????????????????
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void addNewCost(Cost cost) {

        try {
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
            e.printStackTrace();
        }

    }

    @Override
    public Connection getReport() {
        return costManagerConnection;
    }

    @Override
    public void logout() {

    }

}
