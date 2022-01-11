package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * 'MainMenuFrame' :
 * This Class is MainMenu GUI frame screen .
 *
 * Methods:
 * (1) MainMenuFrame(IViewModel viewModel, Account account, IView view),
 * (2) initialization(),
 * (3) setButtonAddNewCategory(),
 * (4) setButtonAddNewCostMain(),
 * (5) setButtonReportsMain()(),
 * (6) setButtonLogOutMain(),
 * (7) toDispose()
 *
 */

public class MainMenuFrame {

    // the members that represent the form
    private JButton buttonAddNewCostMain;
    private JButton buttonReportsMain;
    private JButton buttonLogOutMain;
    private JButton buttonAddNewCategory;
    private JFrame mainFrame;
    private Account account;
    private JLabel labelHelloUser;
    private JPanel mainMenuPanelNorth;
    private JPanel mainMenuPanelCenter;
    // this members connect between the screens and from the view to the model.
    private IView view;
    private IViewModel viewModel;

    public MainMenuFrame(IViewModel viewModel, Account account, IView view) {
        //set the view and the view model to manage screens and make action on the database.
        this.viewModel = viewModel;
        this.account = account;
        this.view = view;
        // init the frame and the controllers.
        initialization();
    }

    private void initialization() {
        // set the main menu frame
        mainFrame = new JFrame();
        mainFrame.setTitle("Main Form");
        mainFrame.setLayout(new BorderLayout());

        // set new buttons and give them names
        buttonAddNewCostMain = new JButton("Add New Cost");
        buttonReportsMain = new JButton("Reports");
        buttonLogOutMain = new JButton("Logout");
        buttonAddNewCategory = new JButton("Add New Category");

        // set new label that show how is the account that connect
        labelHelloUser = new JLabel("Hello " + account.getUsername() + "! Please chose your action");
        labelHelloUser.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        labelHelloUser.setForeground(new Color(0xAECEEE));

        //set add new cost button size and color
        buttonAddNewCostMain.setBackground(new Color(183,244,216));
        buttonAddNewCostMain.setPreferredSize(new Dimension(200, 30));

        //set reports button size and color
        buttonReportsMain.setBackground(new Color(254,250,212));
        buttonReportsMain.setPreferredSize(new Dimension(200, 30));

        //set logout button size and color
        buttonLogOutMain.setBackground(new Color(241,90,34));
        buttonLogOutMain.setPreferredSize(new Dimension(200, 30));

        //set add new category button size and color
        buttonAddNewCategory.setBackground(new Color(241, 200, 200));
        buttonAddNewCategory.setPreferredSize(new Dimension(200, 30));

        Image icon = Toolkit.getDefaultToolkit().getImage("src/MVVM/Model/Images/CostManagerIcon.jpg");

        //set the north panel size and color
        mainMenuPanelNorth = new JPanel();
        mainMenuPanelNorth.setBackground(new Color(45,85,255));
        mainMenuPanelNorth.setPreferredSize(new Dimension(100,100));

        //set the center panel size and color
        mainMenuPanelCenter = new JPanel();
        mainMenuPanelCenter.setBackground(new Color(45,85,255));
        mainMenuPanelCenter.setPreferredSize(new Dimension(100,100));

        //set action to buttons
        buttonAddNewCostMain.addActionListener(e -> setButtonAddNewCostMain());
        buttonReportsMain.addActionListener(e -> setButtonReportsMain());
        buttonLogOutMain.addActionListener(e -> setButtonLogOutMain());
        buttonAddNewCategory.addActionListener(e -> setButtonAddNewCategory());

        // adding label and buttons to the right panels
        mainMenuPanelNorth.add(labelHelloUser);
        mainMenuPanelNorth.add(buttonAddNewCostMain);
        mainMenuPanelNorth.add(buttonAddNewCategory);
        mainMenuPanelNorth.add(buttonReportsMain);
        mainMenuPanelNorth.add(buttonLogOutMain);

        //set main frame details
        mainFrame.setIconImage(icon);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(320, 250);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainMenuPanelNorth,BorderLayout.CENTER);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    /**
     *  setButtonAddNewCategory()
     * this method open add new category screen
     */
    private void setButtonAddNewCategory() {
        view.startAddNewCategory(account);
    }

    /**
     * setButtonAddNewCostMain()
     * this method open add new cost screen
     */
    public void setButtonAddNewCostMain() {
        viewModel.addNewCost(account);
    }

    /**
     * setButtonReportsMain()
     * this method open report screen
     */
    public void setButtonReportsMain() {
        view.startReports(account);
    }

    /**
     * setButtonLogOutMain()
     * this method return the user to login screen
     */
    public void setButtonLogOutMain() {
        view.logout();
    }

    /**
     * toDispose()
     * this method help close the screen from CostManagerView
     */
    public void toDispose() {
        this.mainFrame.dispose();
    }
}