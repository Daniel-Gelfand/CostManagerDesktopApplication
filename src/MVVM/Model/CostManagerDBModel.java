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
    public void setupNewAccount(Account account) {

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
