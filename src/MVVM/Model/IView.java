package MVVM.Model;


import java.util.LinkedList;

/**
 * 'IView':
 *
 * This interface implements the model in architecture MVVM.
 * Methods:
 * (1) start(),
 * (2) setIViewModel(IViewModel viewModel),
 * (3) LoginSuccessfully(Account account),
 * (4) startAddNewCost(Account account, LinkedList<Category> categories),
 * (5) LogOutFromAccount(),
 * (6) AddNewCostSuccessfully(Cost cost, Account account),
 * (7) UserDoesNotExist(String message),
 * (8) startReports(Account account),
 * (9) showReports(LinkedList<Cost> costs),
 * (10) showCategories(LinkedList<Category> categories),
 * (11) closeSignUpFrame(),
 * (12) openSignUpFrame(),
 * (13) logout(),
 * (14) CloseAddNewCategory(),
 * (15) startAddNewCategory(Account account),
 * (16) showErrorMessage(String message).
 *
 */
public interface IView {


    /**
     * 'start' is a method that sets all panels and frames in login frame. (1st screen of gui)
     */
    public void start();


    /**
     * 'setIViewModel' sets IViewModel (MVVM Architecture)
     * @param viewModel vm
     */
    public void setIViewModel(IViewModel viewModel);


    /**
     * 'LoginSuccessfully' is a method that alert if login to account was successfully, and continue to main menu page.
     * @param account username
     */
    public void loginSuccessfully(Account account);

    /**
     * 'startAddNewCost' is a method that start new frame of adding cost.
     * @param account username
     * @param categories categories
     */
    public void startAddNewCost(Account account, LinkedList<Category> categories);


    /**
     * 'LogOutFromAccount' is a method that return to the first screen in gui.
     */
    public void logOutFromAccount();

    /**
     * 'AddNewCostSuccessfully' is a method that alert when we added new cost to database.
     * @param cost cost details
     * @param account username
     */
    public void addNewCostSuccessfully(Cost cost, Account account);


    /**
     * 'UserDoesNotExist' is a method that alert if user or password have problem or not exists.
     * @param message message
     */
    public void userDoesNotExist(String message);


    /**
     * 'startReports' is a method that open the reports frame.
     * @param account username
     */
    public void startReports(Account account);

    /**
     * 'showReports' is a method that shows the reports on frame.
     * @param costs cost details
     */
    public void showReports(LinkedList<Cost> costs);

    /**
     * 'showCategories' is a method that shows all categories to specific account in AddNewCost frame.
     * @param categories categories
     */
    public void showCategories(LinkedList<Category> categories);


    /**
     * 'closeSignUpFrame' is a method that close the frame of signup.
     */
    public void closeSignUpFrame();


    /**
     * 'openSignUpFrame' is a methoad that open signup frame.
     */
    public void openSignUpFrame();

    /**
     * 'logout' is a method that logout account from system, and return the MainMenu.
     */
    public void logout();

    /**
     * 'CloseAddNewCategory' is a method that  close the AddNewCategory Frame.(when we click on close).
     */
    void closeAddNewCategory();

    /**
     * 'startAddNewCategory' is a method that adding new category to specific account.
     * @param account username
     */
    void startAddNewCategory(Account account);

    /**
     * 'showErrorMessage' is a method that shows alert on the screen with the exception he catches.
     * @param message Error Message
     */
    void showErrorMessage(String message);
}
