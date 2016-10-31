import java.util.HashMap;
import java.util.UUID;

public class Course {

    public Course(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj instanceof Course)) {
            Course c = (Course) obj;
            if (this.id == c.id) {
                return true;
            }
        }

        if ((obj instanceof Integer)) {
            Integer c = (Integer) obj;
            if (this.id == c) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    private int id;
}
