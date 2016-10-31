import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class DataLoaderTest {
    private final static String UNITTEST_FOLDER = "unittest";
    private DataLoader dl;
    private People model;
    @Before
    public void setUp() throws Exception {
        (new File(UNITTEST_FOLDER)).mkdir();
        Path file =   Paths.get(UNITTEST_FOLDER+ File.separator + DataLoader.COURSE_INSTRUCTOR_FILE);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writeTest(DataLoader.COLUMN_NAME_COURSE + "," + DataLoader.COLUMN_NAME_PEOPLE + "\n", writer);
            writeTest("1,1\n", writer);
            writer.close();
        } catch (IOException x) {
            x.printStackTrace();
        }

        file =   Paths.get("unittest"+ File.separator + DataLoader.COURSE_STUDENT_FILE);
        if (!Files.exists(file)) {
            Files.createFile(file);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writeTest(DataLoader.COLUMN_NAME_COURSE + "," + DataLoader.COLUMN_NAME_PEOPLE + "\n", writer);
            writeTest("1,2\n", writer);
            writeTest("1,3\n", writer);
            writer.close();
        } catch (IOException x) {
            x.printStackTrace();
        }

        dl = new DataLoader(UNITTEST_FOLDER);

        model = dl.pickModel();
    }
    private void writeTest(String s, BufferedWriter writer) throws IOException {
        writer.write(s, 0, s.length());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStudents() throws Exception {
        List<People> students = dl.getStudents(model);
        assertEquals(2, students.size());
        assertTrue(students.get(0).equals((long)2));
        assertTrue(students.get(1).equals((long)3));
    }

    @Test
    public void getInstructors() throws Exception {
        List<People> instructor = dl.getInstructors(new People(2));
        assertEquals(1, instructor.size());
        assertTrue(instructor.get(0).equals((long)1));
    }

    @Test
    public void pickModel() throws Exception {
        assertTrue(model.equals((long)1));
    }

}