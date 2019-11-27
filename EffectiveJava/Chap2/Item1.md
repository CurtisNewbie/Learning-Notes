# Chapter 2, Item 1 - Creating and Destroying Objects:

<h3>Item 1: Static factory methods instead of constructors</h3>

Instead of using the traditional way (constructors) to obtain instance of objects, a class can provide a <strong>public static factory method</strong> to return instance of the class.

for example:

    public static Boolean valueOf(boolean b){
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

The static factory method mentioned here is not the Factory Method design pattern. Using this static factory method to obtain instances has pros and cons compared with constructors.

<h2>Advantages Over Constructors</h2>

<b>First advantage is that static factory methods can provide a meaningful names that inform users what the returned objects are.</b> Further, constructors can only use the name of the class, so less information can be provided.

<b>Second Advantage is that the static factory methods are not required to create a new object every time they are used.</b> In the snippet shown above, this method uses pre-constructed/cached objects, that are used frequently. This is similar to <b>Flyweight Pattern</b>, and it can greatly improve performance.

Further, with this way to gain objects, classes are able to maintain strict control over which and what objects can be created or exist at any time. A class that applies strict control like this is said to be <b>instance-controlled</b>. Instanced-controlled classes guarantee that it is a <b>singleton</b> or <b>noninstantiable</b>, and <b>if the class is immutable, it can guarantee that no two equal objects exist (e.g., Enum)</b>.

<b>Third advantage is that static factory method can return an object of any subtype of the returned type.</b> This is very flexible and compact. This also allows you to return objects of classes without making these classes public. Users of the API rely on the interfaces, which is called a <b>interface-based frameworks</b>.

    More on:<https://stackoverflow.com/questions/6129026/effective-java-by-joshua-bloch-item1-static-factory-method>

More specifically, say we want a method that returns a <i>Type</i> object as follows:

    Public interface Type{
        ...
    }

    // by convention, this companion class's should be plural (e.g., List and Lists)
    public static class Types implements Type{

        public static Type getSpecialList(String ... args){
            return new SpecialType();
        }

        // this class can't be instantiated without the static factory method due to the scope
        private class SpecialType{
            ...
        }
    }

SpecialList is a class that extends <i>Type</i> interface, we want to customise some of its implementation and methods, but we do not want to make this class public and do not want it to be instantiable. So, with such manner, we make use the static factory method. Users of the API can follow the specification of the interface without the need to know the implementation of the "SpecialType", if the implementation changes, users are not needed to be aware of them.

<b>Fourth advantage is that the class of the returned object can vary based on the input parameters.</b> Which means that the returned objects or the selected implementation can vary depending on the input parameters. The implementation can vary from release to release based on the design of the framework, while the changes won't affect the users of the API.

<b>Fifth advantage is that the class of the returned object need not exist when the class containing this method is writen.</b> This forms the basis of <b>Service Provider Framework</b>, where in there are three major components:

    1. Service interface
    2. Provider registration API
    3. Service access API

The <i>service interface</i> represents an implementation. The <i>provider registration API</i>is used to register implementation, and the <i>service access API</i> is used to obtain an instance of the service. Among these components, the <i>service access API</i> is the one that uses static factory methods.

For example, in JDBC

    DriverManager.getConnection()

is the service access API that uses the static factory methods to return Connection objects using different implementation depending on the input parameters.

<h2>Disadvantages</h2>

<b>The main limitation is that the classes without public or protected constructors cannot be subclassed.</b> Meaning that the good implementation classes such as those private classes in <b>Collections Framework</b> can not be reused or inherited.

<b>The second problem is that these static factory methods are hard to be found by programmers.</b> They are not easy to identify as the constructors. To avoid this problem, a number of naming conventions can be followed as below:

    from - type-conversion method

        Date d = Date.from(...);

    of - aggregation method

        Set<Rank> cards = EnumSet.of(QUEEN, KING);

    valueOf - similar to from and of

    instance() or getInstance() or create() or newInstance()

    get[TypeName]() or new[TypeName]()

        .... = Files.getBufferedReader(path);

    [typeName]()
        ... = Collections.list(...);

<b>Overall, static factory methods are preferable.</b>
