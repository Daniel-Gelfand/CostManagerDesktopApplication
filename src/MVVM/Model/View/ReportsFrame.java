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
 * This Class is Gui of Reports Page.
 *
 */

public class ReportsFrame {
    private Account account;
    private IViewModel viewModel;

    private JFrame reportsFrame;
    private JPanel reportsPanel;
    private JComboBox dayStartReports;
    private JComboBox monthStartReports;
    private JComboBox yearStartReports;
    private String [] daysArray = new String[31];
    private String [] monthsArray = new String[12];
    private String [] yearsArray = new String[10];
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


    public ReportsFrame(IViewModel viewModel, Account account) {
        this.viewModel = viewModel;
        this.account = account;

        setDaysArray();
        setMonthArray();
        setYearsArray();

        reportsFrame = new JFrame();
        reportsFrame.setTitle("Reports Form");

        dayStartReports = new JComboBox(daysArray);
        monthStartReports = new JComboBox(monthsArray);
        yearStartReports = new JComboBox(yearsArray);

        dayEndReports = new JComboBox(daysArray);
        monthEndReports = new JComboBox(monthsArray);
        yearEndReports = new JComboBox(yearsArray);
        labelFromDateReports = new JLabel("From Date: ");
        labelToDateReports = new JLabel("To Date: ");


        buttonSendToGetReport = new JButton("Send To Get Report");
        buttonSendToGetReport.addActionListener(e -> setReportsButton());

        labelSumReports = new JLabel("Total sum in ILS: ");
        textFieldSumReports = new JTextField(20);

        reportsFrame.setLayout(new BorderLayout());

        String columns[] = { "Username", "Categories", "Description","Cost","Currency","Date"};
        String data [][] = new String[0][6];
        DefaultTableModel model = new DefaultTableModel(data,columns);
        tableReports = new JTable(model);
        tableReports.setShowGrid(true);
        tableReports.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(tableReports);
        reportsPanel = new JPanel();
        reportsPanelNorth = new JPanel();
        reportsPanelSouth = new JPanel();

        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon1.jpg");
        reportsFrame.setIconImage(icon);
        buttonSendToGetReport.setBackground(new Color(183,244,216));






        reportsPanel.setPreferredSize(new Dimension(100,100));
        reportsPanelNorth.setPreferredSize(new Dimension(100,100));
        reportsPanelSouth.setPreferredSize(new Dimension(100,100));

        reportsPanelNorth.add(labelFromDateReports);
        reportsPanelNorth.add(dayStartReports);
        reportsPanelNorth.add(monthStartReports);
        reportsPanelNorth.add(yearStartReports);
        reportsPanelNorth.add(labelToDateReports);
        reportsPanelNorth.add(dayEndReports);
        reportsPanelNorth.add(monthEndReports);
        reportsPanelNorth.add(yearEndReports);
        reportsPanelNorth.add(buttonSendToGetReport);
        reportsPanelSouth.add(labelSumReports);
        reportsPanelSouth.add(textFieldSumReports);
        reportsPanel.add(pane);


        reportsPanel.setBackground(new Color(45,85,255));
        reportsPanelNorth.setBackground(new Color(45,85,255));
        reportsPanelSouth.setBackground(new Color(45,85,255));

        reportsFrame.add(reportsPanel,BorderLayout.CENTER);
        reportsFrame.add(reportsPanelNorth,BorderLayout.NORTH);
        reportsFrame.add(reportsPanelSouth,BorderLayout.SOUTH);
        reportsFrame.setSize(500, 500);
        reportsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        reportsFrame.setLocationRelativeTo(null);
        reportsFrame.setResizable(false);
        reportsFrame.setVisible(true);


    }

    public void setReportsButton()
    {
        System.out.println(yearStartReports.getSelectedItem() + "-" + monthStartReports.getSelectedItem() + "-" + dayStartReports.getSelectedItem());
        System.out.println(yearEndReports.getSelectedItem() + "-" + monthEndReports.getSelectedItem() + "-" + dayEndReports.getSelectedItem());
        Date start = Date.valueOf(yearStartReports.getSelectedItem() + "-" + monthStartReports.getSelectedItem() + "-" + dayStartReports.getSelectedItem());
        Date end = Date.valueOf(yearEndReports.getSelectedItem() + "-" + monthEndReports.getSelectedItem() + "-" + dayEndReports.getSelectedItem());
        System.out.println("start" + start);
        System.out.println("end" + end);
        Collection<Cost> costs = new LinkedList<>();
        viewModel.getReport(account, start, end);
    }

    public void showReportsByDate(LinkedList<Cost> costs) {

        String columns[] = { "Username", "Categories", "Description","Cost","Currency","Date"};
        String data [][] = new String[costs.size()][6];
        double sumOfCosts = 0;
        int i = 0;

        for (Cost c: costs) {
            System.out.println(c.getUsernames() + " " + c.getCategories() + " " + c.getCostAmount() + " " + c.getDate());
            String username = c.getUsernames();
            String categories = c.getCategories();
            String description = c.getDescription();
            double cost = c.getCostAmount();
            String currency = c.getCurrency();
            Date date = c.getDate();
            sumOfCosts += cost;

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

    public static void main(String[] args) {

    }

}
