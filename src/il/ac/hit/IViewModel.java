package il.ac.hit;

import il.ac.hit.model.Account;
import il.ac.hit.model.Category;
import il.ac.hit.model.Cost;

import java.sql.Date;


/**
 * 'IViewModel':
 * This interface implements the model in MVVM architecture.
 * Methods:
 * (1) setView(IView view),
 * (2) setModel(IModel model),
 * (3) setupNewAccount(Account account),
 * (4) loginToAccount(Account account),
 * (5) addNewCost(Cost cost, Account account),
 * (6) getReport(Account account, Date start, Date end).
 * (7) addNewCategory(Category category, Account account)
 * (8) addNewCost(Account account)
 */
public interface IViewModel {


    /**
     * 'setView' is a method that sets view member (MVVM).
     * @param view view
     */
     void setView(IView view);


    /**
     * 'setModel' is a method that set model member (MVVM).
     * @param model
     */
     void setModel(IModel model);


    /**
     * 'setupNewAccount' is a method that up new account in the system.
     * @param account username
     */
     void setupNewAccount(Account account);


    /**
     * 'loginToAccount' is a method that check if account is registered in the system.
     * @param account
     */
     void loginToAccount(Account account);


    /**
     * 'addNewCost' is a method that add new cost to specific account on database.
     * @param cost cost details
     * @param account username
     */
     void addNewCost(Cost cost, Account account);


    /**
     * 'getReport' is a method that return details about specific account all costs by selected dates.
     * @param account username
     * @param start from date
     * @param end to date
     */
     void getReport(Account account, Date start, Date end);

    /**
     * 'addNewCategory' is a method that adding new category to specific account.
     * @param category category
     * @param account username
     */
     void addNewCategory(Category category, Account account);

    /**
     * 'addNewCost' is a method that add new cost to specific account on database.
     * @param account username
     */
     void addNewCost(Account account);
}
