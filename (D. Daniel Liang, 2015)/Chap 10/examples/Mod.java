import java.util.*;

public class Mod {

    private String name;
    private List<Student> students;

    /**
     * 
     * @param n name
     */
    public Mod(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String toString() {
        return name;
    }
}