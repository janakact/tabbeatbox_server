package org.tapbeatbox.server.resources;

/**
 * This resource will be used when sending a matched pair to the front end.
 * @author Sajith Dilshan
 */
public class PairResource {

    private int pairId;
    private StudentResource studentOne;
    private StudentResource studentTwo;

    public StudentResource getStudentOne() {
        return studentOne;
    }

    public void setStudentOne(StudentResource studentOne) {
        this.studentOne = studentOne;
    }

    public StudentResource getStudentTwo() {
        return studentTwo;
    }

    public void setStudentTwo(StudentResource studentTwo) {
        this.studentTwo = studentTwo;
    }

    public int getPairId() {
        return pairId;
    }

    public void setPairId(int pairId) {
        this.pairId = pairId;
    }
}
