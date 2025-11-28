package bt6;

import java.util.LinkedList;

public class Teacher {
    private String id;
    private String myoji;
    private String namae;
    LinkedList<Student> assignedStudents = new LinkedList<>();

    public Teacher(String id, String myoji, String namae) {
        this.id = id;
        this.myoji = myoji;
        this.namae = namae;
    }

    public void addStudent(Student student) {
        if(!assignedStudents.contains(student)) {
            assignedStudents.add(student);
        }
    }
    
    public void showStatus() {
        String status = "Teacher " + getProfile() + " :: ";
        for(Student student: assignedStudents) {
            status += student.getId() + " ";
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
