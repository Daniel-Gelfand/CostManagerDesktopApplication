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
    private IView view;

    private JPanel mainMenuPanelNorth;
    private JPanel mainMenuPanelCenter;
    private JPanel mainMenuPanelSouth;

    public MainMenuFrame(IViewModel viewModel, Account account, IView view) {
        this.viewModel = viewModel;
        this.account = account;
        this.view = view;


        mainFrame = new JFrame();
        mainFrame.setTitle("Main Form");
        mainFrame.setLayout(new BorderLayout());

        buttonAddNewCostMain = new JButton("Add New Cost");
        buttonReportsMain = new JButton("Reports");
        buttonLogOutMain = new JButton("Logout");
        labelHelloUser = new JLabel("Hello " + account.getUsername() + "!");

        //labelHelloUser.setLabelFor(buttonAddNewCostMain);
        //labelHelloUser.setFont(new Font("", Font.BOLD, 14));

        buttonAddNewCostMain.setBackground(new Color(183,244,216));
        buttonReportsMain.setBackground(new Color(254,250,212));
        buttonLogOutMain.setBackground(new Color(241,90,34));

        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");

        mainMenuPanelNorth = new JPanel();
        mainMenuPanelCenter = new JPanel();
        mainMenuPanelSouth = new JPanel();
        mainMenuPanelNorth.setPreferredSize(new Dimension(100,100));
        mainMenuPanelCenter.setPreferredSize(new Dimension(100,100));
        mainMenuPanelSouth.setPreferredSize(new Dimension(100,100));
        mainMenuPanelNorth.setBackground(new Color(45,85,255));
        mainMenuPanelCenter.setBackground(new Color(45,85,255));
        mainMenuPanelSouth.setBackground(new Color(45,85,255));


        mainFrame.setIconImage(icon);
        buttonAddNewCostMain.addActionListener(e -> setButtonAddNewCostMain());
        buttonReportsMain.addActionListener(e -> setButtonReportsMain());
        buttonLogOutMain.addActionListener(e -> setButtonLogOutMain());


        mainMenuPanelNorth.add(labelHelloUser);
        mainMenuPanelNorth.add(buttonAddNewCostMain);
        mainMenuPanelCenter.add(buttonReportsMain);
        mainMenuPanelSouth.add(buttonLogOutMain);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainMenuPanelNorth);
        mainFrame.add(mainMenuPanelCenter);
        mainFrame.add(mainMenuPanelSouth);
        mainFrame.setSize(300, 350);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainMenuPanelNorth,BorderLayout.NORTH);
        mainFrame.add(mainMenuPanelCenter,BorderLayout.CENTER);
        mainFrame.add(mainMenuPanelSouth,BorderLayout.SOUTH);

        mainMenuPanelNorth.setLayout(new FlowLayout());
        mainMenuPanelCenter.setLayout(new FlowLayout());
        mainMenuPanelSouth.setLayout(new FlowLayout());
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public void setButtonAddNewCostMain() {
        view.startAddNewCost(account);
    }

    public void setButtonReportsMain() {
        view.startReports(account);
    }

    public void setButtonLogOutMain() {
        viewModel.logout();
    }

    public void toDispose() {
        this.mainFrame.dispose();
    }
}
