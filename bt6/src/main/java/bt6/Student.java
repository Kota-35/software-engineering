package bt6;

import java.util.Objects;

public class Student extends Person {
    private Teacher tutor;

    public Student(String id, String myoji, String namae) {
        super(id, myoji, namae);
    }

    public void setTutor(Teacher teacher) {
        this.tutor = teacher;
        teacher.addStudent(this);
    }

    public void showStatus() {
        String status = "Student " + getProfile() + " :: ";
        if (Objects.nonNull(tutor)) {
            status += tutor.getId();
        }
        System.out.println(status);
    }
}
