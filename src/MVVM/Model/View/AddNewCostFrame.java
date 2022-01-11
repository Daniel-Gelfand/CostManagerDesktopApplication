package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.Category;
import MVVM.Model.Cost;
import MVVM.Model.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.LinkedList;

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
    //private JTextField textFieldCategoryAddNewCost;
    private JLabel labelDateAddNewCost;
    private String [] currencyList = { "Dollar", "Shekel", "Euro", "Sterling" };
    private String [] daysArray = new String[31];
    private String [] monthsArray = new String[12];
    private String [] yearsArray = new String[10];
    private String[] categoriesArray = {"sport"};
    private JComboBox daysCost;
    private JComboBox monthCost;
    private JComboBox yearsCost;
    private JComboBox currency;
    private JComboBox comboBoxCategories;
    private JPanel addNewCostPanel;
    private JFrame addNewCostFrame;
    private IViewModel viewModel;
    private Account account;


    public AddNewCostFrame(IViewModel viewModel, Account account, LinkedList<Category> categories) {
        this.viewModel = viewModel;
        this.account = account;
        this.categoriesArray = setCategoriesArray(categories);

        setDaysArray();
        setMonthArray();
        setYearsArray();

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
        daysCost = new JComboBox(daysArray);
        monthCost = new JComboBox(monthsArray);
        yearsCost = new JComboBox(yearsArray);
        labelDescriptionCostAddNewCost = new JLabel("Description: ");
        textDescriptionsCostAddNewCost = new JTextField(10);
        labelCategoryAddNewCost = new JLabel("Category: ");
        comboBoxCategories = new JComboBox(categoriesArray);
        //textFieldCategoryAddNewCost = new JTextField(10);
        currency = new JComboBox(currencyList);


        buttonAddNewCost.addActionListener(e -> setAddNewCostButton());

        addNewCostPanel = new JPanel();

        addNewCostPanel.setLayout(new FlowLayout());
        addNewCostPanel.setBackground(new Color(45,85,255));
        addNewCostPanel.add(labelCategoryAddNewCost);
        addNewCostPanel.add(comboBoxCategories);
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
        addNewCostFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addNewCostFrame.add(addNewCostPanel);
        addNewCostFrame.setSize(570, 370);
        addNewCostFrame.setResizable(false);
        addNewCostFrame.setLocationRelativeTo(null);
        addNewCostFrame.setVisible(true);
    }

    public void setAddNewCostButton() {


        if (CheckIfCategoryIsNotNull() && checkIfAmountIsValid())
        {
            String username = account.getUsername();
            String description = textDescriptionsCostAddNewCost.getText();
            String currency = convertCurrencyToString(this.currency.getSelectedIndex());
            String category = (String) comboBoxCategories.getSelectedItem();
            double amount = Double.parseDouble(textFieldCostAddNewCost.getText());
            Date date = Date.valueOf(yearsCost.getSelectedItem() + "-" + monthCost.getSelectedItem() + "-" + daysCost.getSelectedItem());

            Cost cost = new Cost(username, category, description, amount, currency , date);
            viewModel.addNewCost(cost, account);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"One or more of the fields weren't filled properly!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean CheckIfCategoryIsNotNull()
    {
        boolean isValidCost = true;

        if (textFieldCostAddNewCost.getText().length() < 1)
        {
            isValidCost = false;
        }

        return isValidCost;
    }

    public boolean checkIfAmountIsValid()
    {
        boolean isValidCost = true;
        String text = textFieldCostAddNewCost.getText();

        for (int i = 0; i < text.length(); i++) {

            if (!Character.isDigit(text.charAt(i)))
            {
                isValidCost = false;
                break;
            }
        }

        return isValidCost;
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

    public void setMonthArray()
    {
        int month = 1;

        for (int i = 0; i < monthsArray.length; i++) {
            if (month < 10)
            {
                monthsArray[i] = "0" + month++;
            }else
            {
                monthsArray[i] = String.valueOf(month++);
            }

        }
    }

    public void setDaysArray()
    {
        int day = 1;

        for (int i = 0; i < daysArray.length; i++) {
            if (day < 10)
            {
                daysArray[i] = "0" + day++;
            }else
            {
                daysArray[i] = String.valueOf(day++);
            }
        }
    }

    public void setYearsArray()
    {
        int year = 2015;

        for (int i = 0; i < yearsArray.length; i++) {
            yearsArray[i] = String.valueOf(year++);
        }
    }

    public String[] setCategoriesArray(LinkedList<Category> categories)
    {
        String [] categoriesArray = new String[categories.size()];
        int i = 0;

        for (Category category: categories) {

            categoriesArray[i++] = category.getCategory();
        }

        return categoriesArray;
    }

    public void toDispose() {
        addNewCostFrame.dispose();
    }

    public static void main(String[] args) {

        //AddNewCostFrame newCostFrame = new AddNewCostFrame();
    }
}
