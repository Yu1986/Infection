import java.util.HashMap;
import java.util.UUID;

public class People {
    public People(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if ((obj instanceof People)) {
            People c = (People) obj;
            if (this.id == c.id) {
                return true;
            }
        }

        if ((obj instanceof Long)) {
            Long c = (Long) obj;
            if (this.id == c) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    private long id;
}
