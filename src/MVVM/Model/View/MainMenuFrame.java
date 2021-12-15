package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IView;
import MVVM.Model.IViewModel;

import javax.swing.*;




public class MainMenuFrame {

    private JButton buttonAddNewCostMain;
    private JButton buttonReportsMain;
    private JButton buttonLogOutMain;
    private JPanel mainPanel;
    private JFrame mainFrame;
    private IViewModel viewModel;
    private Account account;

    public MainMenuFrame(IViewModel viewModel, Account account) {
        this.viewModel = viewModel;
        this.account = account;

        mainFrame = new JFrame();
        mainFrame.setTitle("Main Form");
        mainPanel = new JPanel();
        mainFrame.getContentPane();
        buttonAddNewCostMain = new JButton("Add New Cost");
        buttonReportsMain = new JButton("Reports");
        buttonLogOutMain = new JButton("Logout");

        buttonAddNewCostMain.setBounds(80, 40, 150, 40);
        buttonAddNewCostMain.addActionListener(e -> setButtonAddNewCostMain());

        buttonReportsMain.setBounds(100, 140, 100, 40);

        buttonLogOutMain.setBounds(100,230,100,40);
        buttonLogOutMain.addActionListener(e -> setButtonLogOutMain());

        mainPanel.setLayout(null);
        mainPanel.add(buttonAddNewCostMain);
        mainPanel.add(buttonReportsMain);
        mainPanel.add(buttonLogOutMain);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setSize(300, 400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    public void setButtonAddNewCostMain() {
        viewModel.startNewCost(account);
    }

    public void setButtonReportsMain() {

    }

    public void setButtonLogOutMain() {
        viewModel.logout();
    }

    public void toDispose() {
        this.mainFrame.dispose();
    }
}
