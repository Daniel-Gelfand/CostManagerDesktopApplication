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
 * 'AddNewCostFrame' :
 *
 * This Class is Gui of Add New Cost Page.
 *
 * Methods:
 * (1) AddNewCostFrame(IViewModel viewModel, Account account, LinkedList<Category> categories),
 * (2) initialization(),
 * (3) setAddNewCostButton(),
 * (4) CheckIfCategoryIsNotNull(),
 * (5) checkIfAmountIsValid(),
 * (6) convertCurrencyToString(int index),
 * (7) setMonthArray(),
 * (8) setYearsArray(),
 * (9) setCategoriesArray(LinkedList<Category> categories)
 * (10) toDispose()
 */

public class AddNewCostFrame {

    // The members represent the add new cost form
    private JLabel labelCostAddNewCost;
    private JTextField textFieldCostAddNewCost;
    private JLabel labelDescriptionCostAddNewCost;
    private JTextField textDescriptionsCostAddNewCost;
    private JButton buttonAddNewCost;
    private JLabel labelCategoryAddNewCost;
    private JLabel labelDateAddNewCost;
    private JComboBox daysCost;
    private JComboBox monthCost;
    private JComboBox yearsCost;
    private JComboBox currency;
    private JComboBox comboBoxCategories;
    private JPanel addNewCostPanel;
    private JFrame addNewCostFrame;

    // The members represent the arrays that used by the controllers
    private String [] currencyList = { "Dollar", "Shekel", "Euro", "Sterling" };
    private String [] daysArray = new String[31];
    private String [] monthsArray = new String[12];
    private String [] yearsArray = new String[10];
    private String[] categoriesArray;

    // The members represent the view model and the account that ask to add new cost.
    private IViewModel viewModel;
    private Account account;


    public AddNewCostFrame(IViewModel viewModel, Account account, LinkedList<Category> categories) {
        this.viewModel = viewModel;
        this.account = account;
        this.categoriesArray = setCategoriesArray(categories);
        setDaysArray();
        setMonthArray();
        setYearsArray();
        initialization();
    }

    /**
     *  'initialization()'
     * This method is initialization the frame and controllers.
     */
    private void initialization()
    {
        // set the panel and the frame
        addNewCostFrame = new JFrame();
        addNewCostPanel = new JPanel();
        addNewCostFrame.getContentPane();

        // set the button add new cost name, color and action
        buttonAddNewCost = new JButton("Add New Cost");
        buttonAddNewCost.addActionListener(e -> setAddNewCostButton());
        buttonAddNewCost.setBackground(new Color(183,244,216));

        // set the label that represent the amount of the cost
        labelCostAddNewCost = new JLabel("Cost: ");
        textFieldCostAddNewCost = new JTextField(10);

        // set the label that represent the date and the right combo boxes
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


        //set the panel and adding to him the right controllers
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

        // set the frame details
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");
        addNewCostFrame.setIconImage(icon);
        addNewCostFrame.setTitle("Add New Cost Form");
        addNewCostFrame.setLayout(new BorderLayout());
        addNewCostPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addNewCostFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addNewCostFrame.add(addNewCostPanel);
        addNewCostFrame.setSize(570, 370);
        addNewCostFrame.setResizable(false);
        addNewCostFrame.setLocationRelativeTo(null);
        addNewCostFrame.setVisible(true);
    }

    /**
     * 'setAddNewCostButton()'
     * This method add new account to database
     * first check if the user type selection was valid
     * if it is so ask from the view model to connect to the model to add the cost
     * if it isn't show right message to the user.
     */
    public void setAddNewCostButton() {
        if (CheckIfCategoryIsNotNull() && checkIfAmountIsValid())
        {

            // set the members by the user selection
            String username = account.getUsername();
            String description = textDescriptionsCostAddNewCost.getText();
            String currency = convertCurrencyToString(this.currency.getSelectedIndex());
            String category = (String) comboBoxCategories.getSelectedItem();
            double amount = Double.parseDouble(textFieldCostAddNewCost.getText());
            Date date = Date.valueOf(yearsCost.getSelectedItem() + "-" + monthCost.getSelectedItem() + "-" + daysCost.getSelectedItem());
            // create new cost by the members and ask from the view model to add new cost for specific account.
            Cost cost = new Cost(username, category, description, amount, currency , date);
            viewModel.addNewCost(cost, account);
        }
        else
        {
            JOptionPane.showMessageDialog(null,"One or more of the fields weren't filled properly!", "*WARNING!*", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * make checking if the user input is valid
     * @return boolean
     */
    public boolean CheckIfCategoryIsNotNull()
    {
        boolean isValidCost = true;

        if (textFieldCostAddNewCost.getText().length() < 1)
        {
            isValidCost = false;
        }

        return isValidCost;
    }

    /**
     * make checking if the user input is valid
     * @return boolean
     */
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

    /**
     * return the right currency by user selection
     */
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

    /**
     * setMonthArray()
     * This method set MonthArray
     */
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

    /**
     * setDaysArray()
     * This method set DaysArray
     */
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

    /**
     * setYearsArray()
     * This method set years array
     */
    public void setYearsArray()
    {
        int year = 2015;

        for (int i = 0; i < yearsArray.length; i++) {
            yearsArray[i] = String.valueOf(year++);
        }
    }

    /**
     * make checking if the user input is valid
     * @return boolean
     */
    public String[] setCategoriesArray(LinkedList<Category> categories)
    {
        String [] categoriesArray = new String[categories.size()];
        int i = 0;

        for (Category category: categories) {

            categoriesArray[i++] = category.getCategory();
        }

        return categoriesArray;
    }

    /**
     * 'toDispose()'
     * This method help to the view to close this frame
     */
    public void toDispose() {
        addNewCostFrame.dispose();
    }
}
