package il.ac.hit;

import java.sql.SQLException;

/**
 * 'CostManagerExceptions':
 *
 * This class throws CostManger specific exceptions.
 * CostManagerExceptions holds message.
 */
public class CostManagerExceptions extends SQLException {

    public CostManagerExceptions(String message)
    {
        super(message);
    }
    public CostManagerExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
