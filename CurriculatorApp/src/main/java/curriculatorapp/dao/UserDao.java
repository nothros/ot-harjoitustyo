package curriculatorapp.dao;

import curriculatorapp.domain.User;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Luokka Users-tietokantapöydän toiminnalle.
 */
public class UserDao {

    private final Connection conn;

    public UserDao(String url) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);

    }

    /**
     * Metodi lisää uuden käyttäjän tietokantatauluun Users.
     *
     * @param name käyttäjän nimi
     * @param username käyttäjätunnus
     * @param password salattu salasana
     */
    public void createUser(String name, String username, String password) throws SQLException {
        System.out.println("Lisätään ");
        try ( PreparedStatement p = conn.prepareStatement("INSERT INTO Users(name,username,password) VALUES (?,?,?)")) {
            p.setString(1, name);
            p.setString(2, username);
            p.setString(3, password);
            p.executeUpdate();
            System.out.println("Käyttäjä lisätty");
        }
    }

    /**
     * Metodi etsii käyttäjän tietokantataulusta Users.
     *
     * @param username käyttäjätunnus jolla etsitään, onko käyttäjätunnus jo
     * olemassa
     * @return palauttaa käyttäjän oliona, mikäli tämä löytyy.
     */
    public User findByUsername(String username) throws SQLException {
        ResultSet rs;
        User user;
        try ( PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users "
                + "WHERE username = ?")) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            System.out.print(rs.getString("password"));
            user = new User(rs.getString("name"), rs.getString("username"), rs.getString("password"));
        }
        rs.close();

        return user;
    }

    /**
     * Metodi lisää Users- tietokantataulun, mikäli sitä ei ole olemassa.
     */
    public void createNewUserTable() throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Users "
                + "(user_id INTEGER PRIMARY KEY, "
                + "name    VARCHAR(255), "
                + "username    VARCHAR(255),  "
                + "password   VARCHAR(255))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }

    /**
     * Metodi poistaa koko tietokannan.Tarkoitettu testien käyttöön
     *
     * @param database poistettavan tietokannan nimi
     */
    public void deleteDatabase(String database) throws SQLException {
        conn.close();
        File deletedDB = new File(database + ".db");
        deletedDB.delete();

    }

}
