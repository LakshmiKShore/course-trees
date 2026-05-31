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


    //Returns an expanded group found by turning all terminal groups with at least one course with prerequisites into non-terminal groups
    //Containing the groups of the prerequisites from all the courses in the terminal groups
    public Group expand() {

        //Base case. Returns itself, since it is already expanded.
        if (isExpanded()) {
            return this;
        }

        //First recursive case, for a Terminal but not Expanded group.
        //Creates a group containing all its courses' prereqs, then runs this method on it, sending it to the second recursive case.
        if (isTerminal) {
            Group[] expanded = new Group[courses.length];

            for (int i = 0; i < courses.length; i++) {
                expanded[i] = courses[i].getPrereq();
            }

            return new Group(expanded, amount).expand();
        }

        //Second recursive case, for a non-terminal group. Recurses through all the subgroups in the group.
        //It then sends the new group through this method, which may or may not be unneccessary. I don't want to find out rn so i wont
        //okay that might have led to an infinite loop. i removed it. i hope that fixes it.
        Group[] expanded = new Group[groups.length];

        for (int i = 0; i < groups.length; i++) {
            expanded[i] = groups[i].expand();
        }

        return new Group(expanded, amount).expand();
    }


    //Returns TRUE if the course is terminal and none of its courses have prerequisites.
    //Recursively searches through and does cool things
    public boolean isExpanded() {
        if (!isTerminal) {
            boolean subgroupsAreExpanded = true;

            for (int i = 0; i < groups.length; i++) {
                if (!groups[i].isExpanded()) {subgroupsAreExpanded = false;}
            }

            return subgroupsAreExpanded;
        }

        boolean coursesHavePrereqs = false;

        for (Course c : courses) {
            if (c.hasPrereqs()) {coursesHavePrereqs = true;}
        }

        return coursesHavePrereqs;
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

    public boolean isStrict() {return isTerminal && (courses.length == amount || groups.length == amount);}

    public boolean isSwitch() {return amount == 1;}
}
