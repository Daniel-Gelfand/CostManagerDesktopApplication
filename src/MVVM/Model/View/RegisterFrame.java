package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;
import MVVM.Model.View.LoginFrame;

import javax.swing.*;
import java.awt.*;

/**
 * This Class is Gui of Register Page.
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
        this.viewModel = viewModel;
        this.view = view;

        registerFrame = new JFrame();
        labelUserNameRegister = new JLabel("Username: ");
        textFieldUserNameRegister = new JTextField(15);
        textFieldUserNameRegister.setToolTipText("Minimum 5 Characters!");

        labelPasswordRegister = new JLabel("Password: ");
        textFieldPasswordRegister = new JPasswordField(15);
        textFieldPasswordRegister.setToolTipText("Minimum 5 Characters!");

        buttonCancelRegister = new JButton("Cancel");
        buttonCancelRegister.addActionListener(e -> setButtonCancelRegister());

        buttonCreateRegister = new JButton("Create");
        buttonCreateRegister.addActionListener(e -> setButtonCreateRegister());
        labelAgreeTermsRegister = new JLabel("By Clicking 'Create', I Agree with Terms & Policy");

        labelAgreeTermsRegister.setToolTipText("<html> 1.We have access to all the information you have entered in the software.  <br> 2.We can block you at any time. <br> 3.We may use your information for your own use. </html> ");


        registerPanel = new JPanel();

        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");

        registerFrame.setIconImage(icon);
        buttonCreateRegister.setBackground(new Color(183,244,216));
        buttonCancelRegister.setBackground(new Color(241,90,34));

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
        registerFrame.setLayout(new BorderLayout());
        registerFrame.add(registerPanel,BorderLayout.CENTER);
        registerFrame.setSize(300,200);
        registerFrame.setResizable(false); ////////// ------------->
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null); // Give Me The Window Login In Center Of Screen
        registerFrame.setVisible(true);
    }

    public void setButtonCreateRegister() {

        String username = textFieldUserNameRegister.getText();
        String password = textFieldPasswordRegister.getText();

        Account newAccount = new Account(username, password);

        System.out.println("Account : " + newAccount.getUsername() + ", " + newAccount.getPassword());

        if (username.length() < 4)
        {
            JOptionPane.showMessageDialog(null,"The username must contains 5 or more characters!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }
        else if (password.length() < 4)
        {
            JOptionPane.showMessageDialog(null,"The password must contains 5 or more characters!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }else
        {
            viewModel.setupNewAccount(newAccount);
        }
    }

    public void setButtonCancelRegister()
    {
        view.closeSignUpFrame();
    }

    public void toDispose() {
        registerFrame.dispose();
    }


}
