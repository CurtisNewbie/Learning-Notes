import java.io.Serializable;

public class Student implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private int age;
    private String nationality;

    /**
     * Instantiate Serializable Student
     * 
     * @param n  name
     * @param p  phone
     * @param a  {@code int} age
     * @param na nationality
     */
    public Student(String n, String p, int a, String na) {
        this.name = n;
        this.phone = p;
        this.age = a;
        this.nationality = na;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "[" + getName() + " " + getPhone() + " " + getAge() + " " + getNationality() + "]";
    }
}