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
        runCostManagerApp();
    }

    private static void runCostManagerApp() throws CostManagerExceptions {
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
    }
}
