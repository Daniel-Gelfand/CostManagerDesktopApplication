package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IViewModel;
import MVVM.Model.View.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame{

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
    private IViewModel viewModel;

    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public RegisterFrame(IViewModel viewModel) {
        this.viewModel = viewModel;
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

        //Back Button -----> Maybe in future

        buttonCancelRegister = new JButton("Cancel");
        buttonCancelRegister.setBounds(50, 300, 100, 30); //
        buttonCancelRegister.addActionListener(e -> setButtonCancelRegister());

        buttonCreateRegister = new JButton("Create");
        buttonCreateRegister.setBounds(200, 300, 100, 30); //
        buttonCreateRegister.addActionListener(e -> setButtonCreateRegister());

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
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null); // Give Me The Window Login In Center Of Screen
        registerFrame.setVisible(true);
    }

    public void setButtonCreateRegister() {

        String email = textFieldEmailRegister.getText();
        String username = textFieldUserNameRegister.getText();
        String password = textFieldPasswordRegister.getText();

        Account newAccount = new Account(username, password, "Matan", "Bar", email);

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
