package MVVM.Model;


/**
 * TEXT HERE...
 */


public interface IView {

    public void start(); // Set all panels,frames and actions.
    public void setIViewModel(IViewModel viewModel);
    public void showUsernameIsTaken();
    public void LoginSuccessfully(Account account);
    public void startAddNewCost(Account account);
    public void LogOutFromAccount();
    public void AddNewCostSuccessfully(Cost cost, Account account);
}
