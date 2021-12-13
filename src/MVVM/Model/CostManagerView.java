package MVVM.Model;

import javax.swing.*;
import java.awt.*;

/**
 * TEXT HERE...
 */


public class CostManagerView implements IView {

    private JFrame loginFrame;
    private JPanel loginPanel;
    private JTextField textFieldUserName;
    private JTextField textFieldPassword;
    private JLabel labelUserName;
    private JLabel labelPassword;
    private JLabel labelTextToSignUp;
    private JButton buttonLogin;
    private JButton buttonSignUp;


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



    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    public static void main(String[] args) {
        CostManagerView costManagerView = new CostManagerView();
        costManagerView.loginFrame();
    }




}
