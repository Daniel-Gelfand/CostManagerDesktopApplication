package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.Cost;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.TimerTask;

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


    // Set all panels and frames in login frame. (1st screen of gui)
    @Override
    public void start() {
        this.m_LoginFrame = new LoginFrame(viewModel);

    }

    // Set IViewModel (MVVM Architecture)
    @Override
    public void setIViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    // This method alert if user exits on signup page.
    @Override
    public void showUsernameIsTaken() {
        JOptionPane.showMessageDialog(null,"Username already exists!", "Account already exists", JOptionPane.WARNING_MESSAGE);
    }

    // This method alert is login to account was successfully, and continue to main menu page.
    @Override
    public void LoginSuccessfully(Account account) {
        this.m_LoginFrame.toDispose();
        this.m_MainMenuFrame = new MainMenuFrame(viewModel, account);
    }

    // This method start new frame of adding cost.
    @Override
    public void startAddNewCost(Account account) {
        this.m_MainMenuFrame.toDispose();
        this.m_AddNewCostFrame = new AddNewCostFrame(viewModel, account);
    }

    // This method return to the first screen in gui.
    @Override
    public void LogOutFromAccount() {
        this.m_MainMenuFrame.toDispose();
        this.m_LoginFrame = new LoginFrame(viewModel);
    }

    // This method alert when we added new cost to database.
    @Override
    public void AddNewCostSuccessfully(Cost cost, Account account) {
        JOptionPane.showMessageDialog(null,"The expense was successfully added!", "Cost added", JOptionPane.PLAIN_MESSAGE);
        this.m_AddNewCostFrame.toDispose();
        this.m_MainMenuFrame = new MainMenuFrame(viewModel, account);
    }

    // This method alert if user or password have problem.
    @Override
    public void UserDoesNotExist() {
        JOptionPane.showMessageDialog(null,"Invalid Username or Password!", "*WARNING!*", JOptionPane.WARNING_MESSAGE);
    }

    // This method open the reports frame.
    @Override
    public void startReports(Account account) {
        m_ReportsFrame = new ReportsFrame(viewModel, account);
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
        m_LoginFrame = new LoginFrame(viewModel);
    }

    // This method open signup frame.
    @Override
    public void openSignUpFrame() {
        m_LoginFrame.toDispose();
        m_RegisterFrame = new RegisterFrame(viewModel);
    }

    public static void main(String[] args) {
        //CostManagerView costManagerView = new CostManagerView();


    }

}
