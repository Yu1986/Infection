import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Infection {

    public Infection(DataLoader dl) {
        this.dl = dl;
    }

    public List<People> totalInfection(People model) {
        HashSet<People> infectedPeople = new HashSet();
        LinkedList<People> workQ = new LinkedList();

        workQ.add(model);
        while (!workQ.isEmpty()) {
            People p = workQ.poll();
            if (infectedPeople.contains(p)) {
                continue;
            }
            infectedPeople.add(p);
            dl.getInstructors(p).forEach(i->{workQ.add(i);});
            dl.getStudents(p).forEach(s->{workQ.add(s);});
        }

        List<People> result = new ArrayList<People>();
        infectedPeople.forEach(i->{result.add(i);});
        return result;
    }

    public List<People> limitInfection(People model, int maxInfectedNum) {
        HashSet<People> infectedPeople = new HashSet();
        LinkedList<People> workQ = new LinkedList();
        LinkedList<People> instructorWorkQ = new LinkedList();

        instructorWorkQ.add(model);
        while (!instructorWorkQ.isEmpty() || !workQ.isEmpty()) {
            while (!workQ.isEmpty() && infectedPeople.size() < maxInfectedNum) {
                People p = workQ.poll();
                if (infectedPeople.contains(p)) {
                    continue;
                }
                infectedPeople.add(p);
                dl.getInstructors(p).forEach(i -> {
                    instructorWorkQ.add(i);
                });
                dl.getStudents(p).forEach(s -> {
                    workQ.add(s);
                });
            }
            if (infectedPeople.size() >= maxInfectedNum) {
                break;
            }
            People instructor = instructorWorkQ.poll();
            if (infectedPeople.contains(instructor)) {
                continue;
            }
            workQ.add(instructor);
        }

        List<People> result = new ArrayList<People>();
        infectedPeople.forEach(i->{result.add(i);});
        return result;
    }

    private DataLoader dl;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: ");
            System.err.println("      java -jar xxx.jar input_dir output_dir");
            return;
        }
        DataLoader dl = new DataLoader(args[0]);
        People model = dl.pickModel();
        Infection infection = new Infection(dl);
        List<People> totalInfectedPeople = infection.totalInfection(model);
        List<People> limitInfectedPeople = infection.limitInfection(model, 5);

        (new File(args[1])).mkdir();
        outputToFile(args[1] + File.separator + "total_infection.csv", totalInfectedPeople);
        outputToFile(args[1] + File.separator + "limited_infection.csv", limitInfectedPeople);
    }

    private static void outputToFile(String path, List<People> people) {
        try{
            PrintWriter writer = new PrintWriter(path, "US-ASCII");
            writer.println(DataLoader.COLUMN_NAME_PEOPLE);
            people.forEach(p -> {
                writer.println(p.getId());
            });
            writer.close();
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
}
