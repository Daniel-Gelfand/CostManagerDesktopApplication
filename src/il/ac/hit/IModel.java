package il.ac.hit;

import java.sql.Date;
import java.util.List;

/**
 * 'IModel':
 * This interface implements the model in MVVM architecture.
 * Methods:
 * (1) setupNewAccount(Account account),
 * (2) loginToAccount(Account account),
 * (3) addCategory(Category category, Account account),
 * (4) getCategories(Account account),
 * (5) addNewCost(Cost cost),
 * (6) getReport(Account account, Date start, Date end).
 */
public interface IModel {



    /**
     * 'setupNewAccount this method sets up new account in the system.
     * @param account username
     * @throws CostManagerExceptions
     */
     void setupNewAccount(Account account) throws CostManagerExceptions;



    /**
     * 'loginToAccount' is a method check if account is registered in the system.
     * @param account username
     * @return true/false
     * @throws CostManagerExceptions
     */
     boolean loginToAccount(Account account) throws CostManagerExceptions;



    /**
     * 'addCategory' is a  method that adding new category to the system
     * @param category category
     * @param account username
     * @throws CostManagerExceptions
     */
     void addCategory(Category category, Account account) throws CostManagerExceptions;



    /**
     * 'getCategories' is a method that gets all categories to specific account.
     * @param account username
     * @return list of categories
     * @throws CostManagerExceptions
     */
     List<Category> getCategories(Account account) throws CostManagerExceptions;



    /**
     * 'addNewCost' is a method add new cost to specific account on database.
     * @param cost cost details
     * @throws CostManagerExceptions
     */
     void addNewCost(Cost cost) throws CostManagerExceptions;



    /**
     * 'getReport' is a method that give a report of all costs to specific account filtering by date.
     * @param account username
     * @param start from date
     * @param end to date
     * @return list of costs
     * @throws CostManagerExceptions
     */
     List<Cost> getReport(Account account, Date start, Date end) throws CostManagerExceptions;
}
