package MVVM.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * TEXT HERE...
 */


public interface IModel {
    public void setupNewAccount(Account account) throws CostManagerExceptions;
    public boolean loginToAccount(Account account) throws CostManagerExceptions;
    public void addCategory(Category category) throws CostManagerExceptions;
    public List<Category> getCategories() throws CostManagerExceptions;
    public void addNewCost(Cost cost) throws CostManagerExceptions;
    public LinkedList<Cost> getReport(Account account, Date start, Date end) throws CostManagerExceptions;
    public void logout() throws CostManagerExceptions;



}
