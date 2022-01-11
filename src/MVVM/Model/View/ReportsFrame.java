package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.Cost;
import MVVM.Model.IViewModel;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;
/**
 * 'ReportsFrame' :
 * This Class is Reports Frame GUI frame screen .
 *
 * Methods:
 * (1) MainMenuFrame(IViewModel viewModel, Account account, IView view),
 * (2) initialization(),
 * (3) setReportsButton(),
 * (4) showReportsByDate(LinkedList<Cost> costs),
 * (5) setTableByDates(String[][] data, String[] columns , double sum)
 * (6) setMonthArray()
 * (7) setDayArray()
 * (8) setYearArray()

 *
 */

public class ReportsFrame {
    private Account account;
    private IViewModel viewModel;

    //frame and controllers declaration
    private JFrame reportsFrame;
    private JPanel reportsPanel;
    private JComboBox dayStartReports;
    private JComboBox monthStartReports;
    private JComboBox yearStartReports;
    private JComboBox dayEndReports;
    private JComboBox monthEndReports;
    private JComboBox yearEndReports;
    private JLabel labelFromDateReports;
    private JLabel labelToDateReports;
    private JButton buttonSendToGetReport;
    private JTable tableReports;
    private JPanel reportsPanelNorth;
    private JPanel reportsPanelSouth;
    private JLabel labelSumReports;
    private JTextField textFieldSumReports;

    // Arrays for date combo box set them with the methods below
    private String [] daysArray = new String[31];
    private String [] monthsArray = new String[12];
    private String [] yearsArray = new String[10];


    public ReportsFrame(IViewModel viewModel, Account account) {
        this.viewModel = viewModel;
        this.account = account;
        setDaysArray();
        setMonthArray();
        setYearsArray();
        initialization();
    }

    /**
     * This method initialization the frame and is controllers.
     */
    private void initialization()
    {
        // set the main frame and title
        reportsFrame = new JFrame();
        reportsFrame.setTitle("Reports Form");

        //set the combo boxes for the Start Date the string arrays members (days, months, year)
        dayStartReports = new JComboBox(daysArray);
        monthStartReports = new JComboBox(monthsArray);
        yearStartReports = new JComboBox(yearsArray);
        // set the right label for the combo boxes
        labelFromDateReports = new JLabel("From Date: ");

        //set the combo boxes for the Start End the string arrays members (days, months, year)
        dayEndReports = new JComboBox(daysArray);
        monthEndReports = new JComboBox(monthsArray);
        yearEndReports = new JComboBox(yearsArray);
        // set the right label for the combo boxes
        labelToDateReports = new JLabel("To Date: ");

        // set the report button name action and color
        buttonSendToGetReport = new JButton("Send To Get Report");
        buttonSendToGetReport.addActionListener(e -> setReportsButton());
        buttonSendToGetReport.setBackground(new Color(183,244,216));

        // set the label that Represents the sum of cost the user chose
        labelSumReports = new JLabel("Total sum in ILS: ");
        textFieldSumReports = new JTextField(20);


        // set the table that show the cost by details
        String columns[] = { "Username", "Categories", "Description","Cost","Currency","Date"};
        String data [][] = new String[0][6];
        DefaultTableModel model = new DefaultTableModel(data,columns);
        tableReports = new JTable(model);
        tableReports.setShowGrid(true);
        tableReports.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(tableReports);
        pane.setVisible(true);
        tableReports.setPreferredScrollableViewportSize(new Dimension(450,200));
        tableReports.setFillsViewportHeight(true);


        // set the panels
        reportsPanel = new JPanel();
        reportsPanelNorth = new JPanel();
        reportsPanelSouth = new JPanel();

        // add image icon to the frame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");
        reportsFrame.setIconImage(icon);

        // set panels sizes
        reportsPanel.setPreferredSize(new Dimension(100,100));
        reportsPanelNorth.setPreferredSize(new Dimension(100,100));
        reportsPanelSouth.setPreferredSize(new Dimension(100,100));

        // adding controllers to north panel
        reportsPanelNorth.add(labelFromDateReports);
        reportsPanelNorth.add(dayStartReports);
        reportsPanelNorth.add(monthStartReports);
        reportsPanelNorth.add(yearStartReports);
        reportsPanelNorth.add(labelToDateReports);
        reportsPanelNorth.add(dayEndReports);
        reportsPanelNorth.add(monthEndReports);
        reportsPanelNorth.add(yearEndReports);
        reportsPanelNorth.add(buttonSendToGetReport);

        // adding controllers to south panel
        reportsPanelSouth.add(labelSumReports);
        reportsPanelSouth.add(textFieldSumReports);
        reportsPanel.add(pane);

        // set color to the panels
        reportsPanel.setBackground(new Color(45,85,255));
        reportsPanelNorth.setBackground(new Color(45,85,255));
        reportsPanelSouth.setBackground(new Color(45,85,255));

        // adding the panels to the frame and set his details
        reportsFrame.setLayout(new BorderLayout());
        reportsFrame.add(reportsPanel,BorderLayout.CENTER);
        reportsFrame.add(reportsPanelNorth,BorderLayout.NORTH);
        reportsFrame.add(reportsPanelSouth,BorderLayout.SOUTH);
        reportsFrame.setSize(500, 500);
        reportsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        reportsFrame.setLocationRelativeTo(null);
        reportsFrame.setResizable(false);
        reportsFrame.setVisible(true);
    }


    /**
     * setReportsButton()
     * This method ask from the view model report of expenses by user dates selection.
     */
    public void setReportsButton()
    {
        // init start date and end date by user selection.
        Date start = Date.valueOf(yearStartReports.getSelectedItem() + "-" + monthStartReports.getSelectedItem() + "-" + dayStartReports.getSelectedItem());
        Date end = Date.valueOf(yearEndReports.getSelectedItem() + "-" + monthEndReports.getSelectedItem() + "-" + dayEndReports.getSelectedItem());
        viewModel.getReport(account, start, end);
    }

    /**
     * showReportsByDate(LinkedList<Cost> costs)
     * this method make a matrix of data for the table model
     * @param costs - this methode get list of cost by specific user
     */
    public void showReportsByDate(LinkedList<Cost> costs) {

        String columns[] = { "Username", "Categories", "Description","Cost","Currency","Date"};
        String data [][] = new String[costs.size()][6];
        double sumOfCosts = 0;
        int i = 0;

        for (Cost c: costs) {
            String username = c.getUsernames();
            String categories = c.getCategories();
            String description = c.getDescription();
            double cost = c.getCostAmount();
            String currency = c.getCurrency();
            Date date = c.getDate();
            sumOfCosts += changeToIsraeliShekel(currency, cost);

            data[i][0] = username;
            data[i][1] = categories;
            data[i][2] = description;
            data[i][3] = cost + "";
            data[i][4] = currency;
            data[i][5] = date.toString();
            i++;
        }
        setTableByDates(data,columns , sumOfCosts);
    }

    /**
     * This method change the cost amount from foreign exchange to ILS.
     * @param currency
     * @param costAmount
     * @return
     */
    public double changeToIsraeliShekel(String currency, double costAmount) {
        double totalSum = 0;

        if (currency.equals("Dollar"))
        {
            totalSum += costAmount * 3.12;
        }
        else if (currency.equals("Euro"))
        {
            totalSum += costAmount * 3.54;
        }
        else if (currency.equals("Sterling"))
        {
            totalSum += costAmount * 3.1;
        }
        else
        {
            totalSum = costAmount;
        }

        return totalSum;
    }


    /**
     * setTableByDates(String[][] data, String[] columns , double sum)
     * This method create new table by user date selection and show the costs on the table that show on the screen
     * @param data
     * @param columns
     * @param sum
     */
    public void setTableByDates(String[][] data, String[] columns , double sum) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DefaultTableModel model = new DefaultTableModel(data,columns);
                tableReports.setModel(model);
                textFieldSumReports.setText(sum + "");
            }
        });
    }

    /**
     * setMonthArray()
     * This method set months array
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
     * This method set months array
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
}
