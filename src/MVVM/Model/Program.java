package MVVM.Model;

import MVVM.Model.Model.CostManagerDBModel;
import MVVM.Model.View.CostManagerView;
import MVVM.Model.ViewModel.CostManagerViewModel;

import javax.swing.*;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;

/**
 * TEXT HERE...
 */


public class Program {

    public static void main(String[] args) throws CostManagerExceptions {

        IModel model = new CostManagerDBModel();
        IViewModel vm = new CostManagerViewModel();
        IView view = new CostManagerView();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //view.init();
                view.start();
            }
        });
        vm.setModel(model);
        vm.setView(view);
        view.setIViewModel(vm);

////        Date start = new Date(119, 1, 1);
////        Date end = new Date(123, 1, 1);
////        Account account = new Account("matan", "1234", "matab", "bar", "eee@eee");
////
////        CostManagerDBModel costManagerDBModel =new CostManagerDBModel();
////        System.out.println(start.toString());
////
////
////        Collection<Cost> costs = new LinkedList<>();
////
////        costs = costManagerDBModel.getReport(account, start, end);
////
////        for (Cost c: costs) {
////
////            System.out.println(c.getUsernames() + ", " + c.getCategories() + " , " + c.getCostAmount());
//
//        }

    }
}
