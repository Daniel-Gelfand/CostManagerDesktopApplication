package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.awt.*;

public class LoginFrame {
    private JFrame loginFrame;
    private JPanel loginPanel;
    private JTextField textFieldUserName;
    private JTextField textFieldPassword;
    private JLabel labelUserName;
    private JLabel labelPassword;
    private JLabel labelTextToSignUp;
    private JButton buttonLogin;
    private JButton buttonSignUp;
    private IViewModel viewModel;


    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public LoginFrame(IViewModel viewModel) {

        this.viewModel = viewModel;

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
        buttonLogin.addActionListener(e -> setLoginButton());

        buttonSignUp = new JButton("SignUp");
        buttonSignUp.setBounds(200, 300, 100, 30); //
        buttonSignUp.addActionListener(e -> setSignUpButton());

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
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null); // Give Me The Window Login In Center Of Screen
        loginFrame.setVisible(true);
    }

    public void setLoginButton() {
        String username = textFieldUserName.getText();
        String password = textFieldPassword.getText();
        Account account = new Account(username, password, "Matan", "Bar", "aaa");

        viewModel.loginToAccount(account);
    }

    public void setSignUpButton() {
        viewModel.openSignUpPage();
    }

    public void toDispose() {
        this.loginFrame.dispose();
    }
}
