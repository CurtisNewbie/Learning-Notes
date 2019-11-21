Simple Explaination of Streams in Java:

    Stream uses the concept of functional programming, that a stream is like a sequence of data,
    where we can process each data one by one.

    Normally, a Stream is used with Lambda, the demo shows how it works with and without lambda. Streams
    methods may or may not return stream object, which simulates a stream processing or functional
    processing or method chaining (that processes each element one by one).

    The arguments of these methods are objects of functional interfaces, thus it makes no sense
    not using the Lambda.

Terminal and Non-Terminal Operations:

    Non-Terminal Operations refer to those intermediate methods that transform or filter the elements in the stream, which
    returns another streams that can be chained (like strema chaining or processing).

    Terminal Operations refer to those methods that terminate or end the streams when the operation is finished. These methods
    do not return stream objects. Operations in streams are not performed when streams do not have terminal operations specified.
    They are "lazy";

Sources:

    1.  https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/stream/Stream.html <br>
    2.  https://www.mkyong.com/java8/java-8-streams-map-examples/ <br>
    3.  http://tutorials.jenkov.com/java-functional-programming/streams.html
