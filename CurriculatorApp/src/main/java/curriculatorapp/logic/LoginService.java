package curriculatorapp.logic;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.User;
import java.sql.SQLException;

public class LoginService implements Service {

    private UserDao userdao;
    private CurriculumDao curriculumdao;
    private CoursesDao coursesdao;
    private User loggedUser;

    public LoginService(UserDao userdao, CurriculumDao curriculumdao, CoursesDao coursesdao) {
        this.userdao = userdao;
        this.curriculumdao = curriculumdao;
        this.coursesdao = coursesdao;
    }

    public boolean login(String username, String password) throws SQLException {
        loggedUser = (User) userdao.findByUsername(username);
        if (loggedUser == null) {
            return false;
        }

        return loggedUser.checkPassword(password, loggedUser.getPassword());
    }

    public User getLoggedUser() {
        return loggedUser;

    }

    public CurriculumDao getCurriculumDao() {
        return curriculumdao;
    }

    public CoursesDao getCoursesDao() {
        return coursesdao;
    }

    public boolean createNewUser(String name, String username, String password) throws SQLException {
        User user = (User) userdao.findByUsername(username);
        if (user == null) {
            User newUser = new User(name, username);
            newUser.setPassword(password);
            String cryptedPassword = newUser.getPassword();
            userdao.createUser(name, username, cryptedPassword);
            return true;
        }
        return false;

    }

    public void createNewTablesIfNotExists() throws SQLException {
        userdao.createNewUserTable();
        curriculumdao.createNewTable();
        coursesdao.createNewTable();
    }

}
