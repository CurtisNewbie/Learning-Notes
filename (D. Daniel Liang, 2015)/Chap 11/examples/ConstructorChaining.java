/**
 * The parent constructor will be called first, then the subclasses that inherit
 * this parent class.
 */
public class ConstructorChaining {
    public static void main(String[] args) {

        // invokes the Chile constructor
        Child c = new Child();

    }

}

class Parent {

    public Parent() {
        System.out.println("Ini Parent Constructor");
    }
}

class Child extends Parent {
    public Child() {
        // super() is called implicitly
        System.out.println("Ini Child Constructor");
    }
}
