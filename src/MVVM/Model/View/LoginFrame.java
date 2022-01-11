package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

/**
 * 'LoginFrame' :
 * This Class is AddNewCategory GUI frame screen .
 *
 * Methods:
 * (1) LoginFrame(IViewModel viewModel, IView view),
 * (2) initialization(),
 * (3) setLoginButton(),
 * (4) setSignUpButton(),
 * (5) toDispose().
 *
 */

public class LoginFrame {

    //Login frame members
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


    public LoginFrame(IViewModel viewModel, IView view) {

        // set the view and the view model members
        this.viewModel = viewModel;
        this.view = view;

        // init the frame and his controllers
        initialization();

    }

    /**
     *  'initialization()'
     * This method is initialization the frame and controllers.
     */
    private void initialization()
    {
        // set the login frame and his controllers names
        loginFrame = new JFrame();
        labelUserName = new JLabel("Username: ");
        textFieldUserName = new JTextField(15);
        labelPassword = new JLabel("Password: ");
        textFieldPassword = new JPasswordField(15);
        labelTextToSignUp = new JLabel("Don't Have Account? Sign Up!");
        loginPanel = new JPanel();

        // set the login button name, color and action
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(e -> setLoginButton());
        buttonLogin.setBackground(new Color(183,244,216));

        // set the login button name, color and action
        buttonSignUp = new JButton("SignUp");
        buttonSignUp.addActionListener(e -> setSignUpButton());
        buttonSignUp.setBackground(new Color(254,250,212));

        // set the image icon of the frame
        Image icon = Toolkit.getDefaultToolkit().getImage("src/MVVM/Model/Images/CostManagerIcon.jpg");
        loginFrame.setIconImage(icon);

        // set login panel details.
        loginPanel.setBackground(new Color(45,85,255));
        loginPanel.setLayout(new FlowLayout());
        loginPanel.add(labelUserName);
        loginPanel.add(textFieldUserName);
        loginPanel.add(labelPassword);
        loginPanel.add(textFieldPassword);
        loginPanel.add(buttonLogin);
        loginPanel.add(buttonSignUp);
        loginPanel.add(labelTextToSignUp);

        //set login frame to visible, size, layout and exit operation.
        loginFrame.setTitle("Login Form");
        loginFrame.setLayout(new BorderLayout());
        loginFrame.add(loginPanel,BorderLayout.CENTER);
        loginFrame.setSize(250,250);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null); // Give Me The Window Login In Center Of Screen
        loginFrame.setVisible(true);
    }

    /**
     * 'setLoginButton()'
     * This method make the action after pressing on create button.
     * First the method check if the user input is valid (minimum 4 characters).
     * If the input is ok we send to the view model request to log in to account.
     */
    public void setLoginButton() {
        String username = textFieldUserName.getText();
        String password = textFieldPassword.getText();
        Account account = new Account(username, password);

        if (username.length() < 4)
        {
            JOptionPane.showMessageDialog(null,"The username must be 4 or more characters!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }
        else if (password.length() < 4)
        {
            JOptionPane.showMessageDialog(null,"The password  must be 4 or more characters!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }else
        {
            viewModel.loginToAccount(account);
        }
    }

    /**
     * 'setSignUpButton()'
     * This method ask from the view to open the signup page
     */
    public void setSignUpButton() {
        view.openSignUpFrame();
    }

    /**
     * 'toDispose()'
     * This method help to the view to close this frame
     */
    public void toDispose() {
        this.loginFrame.dispose();
    }
}
