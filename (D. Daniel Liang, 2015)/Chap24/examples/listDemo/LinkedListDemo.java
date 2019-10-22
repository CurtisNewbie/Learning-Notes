
public class LinkedListDemo {
    public static void main(String[] args) {
        MyList<String> list = new MyLinkedList<>();
        System.out.println(list.toString());

        list.add("Apple");
        list.add("Banan");
        list.add("Cat");
        list.add("Cat");
        System.out.println(list.toString());
        System.out.println("Index of Cat: " + list.indexOf("Cat"));
        System.out.println("Last index of Cat: " + list.lastIndexOf("Cat"));
        System.out.println("Remove 1 " + list.remove(1));
        System.out.println(list.toString());
        System.out.println("Get 2: " + list.get(2));
        System.out.println("Set 1:Dog " + list.set(1, "Dog"));
        System.out.println(list.toString());

    }
}