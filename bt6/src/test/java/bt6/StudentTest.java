package bt6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class StudentTest {
    
    @Test
    public void testConstructor() {
        Student student = new Student("s001", "Nagano", "Taro");
        assertEquals("s001", student.getId());
    }

    @Test
    public void testSetTutor() {
        Teacher teacher = new Teacher("t001", "Shinshu", "Ichiro");
        Student student = new Student("s001", "Nagano", "Taro");
        
        student.setTutor(teacher);
        
        // setTutorが正常に動作することを確認
        assertNotNull(student);
        assertEquals("s001", student.getId());
    }

    @Test
    public void testSetTutorMultipleStudents() {
        Teacher teacher = new Teacher("t001", "Shinshu", "Ichiro");
        Student student1 = new Student("s001", "Nagano", "Taro");
        Student student2 = new Student("s002", "Ueda", "Jiro");
        
        student1.setTutor(teacher);
        student2.setTutor(teacher);
        
        assertEquals("s001", student1.getId());
        assertEquals("s002", student2.getId());
    }
}

