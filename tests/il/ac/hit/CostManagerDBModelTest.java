package il.ac.hit;

import il.ac.hit.model.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

class CostManagerDBModelTest {
    CostManagerDBModel costManagerDBModel;
    Account account;
    Cost cost;
    Category category;
    Date date = new Date(121,11,10);
    private List<Category> categories = new LinkedList<>();

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws CostManagerExceptions {
        account = new Account("unittest","123456");
        costManagerDBModel = new CostManagerDBModel();
        cost = new Cost("unittest","TEST","juniTESTv2AfterCMExceptions",999.99,"TEST",date);
        category = new Category("TEST1");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        account = null;
        costManagerDBModel = null;
        cost = null;
        category = null;
    }

    @org.junit.jupiter.api.Test
    void setupNewAccount() throws CostManagerExceptions {
        costManagerDBModel.setupNewAccount(account);
    }

    @org.junit.jupiter.api.Test
    void loginToAccount() throws CostManagerExceptions {
        costManagerDBModel.loginToAccount(account);
    }

    @org.junit.jupiter.api.Test
    void addCategory() throws CostManagerExceptions {
        costManagerDBModel.addCategory(category, account);
    }

    @org.junit.jupiter.api.Test
    void addNewCost() throws CostManagerExceptions {
        costManagerDBModel.addNewCost(cost);
    }
}