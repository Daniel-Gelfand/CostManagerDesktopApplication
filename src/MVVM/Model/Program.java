package MVVM.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {

    public static void main(String[] args) {


        Date date = new Date(121,11,10);
        System.out.println(date.toString());
//        System.out.println(now);
//
//        System.out.println(date);

        CostManagerDBModel costManagerDBModel = new CostManagerDBModel();

        //Account account = new Account("Danoy","123456","Daniel","Gel",
          //      "danoyoy@gmail.com");

        //costManagerDBModel.setupNewAccount(account);
        //System.out.println(costManagerDBModel.loginToAccount(account));

        Cost cost = new Cost("Danoy","Protein","Snacks?",300.33,"Euro",date);
        costManagerDBModel.addNewCost(cost);

    }
}
