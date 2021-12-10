package MVVM.Model;

public class Program {

    public static void main(String[] args) {
        CostManagerDBModel costManagerDBModel = new CostManagerDBModel();
        Account account = new Account("Danoy","123456","Daniel","Gel",
                "danoyoy@gmail.com");
        costManagerDBModel.setupNewAccount(account);
    }
}
