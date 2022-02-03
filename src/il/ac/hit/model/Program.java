package il.ac.hit.model;

import il.ac.hit.IModel;
import il.ac.hit.IView;
import il.ac.hit.IViewModel;
import il.ac.hit.view.CostManagerView;
import il.ac.hit.viewmodel.CostManagerViewModel;

import javax.swing.*;

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
