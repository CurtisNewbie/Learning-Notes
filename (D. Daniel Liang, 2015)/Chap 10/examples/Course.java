import java.util.*;

public class Course {

    private String name;
    private List<Mod> compulsoryMods;
    private List<Mod> optionalMods;

    /**
     * 
     * @param n course name
     */
    public Course(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    // be aware of the shadow copy
    public List<Mod> getCompulsoryMods() {
        return this.compulsoryMods;
    }

    // be aware of the shadow copy
    public void setCompulsoryMods(List<Mod> m) {
        this.compulsoryMods = m;
    }

    // be aware of the shadow copy
    public void setOptionalMods(List<Mod> m) {
        this.optionalMods = m;
    }

    // be aware of the shadow copy
    public List<Mod> getOptionalMods() {
        return this.optionalMods;
    }

    public String toString() {
        return name + " Compulsory Mods: " + compulsoryMods + " Optional Mods: " + optionalMods;
    }
}