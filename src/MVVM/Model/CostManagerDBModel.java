package MVVM.Model;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class CostManagerDBModel implements IModel{

    private List<String> categories = new LinkedList<>();
    private Connection costManagerConnection;

    public CostManagerDBModel(Connection costManagerConnection) {
        this.costManagerConnection = costManagerConnection;
    }

    @Override
    public void setupNewAccount(String username, String password, String firstName, String lastName, String email) {

    }

    @Override
    public void loginToAccount(String username, String password) {

    }

    @Override
    public void addCategory(String category) {
        categories.add(category);
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

    @Override
    public void addDescription(String description) {

    }
}
