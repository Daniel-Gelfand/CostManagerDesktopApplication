package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.Cost;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

/**
 * This Class is Gui of Add New Cost Page.
 *
 */

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
    private String [] months = {"0","1","2","3","4","5","6","7","8","9","10","11","12"}; // why 0 ?
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
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");

        addNewCostFrame.setIconImage(icon);
        buttonAddNewCost.setBackground(new Color(183,244,216));

        labelDateAddNewCost = new JLabel("Date: ");
        daysCost = new JComboBox(days);
        monthCost = new JComboBox(months);
        yearsCost = new JComboBox(years);
        labelDescriptionCostAddNewCost = new JLabel("Description: ");
        textDescriptionsCostAddNewCost = new JTextField(10);
        labelCategoryAddNewCost = new JLabel("Category: ");
        textFieldCategoryAddNewCost = new JTextField(10);
        currency = new JComboBox(currencyList);


        buttonAddNewCost.addActionListener(e -> setAddNewCostButton());

        addNewCostPanel = new JPanel();

        addNewCostPanel.setLayout(new FlowLayout());
        addNewCostPanel.setBackground(new Color(45,85,255));
        addNewCostPanel.add(labelCategoryAddNewCost);
        addNewCostPanel.add(textFieldCategoryAddNewCost);
        addNewCostPanel.add(labelDateAddNewCost);
        addNewCostPanel.add(daysCost);
        addNewCostPanel.add(monthCost);
        addNewCostPanel.add(yearsCost);
        addNewCostPanel.add(labelCostAddNewCost);
        addNewCostPanel.add(textFieldCostAddNewCost);
        addNewCostPanel.add(currency);
        addNewCostPanel.add(labelDescriptionCostAddNewCost);
        addNewCostPanel.add(textDescriptionsCostAddNewCost);
        addNewCostPanel.add(buttonAddNewCost);
        addNewCostFrame.setLayout(new BorderLayout());
        addNewCostPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addNewCostFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addNewCostFrame.add(addNewCostPanel);
        addNewCostFrame.setSize(570, 370);
        addNewCostFrame.setLocationRelativeTo(null);
        addNewCostFrame.setVisible(true);
    }

    public void setAddNewCostButton() {
        String username = account.getUsername();
        String description = textDescriptionsCostAddNewCost.getText();
        String currency = convertCurrencyToString(this.currency.getSelectedIndex());
        String category = textFieldCategoryAddNewCost.getText();
        double amount = Double.parseDouble(textFieldCostAddNewCost.getText());
        Date date = new Date(yearsCost.getSelectedIndex() + 119, monthCost.getSelectedIndex() - 1, daysCost.getSelectedIndex());

        Cost cost = new Cost(username, category, description, amount, currency , date);
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

    public String convertCurrencyToString(int index) {
        String currency;
        switch (index) {
            case 0:
                return "Dollar";
            case 1:
                return "Shekel";
            case 2:
                return "Euro";
            case 3:
                return "Sterling";
                default:
                    return null;
        }
    }

    public void toDispose() {
        addNewCostFrame.dispose();
    }

    public static void main(String[] args) {

        //AddNewCostFrame newCostFrame = new AddNewCostFrame();
    }
}
