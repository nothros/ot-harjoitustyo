/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.logic;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.domain.User;
import java.sql.SQLException;

/**
 *
 * @author ehorrosw
 */
public class AppService implements Service {

    private CurriculumDao curriculumDao;
    private User loggedUser;
    private CoursesDao coursesDao;

    public AppService(User loggedUser, CurriculumDao curriculumdao, CoursesDao coursesdao) throws SQLException {
        this.curriculumDao = curriculumdao;
        this.loggedUser = loggedUser;
        this.coursesDao = coursesdao;
    }

    public String getLoggedName() {
        return loggedUser.getName();
    }

    public String getLoggedUsername() {
        return loggedUser.getUsername();
    }

    public void createCurriculum(String curriculumName, String scope, String choice) throws SQLException {
        curriculumDao.createCurriculum(getLoggedUsername(), curriculumName, scope, choice);
    }

    public boolean checkIfCurriculumExist(String username) throws SQLException {
        return curriculumDao.findCurriculum(username);
    }
}
