package MVVM.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This interface implements the model in architecture MVVM.
 *
 */

public interface IModel {

    // This method sets up new account in the system.
    public void setupNewAccount(Account account) throws CostManagerExceptions;

    // This method check if account is registered in the system.
    public boolean loginToAccount(Account account) throws CostManagerExceptions;

    // This method not in use right now (Not part of requirements)
    public void addCategory(Category category, Account account) throws CostManagerExceptions;

    // This method not in use right now (Not part of requirements)
    public LinkedList<Category> getCategories(Account account) throws CostManagerExceptions;

    // This method add new cost to specific account on database.
    public void addNewCost(Cost cost) throws CostManagerExceptions;

    // This method return details about some costs by selected dates.
    public LinkedList<Cost> getReport(Account account, Date start, Date end) throws CostManagerExceptions;
}
