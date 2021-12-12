package MVVM.Model;

import java.sql.Connection;
import java.util.List;

public interface IModel {
    public void setupNewAccount(Account account);
    public boolean loginToAccount(Account account);
    public void addCategory(Category category);
    public List<Category> getCategories();
    public void addNewCost(Cost cost);
    public Connection getReport();
    public void logout();



}
