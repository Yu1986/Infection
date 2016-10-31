import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataLoader {
    public final static String COURSE_INSTRUCTOR_FILE = "course_instructor.csv";
    public final static String COURSE_STUDENT_FILE = "course_student.csv";
    public final static String COLUMN_NAME_PEOPLE = "people_id";
    public final static String COLUMN_NAME_COURSE = "course_id";
    public DataLoader(String path) {
        dataPath = path;

        courseMap = new HashMap<Integer, Course>();
        peopleMap = new HashMap<Long, People>();
        courseInstructorMap = new HashMap<Course, List<People>>();
        instructorCourseMap = new HashMap<People, List<Course>>();
        courseStudentMap = new HashMap<Course, List<People>>();
        studentCourseMap = new HashMap<People, List<Course>>();

        loadData(COURSE_INSTRUCTOR_FILE, courseInstructorMap, instructorCourseMap);
        loadData(COURSE_STUDENT_FILE, courseStudentMap, studentCourseMap);
    }

    public List<People> getInstructors(People p) {
        List<People> result = new ArrayList();
        if (studentCourseMap.containsKey(p)) {
            studentCourseMap.get(p).forEach(course -> {
                courseInstructorMap.get(course).forEach(instructor->{result.add(instructor);});
            });
        }
        return result;
    }

    public List<People> getStudents(People p) {
        List<People> result = new ArrayList();
        if (instructorCourseMap.containsKey(p)) {
            instructorCourseMap.get(p).forEach(course -> {
                courseStudentMap.get(course).forEach(student->{result.add(student);});
            });
        }
        return result;
    }

    public People pickModel() {
        Set<Course> courseSet = courseInstructorMap.keySet();
        int randNum = (int) (Math.random()* courseSet.size());
        Iterator<Course> ite = courseSet.iterator();
        int i=0;
        People result = null;
        while (ite.hasNext()) {
            Course c = ite.next();
            i++;
            if (i >= randNum) {
                result = courseInstructorMap.get(c).get(0);
                break;
            }
        }
        return result;
    }

    String dataPath;
    HashMap<Integer, Course> courseMap;
    HashMap<Long, People> peopleMap;
    HashMap<Course, List<People>> courseInstructorMap;
    HashMap<People, List<Course>> instructorCourseMap;
    HashMap<Course, List<People>> courseStudentMap;
    HashMap<People, List<Course>> studentCourseMap;

    private Course getCourse(int id) {
        if (courseMap.containsKey(id)) {
            return courseMap.get(id);
        } else {
            Course c = new Course(id);
            courseMap.put(id, c);
            return c;
        }
    }

    private People getPeople(long id) {
        if (peopleMap.containsKey(id)) {
            return peopleMap.get(id);
        } else {
            People c = new People(id);
            peopleMap.put(id, c);
            return c;
        }
    }

    private void loadData(String fname, HashMap<Course, List<People>> coursePeople,
                          HashMap<People, List<Course>>peopleCourse) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath + File.separator + fname))) {
            String line = null;
            line = reader.readLine(); // skip the first line, or check column name
            if (line == null)
                return;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length != 2)
                    continue;
                int courseId = Integer.parseInt(split[0]);
                long peopleId = Long.parseLong(split[1]);
                Course c = getCourse(courseId);
                People p = getPeople(peopleId);
                if (coursePeople.containsKey(c)) {
                    coursePeople.get(c).add(p);
                } else {
                    ArrayList<People> peopleList = new ArrayList<People>();
                    peopleList.add(p);
                    coursePeople.put(c, peopleList);
                }
                if (peopleCourse.containsKey(p)) {
                    peopleCourse.get(p).add(c);
                } else {
                    ArrayList<Course> courseList = new ArrayList<Course>();
                    courseList.add(c);
                    peopleCourse.put(p, courseList);
                }
            }
            reader.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}