import java.util.*;

public class AssociationDemo {

    public static void main(String[] args) {

        Student curtis = new Student("Curtis", "REG_123456");
        System.out.println(curtis.toString());

        // student takes module
        Mod mod1 = new Mod("CS109");
        Mod mod2 = new Mod("CS120");
        Mod mod3 = new Mod("CS555");

        List<Mod> mods = new LinkedList<>();
        mods.add(mod1);
        mods.add(mod2);
        mods.add(mod3);

        curtis.setModules(mods);
        System.out.println(curtis.getModules().toString());

        Mod mod4 = new Mod("Ma135(op)");
        Mod mod6 = new Mod("Ar552(op)");
        List<Mod> opMods = new LinkedList<Mod>();
        opMods.add(mod4);
        opMods.add(mod6);

        Course c = new Course("CS");
        c.setCompulsoryMods(mods);
        c.setOptionalMods(opMods);
        System.out.println(c.toString());

    }
}