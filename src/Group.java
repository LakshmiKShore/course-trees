public class Group {
    /*
    The Group class represents one "group" of prerequisites.
    For example, a Group could represent "Take all of the following: MATH100, MATH112, MATH122"
    It could also represent "Take one of the following: MATH110, MATH211"
    It can even represent something more complicated, like:
        - Complete all of the following:
            - One of:
                - MATH110
                - MATH211
            - One of:
                - All of:
                    - MATH200
                    - MATH201
                - All of:
                    - MATH204

    A group can hold an array of groups OR an array of courses.
    A group holding an array of courses is a terminal group (represented by the boolean isTerminal.)
    It also holds an int number representing how many of the groups need to be completed. (One of, Two of, All of).

    Groups are immutable.
     */

    private final Group[] groups;
    private final Course[] courses;
    private final int amount;
    private final boolean isTerminal;

    //Constructs a non-terminal group.
    public Group(Group[] groups, int amount) {
        this.groups = groups;
        this.courses = new Course[] {};
        this.amount = amount;
        this.isTerminal = false;
    }

    //Constructs a terminal group.
    public Group(Course[] courses, int amount) {
        this.groups = new Group[] {};
        this.courses = courses;
        this.amount = amount;
        this.isTerminal = true;
    }

    //Clone constructor.
    public Group(Group toCopy) {
        this.groups = toCopy.getGroups();
        this.courses = toCopy.getCourses();
        this.amount = toCopy.getAmount();
        this.isTerminal = toCopy.isTerminal();
    }
    

    //toString.
    public String toString() {
        return this.toString(0, 0);
    }

    //toString helper method that allows recursive indenting and asks how deep it should show prerequisite courses.
    public String toString(int indent, int depth) {
        String toReturn = "";
        String strIndent = "";
        for (int i = 0; i < indent; i++) {strIndent += " ";}

        if (amount == courses.length || amount == groups.length) {
            toReturn += (strIndent + "All of: \n");
        } else {
            toReturn += (strIndent + amount + " of: \n");
        }

        if (isTerminal) {
            for (Course c : courses) {

                if ((c.getPrereq().isTerminal && c.getPrereq().getCourses().length == 0) || depth == 0) {
                    toReturn += (strIndent + " - " + c + "\n");
                } else {
                    toReturn += (strIndent + " - " + c + "\n");
                    toReturn += c.getPrereq().toString(indent + 5, depth - 1);
                }

            }
        } else {
            for (Group g : groups) {
                toReturn += g.toString(indent + 3, depth);
            }
        }

        return toReturn;
    }

    //Getter methods.
    public Group[] getGroups() {
        return groups;
    }

    public Course[] getCourses() {
        return courses;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public boolean isEmpty() {return (isTerminal && courses.length == 0);}
}
