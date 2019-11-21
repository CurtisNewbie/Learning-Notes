import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (String s : new String[] { "apple", "banana", "cat" })
            list.add(s);

        // get a stream from a list
        Stream<String> stream = list.stream();

        // forEach method takes an Consumer object, which is a functional interface.
        stream.forEach(new Consumer<String>() {

            @Override
            public void accept(String t) {
                // print out each element (String)
                System.out.println(t);
            }
        });

        // map method takes an Function object, which is a functional interface. Note
        // that a stream can only operate one terminal operation (such as the forEach
        // above) at a time, thus this is why we are getting another stream here
        // (anotherStream).
        Stream<String> anotherStream = list.stream();
        anotherStream.map(new Function<String, Character>() {

            @Override
            public Character apply(String t) {
                // convert each String to Character
                return t.charAt(0);
            }

        }).forEach((t) -> { // chain forEach to the map method, where map is a non-terminal method (that
                            // returns stream) and forEach is a terminal method (that doesn't return stream)
            System.out.println(t);
        });

    }

}