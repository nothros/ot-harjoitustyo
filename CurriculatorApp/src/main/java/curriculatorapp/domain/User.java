

package curriculatorapp.domain;

import java.util.*;
import org.mindrot.jbcrypt.BCrypt;



public class User {

    private String name, username, password;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = encryptPassword(password);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
   public static String encryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    public Boolean checkPassword(String passwordDB){
        return BCrypt.checkpw(this.password, passwordDB);
    }

}