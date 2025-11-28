package bt6;

import java.util.LinkedList;

public class Teacher extends Person {
    LinkedList<Student> assignedStudents = new LinkedList<>();

    public Teacher(String id, String myoji, String namae) {
        super(id, myoji, namae);
    }

    public void addStudent(Student student) {
        if (!assignedStudents.contains(student)) {
            assignedStudents.add(student);
        }
    }

    public void showStatus() {
        String status = "Teacher " + getProfile() + " :: ";
        for (Student student : assignedStudents) {
            status += student.getId() + " ";
        }
        System.out.println(status);
    }
}
