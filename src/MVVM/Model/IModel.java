package MVVM.Model;

import javax.lang.model.element.Name;
import java.sql.Connection;
import java.util.Date;

public interface IModel {

    public void setupNewAccount(String username, String password, String firstName, String lastName,String email);
    public void loginToAccount(String username, String password);
    public void addCategory(String category);
    public void selectCategory();
    public void addNewCost(Cost cost);
    public Connection getReport();
    public void logout();
    public void addDescription(String description);
    //usernames	categories	decriptions	cost	currency	date
}
