package MVVM.Model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CostManagerDBModel implements IModel{

    private List<String> categories = new LinkedList<>();
    private Connection costManagerConnection = null;
    private Statement statement = null;

    public CostManagerDBModel() {
        try
        {
            costManagerConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager", "CostManager" , "CostManager");
            statement = costManagerConnection.createStatement();
            System.out.println("Connected To DB!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setupNewAccount(Account account) {
//        "insert into accounts_db" + "(usernames,passwords,emails,first_name,last_name)"
//                            + "values" + "(account.getUsername(),account.getPassword(),account.getEmail(),account.getFirstName(),account.getLastName())"

//        "insert into accounts_db + (usernames,passwords,emails,first_name,last_name)"
//                    + " values (?,?,?,?,?)"


        try {
//            String query = "insert into accounts_db + (usernames,passwords,emails,first_name,last_name)"
//                    + "values (?,?,?,?,?)";
//            PreparedStatement preparedStmt = costManagerConnection.prepareStatement(query);
//            preparedStmt.setString(1, account.getUsername());
//            preparedStmt.setString(2, account.getPassword());
//            preparedStmt.setString(3, account.getEmail());
//            preparedStmt.setString(4, account.getFirstName());
//            preparedStmt.setString(5, account.getLastName());
//

            int rowAffected = statement.executeUpdate("insert into accounts_db  (usernames,passwords,emails,first_name,last_name)"
                    + "values"+"(+account.getUsername()+,+account.getPassword()+,+account.getEmail()+,+account.getFirstName()+,+account.getLastName()+)");
//            statement.setString(1,account.getUsername());
//            statement.setString(2,account.getPassword());
//            statement.setString(3,account.getEmail());
//            statement.setString(4,account.getFirstName());
//            statement.setString(5,account.getLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void loginToAccount(Account account) {

    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void selectCategory() {

    }

    @Override
    public void addNewCost(Cost cost) {

    }

    @Override
    public Connection getReport() {
        return null;
    }

    @Override
    public void logout() {

    }
}
