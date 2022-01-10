package MVVM.Model.View;

import MVVM.Model.Account;
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

    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public RegisterFrame(IViewModel viewModel) {
        this.viewModel = viewModel;

        registerFrame = new JFrame();
        labelUserNameRegister = new JLabel("Username: ");
        textFieldUserNameRegister = new JTextField(15);
        labelPasswordRegister = new JLabel("Password: ");
        textFieldPasswordRegister = new JPasswordField(15);
        buttonCancelRegister = new JButton("Cancel");
        buttonCancelRegister.addActionListener(e -> setButtonCancelRegister());
        buttonCreateRegister = new JButton("Create");
        buttonCreateRegister.addActionListener(e -> setButtonCreateRegister());
        labelAgreeTermsRegister = new JLabel("By Clicking 'Create', I Agree with Terms & Policy");

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

        //String email = textFieldEmailRegister.getText();
        String username = textFieldUserNameRegister.getText();
        String password = textFieldPasswordRegister.getText();

        Account newAccount = new Account(username, password);

        System.out.println("Account : " + newAccount.getUsername() + ", " + newAccount.getPassword());
        viewModel.setupNewAccount(newAccount);

    }

    public void setButtonCancelRegister()
    {
        viewModel.finishSignUp();
    }

    public void toDispose() {
        registerFrame.dispose();
    }


}
