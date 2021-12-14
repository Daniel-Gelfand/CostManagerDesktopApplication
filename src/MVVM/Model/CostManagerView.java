package MVVM.Model;

import javax.swing.*;
import java.awt.*;

/**
 * TEXT HERE...
 */


public class CostManagerView implements IView {

    //loginFrom
    private JFrame loginFrame;
    private JPanel loginPanel;
    private JTextField textFieldUserName;
    private JTextField textFieldPassword;
    private JLabel labelUserName;
    private JLabel labelPassword;
    private JLabel labelTextToSignUp;
    private JButton buttonLogin;
    private JButton buttonSignUp;


    //RegisterForm
    private JFrame registerFrame;
    private JPanel registerPanel;
    private JLabel labelUserNameRegister;
    private JLabel labelPasswordRegister;
    private JLabel labelEmailRegister;
    private JLabel labelAgreeTermsRegister;
    private JTextField textFieldUserNameRegister;
    private JTextField textFieldPasswordRegister;
    private JTextField textFieldEmailRegister;
    private JButton buttonCreateRegister;
    private JButton buttonCancelRegister;

    // Main From
    private JButton buttonAddNewCostMain;
    private JButton buttonReportsMain;
    private JButton buttonLogOutMain;
    private JPanel mainPanel;
    private JFrame mainFrame;

    // addNewCost Form

    private JLabel labelCostAddNewCost;
    private JTextField textFieldCostAddNewCost;
    private JLabel labelDescriptionCostAddNewCost;
    private JTextField textDescriptionsCostAddNewCost;
    private JButton buttonAddNewCost;
    private JLabel labelCategoryAddNewCost;
    private JTextField textFieldCategoryAddNewCost;
    private String [] currencyList = { "Dollar", "Shekel", "Euro", "Sterling" };
    private JComboBox currency;
    private JPanel addNewCostPanel;
    private JFrame addNewCostFrame;
    private int joker = 4;

    public void loginFrame()
    {

        /*
        loginFrame = new JFrame();
        loginPanel = new JPanel();
        loginPanelEast = new JPanel();
        textFieldPassword = new JPasswordField(15);
        textFieldUserName = new JTextField(15);
        buttonLogin = new JButton("Login");
        buttonSignUp = new JButton("Sign Up!");
        labelPassword = new JLabel("Password: ");
        labelUserName = new JLabel("Username: ");
        labelTextToSignUp = new JLabel("Dont Have Account? Sign Up!");

        loginFrame.setSize(500,500);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginPanel.setLayout(new FlowLayout());
        loginPanel.add(labelUserName);
        loginPanel.add(textFieldUserName);
        loginPanel.add(labelPassword);
        loginPanel.add(textFieldPassword);
        loginPanel.add(buttonLogin);
        loginPanel.add(labelTextToSignUp);
        loginPanel.add(buttonSignUp);
        loginFrame.setLayout(new GridLayout());
        loginFrame.add(loginPanel, new GridLayout());
        loginFrame.setVisible(true);

        */ // OLD Version

        /// NEW VERSION BY DANOY
        loginFrame = new JFrame();
        labelUserName = new JLabel("Username: ");
        labelUserName.setBounds(50, 150, 100, 30);//
        textFieldUserName = new JTextField(15);
        textFieldUserName.setBounds(150, 150, 150, 30); //
        labelPassword = new JLabel("Password: ");
        labelPassword.setBounds(50, 220, 100, 30); // --
        textFieldPassword = new JPasswordField(15);
        textFieldPassword.setBounds(150, 220, 150, 30);// --
        labelTextToSignUp = new JLabel("Don't Have Account? Sign Up!");
        labelTextToSignUp.setBounds(350,300,100,30);
        buttonLogin = new JButton("Login");
        buttonLogin.setBounds(50, 300, 100, 30); //
        buttonSignUp = new JButton("SignUp");
        buttonSignUp.setBounds(200, 300, 100, 30); //
        loginPanel = new JPanel();

        loginFrame.setTitle("Login Form");
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new FlowLayout());
        loginPanel.add(labelUserName);
        loginPanel.add(textFieldUserName);
        loginPanel.add(labelPassword);
        loginPanel.add(textFieldPassword);
        loginPanel.add(buttonLogin);
        loginPanel.add(buttonSignUp);
        loginPanel.add(labelTextToSignUp);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.add(loginPanel,BorderLayout.CENTER);
        loginFrame.setSize(250,250);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null); // Give Me The Window Login In Center Of Screen
        loginFrame.setVisible(true);

    }

    public void registerFrame()
    {
        registerFrame = new JFrame();
        labelUserNameRegister = new JLabel("Username: ");
        labelUserNameRegister.setBounds(50, 150, 100, 30);//
        textFieldUserNameRegister = new JTextField(15);
        textFieldUserNameRegister.setBounds(150, 150, 150, 30); //
        labelPasswordRegister = new JLabel("Password: ");
        labelPasswordRegister.setBounds(50, 220, 100, 30); // --
        textFieldPasswordRegister = new JPasswordField(15);
        textFieldPasswordRegister.setBounds(150, 220, 150, 30);// --
        labelEmailRegister = new JLabel("Email:        ");
        labelEmailRegister.setBounds(50,270,100,30);
        textFieldEmailRegister = new JTextField(15);
        textFieldEmailRegister.setBounds(150,290,150,30);

        buttonCancelRegister = new JButton("Create");
        buttonCancelRegister.setBounds(50, 300, 100, 30); //
        buttonCreateRegister = new JButton("Cancel");
        buttonCreateRegister.setBounds(200, 300, 100, 30); //
        labelAgreeTermsRegister = new JLabel("By Clicking 'Create', I Agree with Terms & Policy");
        labelAgreeTermsRegister.setBounds(200,300,100,30);
        registerPanel = new JPanel();

        registerFrame.setTitle("Register Form");
        registerPanel.setBackground(Color.WHITE);
        registerPanel.setLayout(new FlowLayout());
        registerPanel.add(labelUserNameRegister);
        registerPanel.add(textFieldUserNameRegister);
        registerPanel.add(labelEmailRegister);
        registerPanel.add(textFieldEmailRegister);
        registerPanel.add(labelPasswordRegister);
        registerPanel.add(textFieldPasswordRegister);
        registerPanel.add(buttonCreateRegister);
        registerPanel.add(buttonCancelRegister);
        registerPanel.add(labelAgreeTermsRegister);
        registerFrame.setLayout(new BorderLayout());
        registerFrame.add(registerPanel,BorderLayout.CENTER);
        registerFrame.setSize(300,300);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null); // Give Me The Window Login In Center Of Screen
        registerFrame.setVisible(true);

    }

    public void mainFrame()
    {

        mainFrame = new JFrame();
        mainFrame.setTitle("Main Form");
        mainPanel = new JPanel();
        mainFrame.getContentPane();
        buttonAddNewCostMain = new JButton("Add New Cost");
        buttonReportsMain = new JButton("Reports");
        buttonLogOutMain = new JButton("Logout");

        buttonAddNewCostMain.setBounds(100, 40, 100, 40);
        buttonReportsMain.setBounds(100, 140, 100, 40);
        buttonLogOutMain.setBounds(190,230,100,40);

        mainPanel.setLayout(null);
        mainPanel.add(buttonAddNewCostMain);
        mainPanel.add(buttonReportsMain);
        mainPanel.add(buttonLogOutMain);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setSize(300, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    public void addNewCostFrame()
    {
        //currency.setSelectedIndex(joker);
        addNewCostFrame = new JFrame();
        addNewCostFrame.setTitle("Add New Cost Form");
        addNewCostPanel = new JPanel();
        addNewCostFrame.getContentPane();
        buttonAddNewCost = new JButton("Add New Cost");
        labelCostAddNewCost = new JLabel("Cost: ");
        textFieldCostAddNewCost = new JTextField(10);
        labelDescriptionCostAddNewCost = new JLabel("Description: ");
        textDescriptionsCostAddNewCost = new JTextField(10);
        labelCategoryAddNewCost = new JLabel("Category: ");
        textFieldCategoryAddNewCost = new JTextField(10);
        currency = new JComboBox(currencyList);

        labelCategoryAddNewCost.setBounds(100, 40, 100, 40);
        textFieldCategoryAddNewCost.setBounds(170,40,100,40);
//        labelCostAddNewCost.setBounds();// needtodo
//        textFieldCostAddNewCost.setBounds();// needtodo
//        currency.setBounds();// needtodo
//        labelDescriptionCostAddNewCost.setBounds();// needtodo
//        textDescriptionsCostAddNewCost.setBounds();// needtodo
//        buttonAddNewCost.setBounds(); // needtodo
        // need  to this lines /\

        addNewCostPanel = new JPanel();
        addNewCostPanel.setLayout(null);
        addNewCostPanel.add(labelCategoryAddNewCost);
        addNewCostPanel.add(textFieldCategoryAddNewCost);
        addNewCostPanel.add(labelCostAddNewCost);
        addNewCostPanel.add(textFieldCostAddNewCost);
        addNewCostPanel.add(currency);
        addNewCostPanel.add(labelDescriptionCostAddNewCost);
        addNewCostPanel.add(textDescriptionsCostAddNewCost);
        addNewCostPanel.add(buttonAddNewCost);

        addNewCostPanel.setLayout(null);
        addNewCostPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addNewCostFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addNewCostFrame.add(addNewCostPanel);
        addNewCostFrame.setSize(500, 500);
        addNewCostFrame.setLocationRelativeTo(null);
        addNewCostFrame.setVisible(true);



    }





    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    public static void main(String[] args) {
        CostManagerView costManagerView = new CostManagerView();
        //costManagerView.loginFrame();
        //costManagerView.registerFrame();
        //costManagerView.mainFrame();
        costManagerView.addNewCostFrame();
    }

}
