package MVVM.Model.ViewModel;

import MVVM.Model.*;

import javax.swing.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This Class implements IViewModel, responsible for transitions between screens and connecting between
 * the view to the model.
 *
 *
 */

public class CostManagerViewModel implements IViewModel {

    private IView view;
    private IModel model;
    private ExecutorService service;

    public CostManagerViewModel() {
        this.service = Executors.newFixedThreadPool(6);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }


    // This method start setup new account (turn to the model, that register new account).
    @Override
    public void setupNewAccount(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.setupNewAccount(account);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.closeSignUpFrame();
                        }
                    });
                } catch (CostManagerExceptions e) {
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }

    // This method login to account (turn to the model and check if user exists, and doing actions accordingly).
    @Override
    public void loginToAccount(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if (model.loginToAccount(account)) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                view.LoginSuccessfully(account);
                            }
                        });
                    } else {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                view.UserDoesNotExist("User doesn't exist!");
                            }
                        });
                    }
                } catch (CostManagerExceptions e) {
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }

    // This method turn to the  model and adding new cost the appropriate database.
    @Override
    public void addNewCost(Cost cost, Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addNewCost(cost);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.AddNewCostSuccessfully(cost, account);
                        }
                    });

                } catch (CostManagerExceptions e) {
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }

    // This method turns to the model to get list of costs for specific user.
    @Override
    public void getReport(Account account, Date start, Date end) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    LinkedList<Cost> resultSet = model.getReport(account, start, end);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showReports(resultSet);
                        }
                    });
                } catch (CostManagerExceptions e) {
                    view.showErrorMessage(e.getMessage());
                }
            }
        });

    }

    @Override
    public void addNewCategory(Category category, Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCategory(category, account);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.CloseAddNewCategory();
                        }
                    });
                } catch (CostManagerExceptions e) {
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }


    @Override
    public void addNewCost(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    LinkedList<Category> resultSet = model.getCategories(account);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.startAddNewCost(account, resultSet);
                        }
                    });
                } catch (CostManagerExceptions e) {
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }
}
