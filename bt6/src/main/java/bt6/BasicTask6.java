package bt6;

public class BasicTask6 {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("t001", "Shinshu", "Ichiro");
        Student student1 = new Student("s001", "Nagano", "Taro");
        Student student2 = new Student("s002", "Ueda", "Jiro");
        Student student3 = new Student("s003", "Shinonoi", "Hanako");
        student1.setTutor(teacher);
        student2.setTutor(teacher);
        student3.setTutor(teacher);
        
        teacher.showStatus();
        student1.showStatus();
        student2.showStatus();
        student3.showStatus();
    }
}
