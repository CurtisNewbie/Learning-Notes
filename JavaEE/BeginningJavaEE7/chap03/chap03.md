# Chapter 3 Bean Validation

## Application Level Validation

**Application-level validation** includes three major layers:

1. **Presentation Layer** - e.g., http responses.
2. **Business Logic Layer** - Layer between internal and external, processing business logic.
3. **Business Model Layer** - Layer that maps domain model or data model to the database.

Main Bean Validation Packages:

    javax.validation
    javax.validation.bootstrap
    javax.validation.constraints
    javax.validation.groups
    javax.validation.metadata
    javax.validation.spi

---

## Reference Implementation:

**Hibernate Validator** is the open source reference implementation of **Bean Validation**.

---

## Anatomy of a Constraint

**_Constraints are defined by the combination of a constraint annotation and a list of constraint validation implementations._**

**Constraint Annotation** can be applied to:

- type
- method
- field

**A Constraint in Bean Validation** is made of:

- An annotation defining the constraint
- A list of classes implementing the algorithm of the constraint on a given type (which can be String, Integer, or just a normal Bean class).

---

## Constraint Annotation

**_An annotation is considered as a constraint if its retention policy contains RUNTIME and if the annotation itself is annotated with javax.validation.Constraint_**

For example, a NotNull Constraint Annotation:

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @Constraint(validatedBy = NotNullValidator.class)
    public @interface NotNull {
        String message() default "{javax.validation.constraints.NotNull.message}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

**Constraint Annotations** are just regular annotations, which must define **Meta-Annotations**:

- **@Target({METHOD, FIELD, ...})**: where the constraint can be applied to.
- **@Retention(RUNTIME)**: it should be at least RUNTIME to inspect the objects at runtime.
- **@Constraint(validatedBy = NotNullValidator.class)**: Specifies the class that encapsulates the validation algorithm.
- **@Documented**: optional meta-annotation that specifies whether this constraint be included in JavaDoc or not.

On top of these meta-annotations, **Bean Validation Specification requires each constraint annotation to define three extra attributes**:

- **message**: a way to return an internationalised error message if the constraint is not valid.
- **groups**: Groups are used to control the order in which constraints are evaluated, or to perform partial validation.
- **payload**: This attribute is used to associate metadata information with a constraint.

---

## Constraint Implementation

**_Constraints are defined by the combination of an annotation and zero or more implementation classes._** These implemnetation classes are specified using the **@validatedBy** element of **@Constraint**.

To declare a **Constraint Annotation** representing the constraint, the implementation of this constraint is specified using **@Constraint(validatedBy = .class)** as follows, i.e., the implementation class is _NotNullValidator.class_:

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @Constraint(validatedBy = NotNullValidator.class)
    public @interface NotNull {
        ...
    }

In the implementation class _NotNullValidator_, as a Constraint implementation class, it must implements an **\<\<interface>>** called **ConstraintValidator<A extends Annotation,T>**, where **A** is the Annotation class and **T** is the target type.

    public class NotNullValidator implements ConstraintValidator<NotNull, Object>{

        public void initialize(NotNull parameters){
        }


        public boolean isValid(Object object, ConstraintValidatorContext context){

        }
    }

**ConstraintValidator<A extends Annotation,T>** interface uses **Generics**, which defines two Types, first one is **the name of the Annotation (e.g., @NotNull)**, second one is **the type this annotations applies to(e.g., Object)**. Thus, in above implementation class, it applies for the _@NotNull_ annotation and is for the _Object_ type.

**ConstraintValidator interfacie** defines two methods that need to be implemented:

- **initialise(A constraintAnnotation): void** - initialise the constraint parameters if needed.
- **isValid(T value, ConstraintValidatorContext context): boolean** - implements the validation logic.

---

Notice that since **ConstraintValidator<A extends Annotation,T>** uses **Generic**, we can declare different Constraint algorithm for different data types but using the same **Constraint Annotation**.

For example, we implements three classes, so we can apply **@Size** constraint to types _String_, _BigDecimal_ and _Collection<?>_.

    public class SizeValidatorForString implements ConstraintValidator<Size, String> {...}
    public class SizeValidatorForBigDecimal implements ConstraintValidator<Size, BigDecimal> {...}
    public class SizeValidatorForCollection implements ConstraintValidator<Size, Collection<?>> {...}

---

## Applying a Constraint

Once the annotation is created and implemented, the constraint can be applied on a given element type through **@Target(ElementType.\*)** meta-annotation on the annotation declaration.

- ElementType.FIELD - constrained attributes
- ElementType.METHOD - constrained getters and constrained method return values
- ElementType.CONSTRUCTOR - constrained constructor return values
- ElementType.PARAMETER - constrained method and constructor parameters
- ElementType.TYPE - constrained beans, interfaces and superclasses
- ElementType.ANNOTATION_TYPE - constrained constraint annotations

**_Only static fields and static methods cannot be validated by Bean Validation._**

For example:

    @ChronologicalDates
    public class Order {

        @NotNull @Pattern(regexp = "[a-zA-Z]")
        private String id;

        @Min(1)
        private Double orderMoney;

        private Date createdDate;
        private Date paymentDate;
        private date deliveredDate;

        public Order(){ }

        public Order(@Past Date createdDate){
            this.createdDate = createdDate;
        }

        public @NotNull Double calculateMoney(@GreaterThanZero double changeRate){
            ...
        }

        ...
    }

In the example above, **_@ChronologicalDates_** is a class-level constraint, which is applied for all the _Date_ types in this class, e.g., _createdDate_, _paymentDate_, _deliveredDate_. It can also be applied individually. For the attribute _id_, two constraint-annotations are applied, that it cannot be null **_@NotNull_** and it should match the specified regular expression **_@Pattern_**. For the attribute _orderMoney_, the **_@Min()_** is applied, which constrains this double to have a minimum of 1. The above are the **constraints applied for ElementType.FIELD**.

In the constructor, **_@Past_** is applied for the parameter _Date createdDate_, which is **constraints applied for ElementType.CONSTRUCTOR**. **_@NotNull_** constraint is also applied for the method _calculateMoney()_ for its return value, in which the **_@GreaterThanZero_** is applied for the parameter _double changeRate_. Above is just an example, demonstrating where and how Bean Validation can be used.

---

**_Constraints can be defined either on the attribute or on the getters, but not on both at the same time. It should be consistent in that only attributes or getters are selected._**

---

For more on built-in constraints: https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html

---

## Define and Implement Custom Constraints

Creation of custom constraints can be through:

- aggregating existing constraints
- implement from scratch

### Constraint Aggregation

Aggregating existing constraints is an easier way to create new constraints. **Aggregating existing constraints means that we reuse the existing annotations, and declare these annotations on top of the new constraint annotation. With which we are combining the constraints from multiple annotations, put them together and give it a new name.**

For example, for Email constraint, we do not need to create one from scratch. We can reuse some of the existing constraints, such as @NotNull, @Size, @Pattern. After aggregating them, we just give it a new name, called maybe @Email. Through this way, **we don't need a concrete implementation defined in @Constraint(validatedBy = { })**.

    @NotNull
    @Size(min = 7)
    @Pattern(regexp = "regexpForEmail......")
    @Constraint(validatdBy = {})
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    public @interface Email{
        String message() default "Email Address Not Correct";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

**Remember that we need the three attributes (message, groups, payload).** However, as this is an constraint created by aggregation, thus **if one of the constraints is violated, for example @NotNull, message for @NotNull will be thrown instead of the message we defined for @Email.** To Solve this problem, we need the **@ReportAsSingleViolation**, see below.

    @NotNull
    @Size(min = 7)
    @Pattern(regexp = "regexpForEmail......")
    @ReportAsSingleViolation
    @Constraint(validatdBy = {})
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    public @interface Email{
        String message() default "Email Address Not Correct";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

### Generic Constraint

When Constraints aggregation is not enough, one is implemented for the complex situation. For example, we are now implementing a contraint for a \<String> url, we specifies three properties of this constraint to further validate it.

This constraint is generally used as follows:

    public class ServerConnection{

        @URL_Constraint
        private String resourceURL;

        @NotNull @URL_Constraint(protocol = "http", host = "www.abc.com")
        private String itemURL;

        @URL_Constraint(protocol = "ftp", port = 21)

        //...
    }

The _@URL_Constraint should be applied to String_ Type, and it should be able to provide additional constraints on properties, e.g., _protocol_, _host_, and _port_ if specified. In the URL_Constraint constraint annotation, we need to specifies these three properties in order to use them.

    @Constraint(validatedBy = {URLValidator.class})
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    public @interface URL_Constraint{
        String message() default "Malformed URL_Constraint";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

        String protocol() default "";
        String host() default "";
        int post() default -1;
    }

In this annotation, we specify its implementation class to be _URLValidator.class_, and its targeted application, and when it should be checked (in runtime). Additionally, we add three properties _protocol()_, _host()_, and _post()_, which provide spaces to enforce additional check.

In implementation class:

    public class URLValidator implements ConstraintValidator<URL_Constraint, String>{

        private String protocol;
        private String host;
        private int port;

        public void initialize(URL_Constraint urlConst){
            this.protocol = urlConst.protocol();
            this.host = urlConst.host();
            this.port = urlConst.port();
        }

        public boolean isValid(String value, ConstraintValidatorContext context){
            // it can be null, so @NotNull is not a duplicate
            if(value == null || value.length() == 0)
                return true;

            try {
                URL url = new URL(value);

                if(protocol != null && !protocol.isEmpty() && !url.getProtocol().equals(protocol))
                    return false;

                if(host != null && !host.isEmpty() && !url.getHost().startsWith(host))
                    return false;

                if(port != -1 && url.getPort() != port)
                    return false;

            } catch (MalformedUrlException e){
                return false;
            }
            return true;
        }
    }

To implement a constraint, it **implements ConstraintValidator<A extends Annotation,T> interface** . Here, the annotation is **URL_Constraint**, and it's applied on type **String**. By implementing this interface, two methods must be implemented (**initialize() and isValid()**. The three properties of this constraints are gotten from the **constructor**, by calling the **.protocol()**, **.host()**, **.port()** defined in the constraint annotation. If these properties are provided, we use them to further validate the value.

### Multiple Same Type Constraint on Same Target

additional src: (p.84) https://docs.jboss.org/hibernate/stable/validator/reference/en-US/pdf/hibernate_validator_reference.pdf

Sometimes, we may want to apply same type constraint (of different config) on same target. For example, we want to use multiple regex on the same target, where all regex must be verified to be true.

For example, with **@Pattern**:

    public class Order{

        @Pattern.List({
            @Pattern(regexp = "[a-z]"),
            @Pattern(regexp = "[A-Z]")
        })
        private String orderId;
    }

In this example, @Pattern.List is essentially a list, with this in-mind, in the constraint definition, we need a list of itself (i.e., a property that is also an annotation that contains a list of itself).

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Constraint(validatedBy = PatternValidator.class)
    public @interface Pattern{

        String regexp();
        String meesage() default "...";
        Class<?>[] groups default {};
        Class<? extends Payload>[] payload() default{};

        @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
        @Retention(RUNTIME)
        @interface List{
            Pattern[] value();
        }
    }

**@Pattern** itself is a constraint annotation, in it, there is a property as a constraint annotation that contains a list of @Pattern (**@Pattern[]**). **List** is not a mandatory name, however, it is strongly recommended to name this inner annotation as "List" that makes it explicit. Further, **_e.g., the @Target, @Retention of this inner annotation must be consistent with the outter annotation._**

## Class-Level Constraint

A constraint can be applied to an entire class. The generic type defined in the implementation class will be pointed to the target class. For example **@ChronologicalDates** in the example below is applied to the class _Order_. In the **isValid() method** we can call appropriate getters to conduct the validation algorithm.

    @ChronologicalDates
    public class Order{

        private Date creationDate;
        Private Date paymentDate;

        // getters...
    }

In the implementation class:

    public class ChronologicalDateValidator implements ConstraintValidator<ChronologicalDates, Order>{

        @Override
        public void initialize(ChronologicalDates chronologicalDates){ }

        @Override
        public boolean isValid(Order order, ConstraintValidatorContext context){
            return order.getCreationDate().getTime() < order.getPaymentDate().getTime();
        }
    }

So, the constraints are essentially applied to the several properties (maybe of different types). The implementation access to the object as well as its properties to check its validity.

## Method-Level Constraint

Method-Level Constraint uses a programming style called: **Programming By Constract**. Method-level constraints are used on methods as well as constructors, which requires that: **_preconditions must be met before the method or constructor is invoked._**

    public class Order{

        private String orderId;
        private List<Item> items;

        public Order(@NotNull String id){
            this.orderId = id;
            items = new ArrayList<>();
        }

        public String addItem(@NotNUll Item item){
            items.add(item);
        }

        @AssertTrue
        public boolean hasOrderId(){
            return orderId != null && !orderId.isEmpty();
        }
    }

In example above, _String id_ must be not null, else the constructor is not invoked. In the addItem() method, _Item item_ msut not be null, else the method is not invoked. The **@AssertTrue** constraint is applied to the method _hasOrderId()_, where the return boolean value must be **True**.

## Constraint Inheritance

With bean validation, the constraints are inherited to the subclasses as well. Thus, the subclasses only need to add constraints on its additional properties or methods. The constraints are consequently cumulative.

One needs to pay attention to that when **overriding methods in superclass**, parameter constraints should only be annotated in the root methods rather than in subclasses. For the **constraints on return value**, constraints may be added in subtypes to strengthen the postconditions.

## ConstraintValidatorContext

**ConstraintValidatorContext** is provided when implementing an constraint annotation.

    public class URLValidator implements ConstraintValidator<URL_Constraint, String>{

        public void initialize(URL_Constraint urlConst){ }

        public boolean isValid(String value, ConstraintValidatorContext context){

        }

**ConstraintValidatorContext** can be used to redefine the default constraint message. In the definition of the constraint annotation, only one default message can be defined, while we may want some flexibility providing different violation messages when needed.

This interface contains three major methods:

- **disableDefaultConstraintViolation(): void**
  <br>disables the default **ConstraintViolation** object generation, so we create one on our own
- **getDefaultConstraintMessageTemplate(): String**
  <br>return default message
- **buildConstraintViolationWithTemplate(): ConstraintValidatorContext.ConstraintViolationBuilder**
  <br>get ConstraintViolationBuilder to build a custom violation message

e,g.,

    public boolean isValid(String value, ConstraintValidatorContext context){
        //...
        try {
            URL url = new URL(value);

            if(protocol != null && !protocol.isEmpty() && !url.getProtocol().equals(protocol)){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid Protocol").addConstraintViolation();
                return false;
            }

            if(host != null && !host.isEmpty() && !url.getHost().startsWith(host)){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid Host").addConstraintViolation();
                return false;
            }

            if(port != -1 && url.getPort() != port){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid Port").addConstraintViolation();
                return false;
            }

        } catch (MalformedUrlException e){
            return false;
        }
        return true;
    }

Generally, what the code above does is that it disables the default generation of **ConstraintViolation** object, then we get a builder to create one with new message.

## Deployment Descriptor

XML can be used to define metadata for bean validation. When used, it overrides those annotations declarations. In **validation.xml**, it defines the external **constraints.xml** file.

    <?xml version="1.0" encoding="UTF-8"?>
    <validation-config
        xmlns="http://jboss.org/xml/ns/javax/validation/configuration"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://jboss.org/xml/ns/javax/validation/configuration 
        validation-configuration-1.1.xsd"
        version="1.1">
        <constraint-mapping>META-INF/constraints.xml</constraint-mapping>
    </validation-config>

In **constraints.xml** (book):

    <?xml version="1.0" encoding="UTF-8"?>
    <constraint-mappings
        xmlns="http://jboss.org/xml/ns/javax/validation/mapping"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://jboss.org/xml/ns/javax/validation/mapping 
        validation-mapping-1.1.xsd"
        version="1.1">
        <bean class=".../.../Book" ignore-annotations="false">
            <field name="title">
                <constraint annotation="javax.validation.constraints.NotNull">
                    <message>Title should not be null</message>
                </constraint>
            </field>
            <field name="price">
                <constraint annotation="javax.validation.constraints.NotNull"/>
                <constraint annotation="javax.validation.constraints.Min">
                    <element name="value">2</element>
                </constraint>
            </field>
            <field name="description">
                <constraint annotation="javax.validation.constraints.Size">
                    <element name="max">2000</element>
                </constraint>
            </field>
        </bean>
    </constraint-mappings>

## Validating Constraints

add src: https://docs.oracle.com/javaee/7/api/javax/validation/executable/ExecutableValidator.html#validateReturnValue-T-java.lang.reflect.Method-java.lang.Object-java.lang.Class...-

add src: https://docs.oracle.com/javaee/7/api/javax/validation/ConstraintViolation.html

add src: https://docs.oracle.com/javaee/7/api/javax/validation/Validator.html

Bean validation is handled by the JavaEE container automatically when provided. Without one, Validator must be gained and used programmatically or manually.

    javax.validation.Validator<interface>

With methods in **Validator** interface, we can validate the whole object, or its properties, and so on programmatically.

    javax.validation.ConstraintViolation<interface>

A set of **ConstraintViolation** objects is returned while a bean is validated, where each contains information e.g., violation message. If the set is empty, it means the validation is successful. I.e., **_A ConstraintViolation describes a validation failure_**.

    javax.validation.ExecutableValidator<interface>

**ExecutableValidator** is used to validate executable, i.e., methods and constructors. It provides methods to validate parameters and return values of methods and constructors.

### Obtain A Validator

**Obtain one programmatically**:

    // obtain factory and validator
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    //... validating some beans

    // must close it programmatically
    factory.close();

**Obtain one through CDI**:

    @Inject ValidatorFactory factory;
    @Inject Validator validator;

**Validate a bean**:

    // create a bean
    Order order = new Order("123-456", new Date());

    // validate the bean
    Set<ConstraintViolation<Order>> violations = validator.validate(order);

    // check if there is any violation
    if(violations.isEmpty()){
        // violation passed!
    }else{
        for(ConstraintViolation<Order> v : violations){
            System.out.println(v.getMessage() + "  " + v.getInvalidValue());
        }
    }

**Validate only the properties**:

    // validate property called "orderId"
    Set<ConstraintViolation<Order>> violations = validator.validateProperty(order, "orderId");

**Validate property based on given value**:

    // validate property called "orderId" with given value
    Set<ConstraintViolation<Order>> violations = validator.validateValue(Order.class, "orderId", "____invalidValue__");

**Validate methods using reflective API** :

    // get the method using reflective API
    Method addItemMethod = Order.class.getMethod("addItem", Order.class);

    // get ExecutableValidator
    ExecutableValidator methodValidator = validator.forExecutables();

    // validate parameter
    methodValidator.validateParameters(order, addItemMethod, item);

    // validate return value
    Method hasOrderIdMethod = Order.class.getMethod("hasOrderId", Order.class);
    methodValidator.validateReturnValue(order, hasOrderIdMethod, true)
