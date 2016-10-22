import java.util.ArrayList;

class Person {
    int person_id;
    ArrayList<Course> attend_course = new ArrayList<>();
    ArrayList<Course> teach_course = new ArrayList<>();


    /*getter and setters*/
    public void setPerson_id(int id) {
        person_id = id;
    }
    public int getPerson_id(){
        return person_id;
    }
    public ArrayList<Course> getAttend_course(){
        return attend_course;
    }
    public void addAttendCourse(Course c){
        attend_course.add(c);
    }
    public void deleteAttendCourse(Course c){
        attend_course.remove(c);
    }
    public ArrayList<Course> getTeach_course(){
        return teach_course;
    }
    public void addTeachCourse(Course c){
        teach_course.add(c);
    }
    public void deleteTeachCourse(Course c){
        teach_course.remove(c);
    }


}

class Course {
    int course_id;
    ArrayList<Person> students  = new ArrayList<>();
    ArrayList<Person> instructor = new ArrayList<>();
    ArrayList<Machine> machine = new ArrayList<Machine>();


    /*getter and setters*/
    public void setCourse_id(int id) {
        course_id = id;
    }
    public int getCourse_id(){
        return course_id;
    }
    public ArrayList<Person> getStudents(){
        return students;
    }
    public void addStudent(Person person){
        students.add(person);
    }
    public void deleteStudent(Person person){
        students.remove(person);
    }
    public ArrayList<Person> getInstructor(){
        return instructor;
    }
    public void addInstructor(Person person){
        instructor.add(person);
    }
    public void deleteInstructor(Person person){
        instructor.remove(person);
    }

}

class Machine{

}