package MVVM.Model;


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
    private String username;
    private String password;



    /**
     * 'Account' is a constructor.
     * In this constructor we set username and password into Account.
     * @param username username
     * @param password password
     */
    public Account(String username, String password) {
        setPassword(password);
        setUsername(username);
    }



    /**
     * 'getUsername' is a method that gets the username.
     * @return username
     */
    public String getUsername() {
        return username;
    }



    /**
     * 'setUsername' is a method that sets the username.
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
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
