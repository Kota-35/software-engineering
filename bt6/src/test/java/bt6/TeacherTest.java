package bt6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TeacherTest {
    
    @Test
    public void testConstructor() {
        Teacher teacher = new Teacher("t001", "Shinshu", "Ichiro");
        assertEquals("t001", teacher.getId());
    }

    @Test
    public void testAddStudent() {
        Teacher teacher = new Teacher("t001", "Shinshu", "Ichiro");
        Student student = new Student("s001", "Nagano", "Taro");
        
        teacher.addStudent(student);
        
        assertTrue(teacher.assignedStudents.contains(student));
    }

    @Test
    public void testAddStudentMultiple() {
        Teacher teacher = new Teacher("t001", "Shinshu", "Ichiro");
        Student student1 = new Student("s001", "Nagano", "Taro");
        Student student2 = new Student("s002", "Ueda", "Jiro");
        
        teacher.addStudent(student1);
        teacher.addStudent(student2);
        
        assertEquals(2, teacher.assignedStudents.size());
        assertTrue(teacher.assignedStudents.contains(student1));
        assertTrue(teacher.assignedStudents.contains(student2));
    }

    @Test
    public void testAddStudentDuplicate() {
        Teacher teacher = new Teacher("t001", "Shinshu", "Ichiro");
        Student student = new Student("s001", "Nagano", "Taro");
        
        teacher.addStudent(student);
        teacher.addStudent(student);
        
        // 重複して追加されないことを確認
        assertEquals(1, teacher.assignedStudents.size());
    }
}

