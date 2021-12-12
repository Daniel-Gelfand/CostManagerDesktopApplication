package MVVM.Model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CostManagerDBModelTest {
    CostManagerDBModel costManagerDBModel;
    Account account;
    Cost cost;
    Category category;
    Date date = new Date(121,11,10);
    private List<Category> categories = new LinkedList<>();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        account = new Account("juniTESTv2","123456","Junit","test","junit@test.com");
        costManagerDBModel = new CostManagerDBModel();
        cost = new Cost("juniTESTv2","TEST","TEST TEST TEST?",999.99,"TEST",date);
        category = new Category("TEST");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        account = null;
        costManagerDBModel = null;
        cost = null;
        category = null;
    }

    @org.junit.jupiter.api.Test
    void setupNewAccount() {
        costManagerDBModel.setupNewAccount(account);
    }

    @org.junit.jupiter.api.Test
    void loginToAccount() {
        costManagerDBModel.loginToAccount(account);
    }

    @org.junit.jupiter.api.Test
    void addCategory() {
        categories.add(category);
    }

    @org.junit.jupiter.api.Test
    void addNewCost() {
        costManagerDBModel.addNewCost(cost);
    }
}