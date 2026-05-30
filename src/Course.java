public class Course {
    /*
    The Course class represents one college course.
    It has a String subject (ie: MATH or ENG or CSC) and an int number
        that combine to make the CRN (ie: MATH100, CSC115).
    It also has a Group that represents its prerequisites.

    The Course class is immutable.
     */

    private final String subject;
    private final int number;
    private final Group prereq;

    //Default constructor.
    public Course(String subject, int number, Group prereq) {
        this.subject = subject;
        this.number = number;
        this.prereq = prereq;
    }

    //No prereq constructor.
    public Course(String subject, int number) {
        this.subject = subject;
        this.number = number;
        this.prereq = new Group(new Course[] {}, 0);
    }

    //Clone constructor.
    public Course(Course toCopy) {
        this.subject = toCopy.getSubject();
        this.number = toCopy.getNumber();
        this.prereq = toCopy.getPrereq();
    }



    //toString. Returns the CRN (subject + number).
    public String toString() {
        return subject + number;
    }

    //Getter Methods.
    public String getSubject() {
        return subject;
    }

    public int getNumber() {
        return number;
    }

    public Group getPrereq() {
        return prereq;
    }
}
