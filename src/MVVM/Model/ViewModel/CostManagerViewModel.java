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
    // the class members of the view model.
    private IView view;
    private IModel model;
    private final ExecutorService service;

    /**
     * This method adding 5 threads to the pool
     */
    public CostManagerViewModel() {
        this.service = Executors.newFixedThreadPool(5);
    }

    /**
     * This method is a set property of the view
     * @param view view
     */
    @Override
    public void setView(IView view) {
        this.view = view;
    }

    /**
     * This method is a set property of the model
     * @param model
     */
    @Override
    public void setModel(IModel model) {
        this.model = model;
    }


    /**
     * This method start setup new account (turn to the model, that register new account).
     * @param account username
     */
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
                    // If catch exception than pop up error message.
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }


    /**
     * This method login to account (turn to the model and check if user exists, and doing actions accordingly).
     * @param account username
     */
    @Override
    public void loginToAccount(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // Check if user is exists in the system and beck true or false
                    if (model.loginToAccount(account)) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            // if true continue the flow for the account
                            public void run() {
                                view.loginSuccessfully(account);
                            }
                        });
                    } else {
                        SwingUtilities.invokeLater(new Runnable() {
                            // if false shoe the user that he type wrong details
                            @Override
                            public void run() {
                                view.userDoesNotExist("User doesn't exist!");
                            }
                        });
                    }
                } catch (CostManagerExceptions e) {
                    // If catch exception than pop up error message.
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * This method send request to the model to add new cost to database.
     * @param account username
     */
    @Override
    public void addNewCost(Cost cost, Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //torn to the model to add new cost to specific user.
                    model.addNewCost(cost);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.addNewCostSuccessfully(cost, account);
                        }
                    });

                } catch (CostManagerExceptions e) {
                    // If catch exception than pop up error message.
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }

    /**
     * This method send request to the report about specific user account by specific dates.
     * @param account username
     */
    @Override
    public void getReport(Account account, Date start, Date end) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // create new linked list that contains costs report by specific user.
                    LinkedList<Cost> resultSet = model.getReport(account, start, end);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showReports(resultSet);
                        }
                    });
                } catch (CostManagerExceptions e) {
                    // If catch exception than pop up error message.
                    view.showErrorMessage(e.getMessage());
                }
            }
        });

    }

    /**
     * This method send request to the model to add new category to database.
     * @param account username
     */
    @Override
    public void addNewCategory(Category category, Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //torn to the model to add new category to specific user.
                    model.addCategory(category, account);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        //turn to the view for continue with the app flow.
                        public void run() {
                            view.closeAddNewCategory();
                        }
                    });
                } catch (CostManagerExceptions e) {
                    // If catch exception than pop up error message.
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }


    /**
     * This method send request to the model to get linked list of the categories by specific user account.
     * @param account username
     */
    @Override
    public void addNewCost(Account account) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    // create new linked list that contains all categories by specific user.
                    LinkedList<Category> resultSet = model.getCategories(account);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            //turn to the view for continue with the app flow.
                            view.startAddNewCost(account, resultSet);
                        }
                    });
                } catch (CostManagerExceptions e) {
                    // If catch exception than pop up error message.
                    view.showErrorMessage(e.getMessage());
                }
            }
        });
    }
}
