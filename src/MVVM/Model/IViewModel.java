package MVVM.Model;

import java.sql.Connection;
import java.util.List;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void setupNewAccount(Account account);
    public void loginToAccount(Account account);
    public void addCategory(Category category);
    public void getCategories();
    public void addNewCost(Cost cost, Account account);
    public void getReport();
    public void logout();
    public void startNewCost(Account account);
}
