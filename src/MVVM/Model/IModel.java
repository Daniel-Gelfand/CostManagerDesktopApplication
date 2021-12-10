package MVVM.Model;

import javax.lang.model.element.Name;
import java.sql.Connection;
import java.util.Date;

public interface IModel {
    public void setupNewAccount(Account account);
    public void loginToAccount(Account account);
    public void addCategory(Category category);
    public void selectCategory();
    public void addNewCost(Cost cost);
    public Connection getReport();
    public void logout();
}
