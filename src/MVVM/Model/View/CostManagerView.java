package MVVM.Model.View;

import MVVM.Model.*;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 'CostManagerView' :
 *
 * This Class implements IView, manage the gui frames.
 *
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
 */


public class CostManagerView implements IView {

    //CostManagerView members
    private RegisterFrame m_RegisterFrame;
    private LoginFrame m_LoginFrame;
    private MainMenuFrame m_MainMenuFrame;
    private AddNewCostFrame m_AddNewCostFrame;
    private ReportsFrame m_ReportsFrame;
    private FrameAddNewCategory m_frameAddNewCategory;

    //ViewModel member
    private IViewModel viewModel;

    // Thread service pool
    private ExecutorService service;

    /**
     * 'CostManagerView' is a constructor.
     * In this constructor we initialize the threads.
     */
    public CostManagerView() {
        this.service = Executors.newFixedThreadPool(5);
    }


    // Set all panels and frames in login frame. (1st screen of gui)

    /**
     * 'start' is a method that sets all panels and frames in login frame. (1st screen of gui)
     */
    @Override
    public void start() {
        this.m_LoginFrame = new LoginFrame(viewModel, this);

    }


    /**
     * 'setIViewModel' is a method that sets IViewModel (MVVM Architecture)
     * @param viewModel vm
     */
    @Override
    public void setIViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }



    /**
     * 'LoginSuccessfully' is a method that alert is login to account was successfully, and continue to main menu page.
     * @param account username
     */
    @Override
    public void LoginSuccessfully(Account account) {
        this.m_LoginFrame.toDispose();
        this.m_MainMenuFrame = new MainMenuFrame(viewModel, account, this);
    }


    /**
     * 'CloseAddNewCategory is a method that close the frame of AddNewCategory and continue the flow of application.
     */
    @Override
    public void CloseAddNewCategory() {
        m_frameAddNewCategory.toDispose();
    }

    /**
     * 'startAddNewCategory' is a method that open the frame of AddNewCategory when the application still runs for the user.
     * @param account username
     */
    @Override
    public void startAddNewCategory(Account account) {
        m_frameAddNewCategory = new FrameAddNewCategory(viewModel, account);
    }

    /**
     * 'showCategories' is a method that update the list of categories to specific user.
     * @param categories categories
     */
    @Override
    public void showCategories(LinkedList<Category> categories) {
        m_AddNewCostFrame.setCategoriesArray(categories);
    }


    /**
     * 'startAddNewCost' is a method that start new frame of adding cost when the application still runs for the user.
     * @param account username
     * @param categories categories
     */
    @Override
    public void startAddNewCost(Account account, LinkedList<Category> categories) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        //Open the frame to specific account and initialize the correct categories.
                        m_AddNewCostFrame = new AddNewCostFrame(viewModel, account, categories);
                    }
                });
            }
        });
    }


    /**
     * 'LogOutFromAccount' is a method that disconnect from current account and return to the log in  screen in gui.
     */
    @Override
    public void LogOutFromAccount() {
        this.m_MainMenuFrame.toDispose();
        this.m_LoginFrame = new LoginFrame(viewModel, this);
    }


    /**
     * 'AddNewCostSuccessfully' is a method that alert when we added new cost to database when the application still runs for the user.
     * @param cost cost details
     * @param account username
     */
    @Override
    public void AddNewCostSuccessfully(Cost cost, Account account) {
        JOptionPane.showMessageDialog(null,"The expense was successfully added!", "Cost added", JOptionPane.PLAIN_MESSAGE);
        this.m_AddNewCostFrame.toDispose();
    }


    /**
     * 'UserDoesNotExist' is a method that alert if user or password have problem when the application still runs for the user.
     * @param message message
     */
    @Override
    public void UserDoesNotExist(String message) {
        JOptionPane.showMessageDialog(null,message, "*WARNING!*", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 'startReports is a method that open the reports frame for specific user when the application still runs.
     * @param account username
     */
    @Override
    public void startReports(Account account) {

        service.submit(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        m_ReportsFrame = new ReportsFrame(viewModel, account);;
                    }
                });
            }
        });
    }


    /**
     * 'showReports' is a method that update the cost table by selected date to specific user.
     * @param costs cost details
     */
    @Override
    public void showReports(LinkedList<Cost> costs) {
        m_ReportsFrame.showReportsByDate(costs);
    }


    /**
     * 'closeSignUpFrame' is a method that close the signup frame and returns the login frame.
     */
    @Override
    public void closeSignUpFrame() {
        m_RegisterFrame.toDispose();
        m_LoginFrame = new LoginFrame(viewModel, this);
    }

    /**
     * 'openSignUpFrame' is a method that open the signup frame.
     */
    @Override
    public void openSignUpFrame() {
        m_LoginFrame.toDispose();
        m_RegisterFrame = new RegisterFrame(viewModel, this);

    }

    /**
     * 'logout' is a method that disconnect the user and returns the login frame.
     */
    @Override
    public void logout() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Logout from current account.
                        LogOutFromAccount();
                    }
                });
            }
        });
    }

    /**
     * 'showErrorMessage' is a method that shows alert on the screen with the exception he catches.
     * @param message Error Message
     */
    @Override
    public void showErrorMessage(String message) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // Alert Window message
                        JOptionPane.showMessageDialog(null,message, "*WARNING!*", JOptionPane.ERROR_MESSAGE);;
                    }
                });
            }
        });

    }

}
