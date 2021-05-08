package curriculatorapp.domain;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Käyttäjää kuvaava luokka
 */
public class User {

    private String name, username, password;
    private Curriculum curriculum;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;

    }

    public User(String name, String username) {
        this.name = name;
        this.username = username;

    }

    public void setCurriculum(Curriculum curriculum) {

        this.curriculum = new Curriculum(curriculum);
        System.out.print(curriculum.getChoice());

    }

    public Curriculum getCurriculum() {
        return this.curriculum;
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

    /**
     * Metodi salasanan salaamiseen
     *
     * @param password Käyttäjän ehdottama salasana
     * @return Salattu salasana
     *
     */
    public String encryptPassword(String password) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return bcryptHashString;
    }

    /**
     * Metodi salasanan tarkistamiseen.
     *
     * @param triedPassword Käyttäjän kokeilema salasana
     * @param passwordDB Tietokannassa oleva salasana
     * @return Palauttaa true; mikäli salasana tsämää.
     *
     */
    public Boolean checkPassword(String triedPassword, String passwordDB) {
        BCrypt.Result result = BCrypt.verifyer().verify(triedPassword.toCharArray(), passwordDB);
        return result.verified;
    }

}
