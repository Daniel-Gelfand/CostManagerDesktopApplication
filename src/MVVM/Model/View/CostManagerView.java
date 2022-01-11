package MVVM.Model.View;

import MVVM.Model.*;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TEXT HERE...
 */


public class CostManagerView implements IView {

    //View Model
    private IViewModel viewModel;
    private RegisterFrame m_RegisterFrame;
    private LoginFrame m_LoginFrame;
    private MainMenuFrame m_MainMenuFrame;
    private AddNewCostFrame m_AddNewCostFrame;
    private ReportsFrame m_ReportsFrame;
    private ExecutorService service;
    private FrameAddNewCategory m_frameAddNewCategory;

    public CostManagerView() {
        this.service = Executors.newFixedThreadPool(5);
    }


    // Set all panels and frames in login frame. (1st screen of gui)
    @Override
    public void start() {
        this.m_LoginFrame = new LoginFrame(viewModel, this);

    }

    // Set IViewModel (MVVM Architecture)
    @Override
    public void setIViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }


    // This method alert is login to account was successfully, and continue to main menu page.
    @Override
    public void LoginSuccessfully(Account account) {
        this.m_LoginFrame.toDispose();
        this.m_MainMenuFrame = new MainMenuFrame(viewModel, account, this);
    }

    // This method start new frame of adding cost.
    @Override
    public void startAddNewCost(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            m_AddNewCostFrame = new AddNewCostFrame(viewModel, account);
                        }
                    });
                }catch (Exception e) {

                }
            }
        });
    }

    // This method return to the first screen in gui.
    @Override
    public void LogOutFromAccount() {
        this.m_MainMenuFrame.toDispose();  // צריך לשים לב אם רוצים לכבות את כל התפריטים ברגע שמתנתקים
        //m_frameAddNewCategory.toDispose();
        this.m_LoginFrame = new LoginFrame(viewModel, this);
    }

    // This method alert when we added new cost to database.
    @Override
    public void AddNewCostSuccessfully(Cost cost, Account account) {
        JOptionPane.showMessageDialog(null,"The expense was successfully added!", "Cost added", JOptionPane.PLAIN_MESSAGE);
        this.m_AddNewCostFrame.toDispose();
        this.m_MainMenuFrame = new MainMenuFrame(viewModel, account, this);
    }

    // This method alert if user or password have problem.
    @Override
    public void UserDoesNotExist(String message) {
        JOptionPane.showMessageDialog(null,message, "*WARNING!*", JOptionPane.WARNING_MESSAGE);
    }

    // This method open the reports frame.
    @Override
    public void startReports(Account account) {

        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            m_ReportsFrame = new ReportsFrame(viewModel, account);;
                        }
                    });
                }
                catch (Exception e) {

                }
            }
        });
    }

    // This method show the reports on frame.
    @Override
    public void showReports(LinkedList<Cost> costs) {
        m_ReportsFrame.showReportsByDate(costs);
    }

    // This method close the frame of signup
    @Override
    public void closeSignUpFrame() {
        m_RegisterFrame.toDispose();
        m_LoginFrame = new LoginFrame(viewModel, this);
    }

    // This method open signup frame.
    @Override
    public void openSignUpFrame() {
        m_LoginFrame.toDispose();
        m_RegisterFrame = new RegisterFrame(viewModel, this);

    }

    @Override
    public void logout() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        LogOutFromAccount();
                    }
                });
            }
        });
    }

    @Override
    public void CloseAddNewCategory() {
        m_frameAddNewCategory.toDispose();
    }

    @Override
    public void startAddNewCategory() {
        m_frameAddNewCategory = new FrameAddNewCategory(viewModel);
    }

    @Override
    public void showErrorMessage(String message) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(null,message, "*WARNING!*", JOptionPane.ERROR_MESSAGE);;
                    }
                });
            }
        });

    }

}
