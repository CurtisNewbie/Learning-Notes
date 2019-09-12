import java.util.*;

public class Student {

    private String name;
    private String reg_no;

    // a number of modules
    private List<Mod> modules;

    // one specific course
    private Course course;

    /**
     * 
     * @param n   name
     * @param reg reg no
     */
    public Student(String n, String reg) {
        this.name = n;
        this.reg_no = reg;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getReg() {
        return this.reg_no;
    }

    public void setReg(String reg) {
        this.reg_no = reg;
    }

    // be aware of shadow copy
    public List<Mod> getModules() {
        return this.modules;
    }

    // be aware of shadow copy
    public void setModules(List<Mod> mod) {
        this.modules = mod;
    }

    // be aware of shadow copy
    public Course getCourse() {
        return this.course;
    }

    // be aware of shadow copy
    public void setCourse(Course c) {
        this.course = c;
    }

    public String toString() {
        return name + " " + reg_no;
    }

}