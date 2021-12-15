package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.Cost;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.sql.Date;

public class AddNewCostFrame {

    private JLabel labelCostAddNewCost;
    private JTextField textFieldCostAddNewCost;
    private JLabel labelDescriptionCostAddNewCost;
    private JTextField textDescriptionsCostAddNewCost;
    private JButton buttonAddNewCost;
    private JLabel labelCategoryAddNewCost;
    private JTextField textFieldCategoryAddNewCost;
    private JLabel labelDateAddNewCost;
    private String [] currencyList = { "Dollar", "Shekel", "Euro", "Sterling" };
    private String [] days = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String [] months = {"0","1","2","3","4","5","6","7","8","9","10","11","12"};
    private String [] years = {"2019","2020","2021","2022"};
    private JComboBox daysCost;
    private JComboBox monthCost;
    private JComboBox yearsCost;
    private JComboBox currency;
    private JPanel addNewCostPanel;
    private JFrame addNewCostFrame;
    private IViewModel viewModel;
    private Account account;


    public AddNewCostFrame(IViewModel viewModel, Account account) {
        this.viewModel = viewModel;
        this.account = account;

        addNewCostFrame = new JFrame();
        addNewCostFrame.setTitle("Add New Cost Form");
        addNewCostPanel = new JPanel();
        addNewCostFrame.getContentPane();
        buttonAddNewCost = new JButton("Add New Cost");
        labelCostAddNewCost = new JLabel("Cost: ");
        textFieldCostAddNewCost = new JTextField(10);

        labelDateAddNewCost = new JLabel("Date: ");
        daysCost = new JComboBox(days);
        monthCost = new JComboBox(months);
        yearsCost = new JComboBox(years);

        labelDescriptionCostAddNewCost = new JLabel("Description: ");
        textDescriptionsCostAddNewCost = new JTextField(10);
        labelCategoryAddNewCost = new JLabel("Category: ");
        textFieldCategoryAddNewCost = new JTextField(10);
        currency = new JComboBox(currencyList);

        labelCategoryAddNewCost.setBounds(100, 40, 100, 40);
        textFieldCategoryAddNewCost.setBounds(170,40,100,40);
        labelDateAddNewCost.setBounds(300,40,100,30);
        daysCost.setBounds(330,40,70,30);
        monthCost.setBounds(390,40,70,30);
        yearsCost.setBounds(450,40,100,30);

        labelCostAddNewCost.setBounds(100,80,100,40);
        textFieldCostAddNewCost.setBounds(170,80,100,40);
        currency.setBounds(270,80,100,50);
        labelDescriptionCostAddNewCost.setBounds(100,120,100,40);
        textDescriptionsCostAddNewCost.setBounds(100,150,300,70);

        buttonAddNewCost.setBounds(200,250,150,40);
        buttonAddNewCost.addActionListener(e -> setAddNewCostButton());
        //Back Button -----> Maybe in future

        addNewCostPanel = new JPanel();
        addNewCostPanel.setLayout(null);
        addNewCostPanel.add(labelCategoryAddNewCost);
        addNewCostPanel.add(textFieldCategoryAddNewCost);
        addNewCostPanel.add(labelDateAddNewCost);
        addNewCostPanel.add(daysCost); //
        addNewCostPanel.add(monthCost); //
        addNewCostPanel.add(yearsCost); //
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
        addNewCostFrame.setSize(550, 350);
        addNewCostFrame.setLocationRelativeTo(null);
        addNewCostFrame.setVisible(true);
    }

    public void setAddNewCostButton() {
        String username = account.getUsername();
        String description = textDescriptionsCostAddNewCost.getText();
        //int currency = this.currency.getSelectedIndex();
        String category = textFieldCategoryAddNewCost.getText();
        double amount = Double.parseDouble(textFieldCostAddNewCost.getText());
        Date date = new Date(yearsCost.getSelectedIndex() + 119, monthCost.getSelectedIndex() - 1, daysCost.getSelectedIndex());

        Cost cost = new Cost(username, category, description, amount, "dollar" , date);
        System.out.println("Matan");
        System.out.println(category);
        System.out.println(date.toString());
        System.out.println("month:" + monthCost.getSelectedIndex());
        System.out.println("day:" + daysCost.getSelectedIndex() );
        System.out.println(amount);
        System.out.println(yearsCost.getSelectedIndex());
        System.out.println(currency);
        viewModel.addNewCost(cost, account);

        //addNewCostFrame.dispose();
    }

    public void toDispose() {
        addNewCostFrame.dispose();
    }

    public static void main(String[] args) {

        //AddNewCostFrame newCostFrame = new AddNewCostFrame();
    }
}
