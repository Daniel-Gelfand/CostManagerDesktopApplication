package MVVM.Model;

import java.sql.SQLException;

public class CostManagerExceptions extends SQLException {

    public CostManagerExceptions(String message)
    {
        super(message);
    }
    public CostManagerExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
