package MVVM.Model.View;

import MVVM.Model.Account;
import MVVM.Model.IViewModel;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportsFrame {
    private Account account;
    private IViewModel viewModel;

    private JFrame reportsFrame;
    private JPanel reportsPanel;
    private String [] daysStart = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String [] monthsStart = {"0","1","2","3","4","5","6","7","8","9","10","11","12"};
    private String [] yearsStart = {"2019","2020","2021","2022"};
    private JComboBox dayStartReports;
    private JComboBox monthStartReports;
    private JComboBox yearStartReports;
    private String [] daysEnd = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String [] monthsEnd = {"0","1","2","3","4","5","6","7","8","9","10","11","12"};
    private String [] yearsEnd = {"2019","2020","2021","2022"};
    private JComboBox dayEndReports;
    private JComboBox monthEndReports;
    private JComboBox yearEndReports;
    private JLabel labelFromDateReports;
    private JLabel labelToDateReports;
    private JButton buttonSendToGetReport; ////////////////////////
    private JTable tableReports;
    private JPanel reportsPanelNorth;
    private JPanel reportsPanelSouth;
    private JLabel labelSumReports;
    private JTextField textFieldSumReports;





    public ReportsFrame() {
        reportsFrame = new JFrame();
        reportsFrame.setTitle("Reports Form");

        dayStartReports = new JComboBox(daysStart);
        monthStartReports = new JComboBox(monthsStart);
        yearStartReports = new JComboBox(yearsStart);

        dayEndReports = new JComboBox(daysEnd);
        monthEndReports = new JComboBox(monthsEnd);
        yearEndReports = new JComboBox(yearsEnd);
        labelFromDateReports = new JLabel("From Date: ");
        labelToDateReports = new JLabel("To Date: ");


        buttonSendToGetReport = new JButton("Send To Get Report");
        buttonSendToGetReport.addActionListener(e -> setReportsButton());

        labelSumReports = new JLabel("Sum");
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
        reportsPanel.setBackground(Color.WHITE);
        reportsPanelNorth.setBackground(Color.WHITE);
        reportsPanelSouth.setBackground(Color.WHITE);
        reportsFrame.add(reportsPanel,BorderLayout.CENTER);
        reportsFrame.add(reportsPanelNorth,BorderLayout.NORTH);
        reportsFrame.add(reportsPanelSouth,BorderLayout.SOUTH);
        reportsFrame.setSize(500, 500);
        reportsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportsFrame.setLocationRelativeTo(null);
        reportsFrame.setVisible(true);


    }

    public void setReportsButton()
    {

    }

    public static void main(String[] args) {

        ReportsFrame reportsFrame = new ReportsFrame();

    }

}
