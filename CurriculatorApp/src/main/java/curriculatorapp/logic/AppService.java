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
    private CoursesDao coursesdao;

    public AppService(User loggedUser, CurriculumDao curriculumdao, CoursesDao coursesdao) throws SQLException {
        this.curriculumDao = curriculumdao;
        this.loggedUser = loggedUser;
        this.coursesdao = coursesdao;

    }

    public String getLoggedName() {
        return loggedUser.getName();
    }
}
