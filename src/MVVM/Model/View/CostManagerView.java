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



    @Override
    public void start() {
        this.m_LoginFrame = new LoginFrame(viewModel);

    }

    @Override
    public void setIViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void showUsernameIsTaken() {
        JOptionPane.showMessageDialog(null,"Username already exists!", "Account already exists", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void LoginSuccessfully(Account account) {
        this.m_LoginFrame.toDispose();
        this.m_MainMenuFrame = new MainMenuFrame(viewModel, account);
    }

    @Override
    public void startAddNewCost(Account account) {
        this.m_MainMenuFrame.toDispose();
        this.m_AddNewCostFrame = new AddNewCostFrame(viewModel, account);
    }

    @Override
    public void LogOutFromAccount() {
        this.m_MainMenuFrame.toDispose();
        this.m_LoginFrame = new LoginFrame(viewModel);
    }

    @Override
    public void AddNewCostSuccessfully(Cost cost, Account account) {
        JOptionPane.showMessageDialog(null,"The expense was successfully added!", "Cost added", JOptionPane.PLAIN_MESSAGE);
        this.m_AddNewCostFrame.toDispose();
        this.m_MainMenuFrame = new MainMenuFrame(viewModel, account);
    }

    @Override
    public void UserDoesNotExist() {
        JOptionPane.showMessageDialog(null,"User does not exist!", "Cost added", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void startReports(Account account) {
        m_ReportsFrame = new ReportsFrame(viewModel, account);
    }

    @Override
    public void showReports(LinkedList<Cost> costs) {
        m_ReportsFrame.showReportsByDate(costs);
    }

    public static void main(String[] args) {
        //CostManagerView costManagerView = new CostManagerView();


    }

}
