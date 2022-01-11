package MVVM.Model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * This interface implements the model in architecture MVVM.
 *
 */

public interface IViewModel {

    // This method set View member (MVVM).
    public void setView(IView view);

    // This method set model member (MVVM).
    public void setModel(IModel model);

    // This method sets up new account in the system.
    public void setupNewAccount(Account account);

    // This method check if account is registered in the system.
    public void loginToAccount(Account account);

    // This method add new cost to specific account on database.
    public void addNewCost(Cost cost, Account account);

    // This method return details about some costs by selected dates.
    public void getReport(Account account, Date start, Date end);

    public void addNewCategory(Category category, Account account);

    public void addNewCost(Account account);
}
