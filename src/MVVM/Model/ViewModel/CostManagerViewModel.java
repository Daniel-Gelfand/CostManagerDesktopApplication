package MVVM.Model.ViewModel;

import MVVM.Model.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CostManagerViewModel implements IViewModel {

    private IView view;
    private IModel model;
    private ExecutorService service;

    public CostManagerViewModel() {
        this.service = Executors.newFixedThreadPool(5);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void setupNewAccount(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.setupNewAccount(account);
                }
                catch (CostManagerExceptions e) {
                    view.showUsernameIsTaken();
                }
            }
        });
    }

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
                    }
                    else {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                view.UserDoesNotExist();
                            }
                        });
                    }
                }
                catch (CostManagerExceptions e) {

                }
            }
        });
    }

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void getCategories() {

    }

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

                }
                catch (CostManagerExceptions e) {
                    System.out.println("Cant login to account");
                }
            }
        });
    }

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
                }
                catch (CostManagerExceptions e) {

                }
            }
        });

    }

    @Override
    public void logout() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.logout();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.LogOutFromAccount();
                        }
                    });

                }catch (CostManagerExceptions e) {

                }
            }
        });
    }

    @Override
    public void startNewCost(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.startAddNewCost(account);
                        }
                    });
                }catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void goToReports(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.startReports(account);
                        }
                    });
                }
                catch (Exception e) {

                }
            }
        });
    }
}
