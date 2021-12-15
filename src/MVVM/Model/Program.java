package MVVM.Model;

import MVVM.Model.Model.CostManagerDBModel;
import MVVM.Model.View.CostManagerView;
import MVVM.Model.ViewModel.CostManagerViewModel;

import javax.swing.*;
import java.sql.Date;

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

//        Cost cost1 = new Cost("maim", "Asos", "new item", 123.25, "Euro", new Date(121, 10, 2));
//        Cost cost2 = new Cost("maim", "Asos", "new item", 123.25, "Euro", new Date(121, 10, 2));
//
//        CostManagerDBModel costManagerDBModel = new CostManagerDBModel();
//        costManagerDBModel.addNewCost(cost1);
//        costManagerDBModel.addNewCost(cost2);


    }
}
