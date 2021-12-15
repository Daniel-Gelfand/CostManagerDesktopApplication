package MVVM.Model.ViewModel;

import MVVM.Model.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
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
                }
                catch (CostManagerExceptions e) {
                    view.showUsernameIsTaken();
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

    // This method not in use yet.
    @Override
    public void addCategory(Category category) {

    }

    // This method not in use yet.
    @Override
    public void getCategories() {

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

                }
                catch (CostManagerExceptions e) {
                    System.out.println("Cant login to account");
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
                }
                catch (CostManagerExceptions e) {

                }
            }
        });

    }

    // This method turn to the model and logout and turn the user to the main page.
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

    // This method switch between screens in the view. From Main Page --> Add New Cost Page.
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

    // This method switch between screens in the view. From Main Page --> Reports Page.
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

    // This method switch between screens in the view. From Signup Page --> Login Page.
    @Override
    public void finishSignUp() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.closeSignUpFrame();
                        }
                    });
                }
                catch (Exception e) {

                }
            }
        });
    }

    // This method switch between screens in the view. From Login Page --> Signup Page
    @Override
    public void openSignUpPage() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.openSignUpFrame();
                        }
                    });
                }
                catch (Exception e) {

                }
            }
        });
    }
}
