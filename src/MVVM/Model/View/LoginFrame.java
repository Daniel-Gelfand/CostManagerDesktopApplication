package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

/**
 * This Class is Gui of Login Page.
 *
 */

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
    private IView view;


    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public LoginFrame(IViewModel viewModel, IView view) {

        this.viewModel = viewModel;
        this.view = view;

        loginFrame = new JFrame();

        labelUserName = new JLabel("Username: ");
        textFieldUserName = new JTextField(15);
        labelPassword = new JLabel("Password: ");
        textFieldPassword = new JPasswordField(15);
        labelTextToSignUp = new JLabel("Don't Have Account? Sign Up!");
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(e -> setLoginButton());
        buttonSignUp = new JButton("SignUp");
        buttonSignUp.addActionListener(e -> setSignUpButton());
        loginPanel = new JPanel();
        buttonLogin.setBackground(new Color(183,244,216));
        buttonSignUp.setBackground(new Color(254,250,212));

        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");

        loginFrame.setIconImage(icon);
        loginFrame.setTitle("Login Form");
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBackground(new Color(45,85,255));
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
        view.openSignUpFrame();
    }

    public void toDispose() {
        this.loginFrame.dispose();
    }
}
