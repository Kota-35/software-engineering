package bt6;

import java.util.Objects;

public class Student {
    private String id;
    private String myoji;
    private String namae;
    private Teacher tutor;

    public Student(String id, String myoji, String namae) {
        this.id = id;
        this.myoji = myoji;
        this.namae = namae;
    }

    public void setTutor(Teacher teacher) {
        this.tutor = teacher;
        teacher.addStudent(this);
    }

    public void showStatus() {
        String status = "Student " + getProfile() + " :: ";
        if(Objects.nonNull(tutor)) {
            status += tutor.getId();
        }
        System.out.println(status);
    }
    
    private String getProfile() {
        return id + ": " + myoji + " " + namae;
    }

    public String getId() {
        return id;
    }
}
