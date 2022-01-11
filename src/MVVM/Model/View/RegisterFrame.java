package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;
import MVVM.Model.View.LoginFrame;

import javax.swing.*;
import java.awt.*;

/**
 * 'RegisterFrame' :
 * This Class is Register Frame GUI frame screen .
 *
 * Methods:
 * (1) MainMenuFrame(IViewModel viewModel, Account account, IView view),
 * (2) initialization(),
 * (3) setButtonCreateRegister(),
 * (4) toDispose().
 *
 */
public class RegisterFrame{

    private JFrame registerFrame;
    private JPanel registerPanel;
    private JLabel labelUserNameRegister;
    private JLabel labelPasswordRegister;
    private JLabel labelAgreeTermsRegister;
    private JTextField textFieldUserNameRegister;
    private JTextField textFieldPasswordRegister;
    private JButton buttonCreateRegister;
    private JButton buttonCancelRegister;
    private IViewModel viewModel;
    private IView view;


    public RegisterFrame(IViewModel viewModel, IView view) {
        //set the view and the viewModel members and then initialization the frame.
        this.viewModel = viewModel;
        this.view = view;
        initialization();
    }

    /**
     *  'initialization()'
     * This method is initialization the frame and controllers.
     */
    private void initialization()
    {
        // init frame and his controls
        registerFrame = new JFrame("Register Page");

        //init text field and set tool tip for them
        labelUserNameRegister = new JLabel("Username: ");
        textFieldUserNameRegister = new JTextField(15);
        textFieldUserNameRegister.setToolTipText("Minimum 4 Characters!");

        //init text field and set tool tip for them
        labelPasswordRegister = new JLabel("Password: ");
        textFieldPasswordRegister = new JPasswordField(15);
        textFieldPasswordRegister.setToolTipText("Minimum 4 Characters!");

        //  set cancel name, color and action
        buttonCancelRegister = new JButton("Cancel");
        buttonCancelRegister.addActionListener(e -> setButtonCancelRegister());
        buttonCancelRegister.setBackground(new Color(241,90,34));

        // set create name, color and action
        buttonCreateRegister = new JButton("Create");
        buttonCreateRegister.addActionListener(e -> setButtonCreateRegister());
        buttonCreateRegister.setBackground(new Color(183,244,216));

        // set label agree terms register and add him action
        labelAgreeTermsRegister = new JLabel("By Clicking 'Create', I Agree with Terms & Policy");
        labelAgreeTermsRegister.setToolTipText("<html> 1.We have access to all the information you have entered in the software.  <br> 2.We can block you at any time. <br> 3.We may use your information for your own use. </html> ");

        registerPanel = new JPanel();
        // set icon image for the frame
        Image icon = Toolkit.getDefaultToolkit().getImage("src/MVVM/Model/Images/CostManagerIcon.jpg");
        registerFrame.setIconImage(icon);

        //set register title, color and adding relevant controls
        registerFrame.setTitle("Register Form");
        registerPanel.setBackground(new Color(45,85,255));
        registerPanel.setLayout(new FlowLayout());
        registerPanel.add(labelUserNameRegister);
        registerPanel.add(textFieldUserNameRegister);
        registerPanel.add(labelPasswordRegister);
        registerPanel.add(textFieldPasswordRegister);
        registerPanel.add(buttonCreateRegister);
        registerPanel.add(buttonCancelRegister);
        registerPanel.add(labelAgreeTermsRegister);

        // set main frame details
        registerFrame.setLayout(new BorderLayout());
        registerFrame.add(registerPanel,BorderLayout.CENTER);
        registerFrame.setSize(300,200);
        registerFrame.setResizable(false); ////////// ------------->
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // open the frame in the center of the screen
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setVisible(true);
    }

    /**
     * This method make the action after pressing on create button.
     * First the method check if the user input is valid (minimum 4 characters).
     * If the input is ok we send to the view model request to set up new account.
     */
    public void setButtonCreateRegister() {
        // get text from the text fields
        String username = textFieldUserNameRegister.getText();
        String password = textFieldPasswordRegister.getText();
        Account newAccount = new Account(username, password);

        // Check user input if ok so create new account.
        if (username.length() < 4)
        {
            JOptionPane.showMessageDialog(null,"The username must contains 4 or more characters!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }
        else if (password.length() < 4)
        {
            JOptionPane.showMessageDialog(null,"The password must contains 4 or more characters!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }else
        {
            viewModel.setupNewAccount(newAccount);
        }
    }

    /**
     * This method execute to the view and open the
     * first screen page (login form).
     */
    public void setButtonCancelRegister()
    {
        view.closeSignUpFrame();
    }

    /**
     * This method used by the view to close this screen page
     */
    public void toDispose() {
        registerFrame.dispose();
    }


}
