package MVVM.Model;


import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * This interface implements the model in architecture MVVM.
 */


public interface IView {

    // Set all panels and frames in login frame. (1st screen of gui)
    public void start();

    // Set IViewModel (MVVM Architecture)
    public void setIViewModel(IViewModel viewModel);

    // This method alert if user exits on signup page.
    public void showUsernameIsTaken();

    // This method alert is login to account was successfully, and continue to main menu page.
    public void LoginSuccessfully(Account account);

    // This method start new frame of adding cost.
    public void startAddNewCost(Account account);

    // This method return to the first screen in gui.
    public void LogOutFromAccount();

    // This method alert when we added new cost to database.
    public void AddNewCostSuccessfully(Cost cost, Account account);

    // This method alert if user or password have problem.
    public void UserDoesNotExist();

    // This method open the reports frame.
    public void startReports(Account account);

    // This method show the reports on frame.
    public void showReports(LinkedList<Cost> costs);

    // This method close the frame of signup
    public void closeSignUpFrame();

    // This method open signup frame.
    public void openSignUpFrame();

    public void logout();
}
