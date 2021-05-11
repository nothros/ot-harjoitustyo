package curriculatorapp.dao;

import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Luokka Curriculums-tietokantapöydän toiminnalle.
 */
public class CurriculumDao {

    private final Connection conn;

    public CurriculumDao(String url) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);

    }

    public void createNewTable() throws SQLException {
        try ( PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Curriculums "
                + "(curriculum_id INTEGER PRIMARY KEY, "
                + "user_id   INTEGER, "
                + "curriculum_name    VARCHAR(255),  "
                + "studymeter   INTEGER, "
                + "studychoice   VARCHAR(255), "
                + " FOREIGN KEY (user_id) REFERENCES users (user_id))")) {
            stmt.executeUpdate();

        }
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }

    /**
     * Metodi lisää uuden opinnon tietokantatauluun Curriculums.
     *
     * @param loggedUsername Kirjautuneena olevan käyttäjän käyttätunnus
     * @param curriculumName Käyttäjän määrittelemä nimi opinnoilleen
     * @param scope Opintojen laajuus
     * @param choice Opintojen laajuutta kuvaaava termi
     */
    @SuppressWarnings("empty-statement")
    public void createCurriculum(User loggedUser, String curriculumName, int scope, String choice) throws SQLException {
        System.out.println("Lisätään ");
            try ( PreparedStatement stmt = conn.prepareStatement("INSERT INTO Curriculums (user_id,curriculum_name,studymeter,studychoice) VALUES (?,?,?,?)")){
            stmt.setInt(1, loggedUser.getId());
            stmt.setString(2, curriculumName);
            stmt.setInt(3, scope);
            stmt.setString(4, choice);
            stmt.executeUpdate();
            
        }
    }

    /**
     * Metodi etsii, onko käyttäjällä opintoja.
     *
     * @param loggedUsername Kirjautuneen käyttäjän käyttäjätunnus
     * @return Palauttaa true mikäli käyttäjällä on opintoja
     */
    public Curriculum findCurriculum(User loggedUser) throws SQLException {
        Curriculum curriculum = null;

                try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Curriculums WHERE user_id=?")){
                stmt.setInt(1, loggedUser.getId());
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    return null;
                }
                curriculum = new Curriculum(rs.getInt("curriculum_id"), rs.getString("curriculum_name"), rs.getString("studychoice"), rs.getInt("studymeter"), loggedUser);

            }

        

        return curriculum;
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