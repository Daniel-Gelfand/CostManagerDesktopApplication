package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * This Class is Gui of Main Menu Page.
 *
 */


public class MainMenuFrame {

    private JButton buttonAddNewCostMain;
    private JButton buttonReportsMain;
    private JButton buttonLogOutMain;
    private JPanel mainPanel;
    private JFrame mainFrame;
    private IViewModel viewModel;
    private Account account;
    private JLabel labelHelloUser;

    public MainMenuFrame(IViewModel viewModel, Account account) {
        this.viewModel = viewModel;
        this.account = account;


        mainFrame = new JFrame();
        mainFrame.setTitle("Main Form");
        mainFrame.setLayout(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(45,85,255));
        mainFrame.getContentPane();
        buttonAddNewCostMain = new JButton("Add New Cost");
        buttonReportsMain = new JButton("Reports");
        buttonLogOutMain = new JButton("Logout");
        labelHelloUser = new JLabel("Hello " + account.getUsername() + "!");
        labelHelloUser.setBounds(2,2,150,40);
        labelHelloUser.setFont(new Font("", Font.BOLD, 14));
        buttonAddNewCostMain.setBackground(new Color(183,244,216));
        buttonReportsMain.setBackground(new Color(254,250,212));
        buttonLogOutMain.setBackground(new Color(241,90,34));

        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");

        mainFrame.setIconImage(icon);
        buttonAddNewCostMain.addActionListener(e -> setButtonAddNewCostMain());
        buttonReportsMain.addActionListener(e -> setButtonReportsMain());
        buttonLogOutMain.addActionListener(e -> setButtonLogOutMain());
        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(labelHelloUser);
        mainPanel.add(buttonAddNewCostMain);
        mainPanel.add(buttonReportsMain);
        mainPanel.add(buttonLogOutMain);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setSize(300, 350);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    public void setButtonAddNewCostMain() {
        viewModel.startNewCost(account);
    }

    public void setButtonReportsMain() {
        viewModel.goToReports(account);

    }

    public void setButtonLogOutMain() {
        viewModel.logout();
    }

    public void toDispose() {
        this.mainFrame.dispose();
    }
}
