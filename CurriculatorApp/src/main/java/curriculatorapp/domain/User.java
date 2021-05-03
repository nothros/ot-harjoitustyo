package curriculatorapp.domain;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class User {

    private String name, username, password;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        
    }

    public User(String name, String username) {
        this.name = name;
        this.username = username;
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = encryptPassword(password);
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

    public String encryptPassword(String password) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return bcryptHashString;
    }

    public Boolean checkPassword(String triedPassword, String passwordDB) {
        BCrypt.Result result = BCrypt.verifyer().verify(triedPassword.toCharArray(), passwordDB);
        return result.verified;
    }

}
