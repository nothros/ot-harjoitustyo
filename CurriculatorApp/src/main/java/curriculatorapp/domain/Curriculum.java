/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.domain;

/**
 *
 * @author ehorrosw
 */
public class Curriculum {

    private String curriculumName, choice;
    private int scope;
    private final User user;

    public Curriculum(String curriculumName, String choice, int scope, User user) {
        this.curriculumName = curriculumName;
        this.choice = choice;
        this.scope = scope;
        this.user = user;
        System.out.println(getCurriculumName());
    }

    Curriculum(Curriculum curriculum) {
        this.curriculumName = curriculum.getCurriculumName();
        this.choice = curriculum.getChoice();
        this.scope = curriculum.getScope();
        this.user = curriculum.getUser();
    }

    public User getUser() {
        return this.user;
    }

    public String getCurriculumName() {
        return this.curriculumName;
    }

    public String getChoice() {
        return this.choice;
    }

    public int getScope() {
        return this.scope;
    }
}
