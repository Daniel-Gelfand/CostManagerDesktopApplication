package il.ac.hit;


/**
 * 'Account' :
 *
 * This Class encapsulate all details about account.
 *
 * Methods:
 *  (1) Account(String username, String password),
 *  (2) getUsername(),
 *  (3) setUsername(String username),
 *  (4) getPassword(),
 *  (5) setPassword(String password).
 */
public class Account {

    //These members are appropriate to account.
    private String userName;
    private String password;



    /**
     * 'Account' is a constructor.
     * In this constructor we set username and password into Account.
     * @param userName username
     * @param password password
     */
    public Account(String userName, String password) {
        setPassword(password);
        setUserName(userName);
    }



    /**
     * 'getUsername' is a method that gets the username.
     * @return username
     */
    public String getUserName() {
        return userName;
    }



    /**
     * 'setUsername' is a method that sets the username.
     * @param userName username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }



    /**
     * 'getPassword' is a method thats gets the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }



    /**
     * 'setPassword' is a method that sets the password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }






}
