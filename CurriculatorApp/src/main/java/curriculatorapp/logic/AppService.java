/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.logic;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.domain.User;
import java.sql.SQLException;

/**
 *
 * @author ehorrosw
 */
public class AppService implements Service {

    private CurriculumDao curriculumDao;
    private User loggedUser;
    private Curriculum curriculum;
    private CoursesDao coursesDao;

    public AppService(User loggedUser, CurriculumDao curriculumdao, CoursesDao coursesdao) throws SQLException {
        this.curriculumDao = curriculumdao;
        this.loggedUser = loggedUser;
        this.coursesDao = coursesdao;
    }

    public User getLoggedUser() {
        return loggedUser;
    }


    public void createCurriculum(String curriculumName, int scope, String choice) throws SQLException {
        curriculumDao.createCurriculum(loggedUser.getName(), curriculumName, scope, choice);
    }

    public boolean checkIfCurriculumExist() throws SQLException {
        curriculum=(Curriculum)findCurriculum();
        return curriculum != null;
    }
    
    public Curriculum findCurriculum() throws SQLException{
        curriculum = (Curriculum) curriculumDao.findCurriculum(loggedUser);
        return curriculum;
    }
}
