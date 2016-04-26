package org.tapbeatbox.server.resources;

/**
 * This Resource will be used in controllers when reading a match request sent by the front end.
 *
 * @author Chathura Widanage
 */
public class MatchResource {
    /**
     * The primary key of the first student
     */
    private Integer studentOne;
    /**
     * The primary key of the second student
     */
    private Integer studentTwo;

    public Integer getStudentOne() {
        return studentOne;
    }

    public void setStudentOne(Integer studentOne) {
        this.studentOne = studentOne;
    }

    public Integer getStudentTwo() {
        return studentTwo;
    }

    public void setStudentTwo(Integer studentTwo) {
        this.studentTwo = studentTwo;
    }

}
