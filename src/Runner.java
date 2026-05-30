import java.io.IOException;

public class Runner {

    public static void main(String[] args) {
        Course calcI = new Course("MATH", 100);
        Course calcII = new Course("MATH", 101, new Group(new Course[] {calcI}, 1));
        Course calcIII = new Course("MATH", 200, new Group(new Course[] {calcII}, 1));
        Course calcIV = new Course("MATH", 204, new Group(new Course[] {calcIII}, 1));
        Course logic = new Course("MATH", 122);
        Course realIntro = new Course("MATH", 236, new Group(new Course[] {calcII, logic}, 2));

        Course complexVar = new Course("MATH", 301, new Group(
                new Group[] {
                        new Group(new Course[] {calcIII}, 1),
                        new Group(new Course[] {calcIV, realIntro}, 1)
                            }, 2));

        complexVar.printPrereq(3);
    }

}
