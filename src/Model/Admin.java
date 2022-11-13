package Model;

import java.io.Serializable;

/**
 * The admin account with access to the admin module
 * An admin can change movie records and configure system settings
 */
public class Admin implements Serializable {
    private String username;
    private String password;

    /**
     * Creates an Admin with the given attributes
     * @param username						This Admin username
     * @param password						This Admin password
     */
    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Changes the username of this Admin
     * @param username This Student's new name.
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Gets the username of this Admin.
     * @return this Admin username.
     */
    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Gets the password of this Admin.
     * @return this Admin password.
     */
    public String getPassword(){
        return password;
    }
}
