# Notes of TypeScript Syntax and Config - JavaTpoint

Src: https://www.javatpoint.com/typescript-tutorial

## 1. Compiler Configuration

TypeScript compiler configuration is given in <b>tsconfig.json</b> file, specifying how the Typescript file is transpiled to Javascript file.

    {
        "compilerOptions": {
            "declaration": true,
            "emitDecoratorMetadata": false,
            "experimentalDecorators": false,
            "module": "none",
            "moduleResolution": "node",
            "noFallthroughCasesInSwitch": false,
            "noImplicitAny": false,
            "noImplicitReturns": false,
            "removeComments": false,
            "sourceMap": false,
            "strictNullChecks": false,
            "target": "es3"
        },
        "compileOnSave": true
    }

### 1.1 Tranpile and Execute Commands

To transpile TypeScript file:

    $ tsc fileToBeTranspiled.ts

To run JavaScript file in CLI:

    $ node fileToBeTranspiled.js

**_ [Demo 1] _**

## 2. Built-in Data Types

TypeScript supports five built-in data types.

    1. number
    2. void
    3. string
    4. null
    5. boolean
    [Any Type]

    // Use lowercase

### 2.1 Number

As in JavaScript, all the <b>numbers</b> are stored as <b>floating-point values</b>. Thus, regardless of Integer, Binary, Floating numbers are covered by <b>Number Type</b>. (See numberDemo.ts)

### 2.2 String

<b>String</b> can be used with <b>Expressions</b>, or i.e., expressions can be embedded intp the string value referencing to other string variables.

    e.g.,
    let appleStr: string = "apple";
    let embStr: string = `${appleStr} is a fruit`;

The syntax of expression is as follows:

    ${variableName}

<b>When using expression, neither single quote nor double quote should be used. Use `</b>

### 2.3 Boolean

Boolean has two values, either <b>"true"</b> or <b>"false"</b>.

### 2.4 Void

Void can be used as a datatype, tho it's not really useful. When it's treated as a datatype, it can only be assigned the value of <b>"undefined"</b> or <b>"null"</b>.

    e.g.,
    let nothing: void = null;
    nothing = undefined;

### 2.5 Null Type

Null represents a variable whose value is undefined. The Null accepts the only one value, which is null.

### 2.6 Any Type

Any Type is the super type of all data types. It is used to opt out of type checking in Typescript. I.e., it refers to "Any" data type.

## 3. User-Defined Data Type

User-defined data types include:

    e.g.,
    - Array
    - Tuple
    - Enum
    - Interface
    - Class

### 3.1 Array

There two ways to declare an array, one is by declaring the type of the element as in most of the language.

    var list: number[] = [1, 2, 3, 4, 5];

Another way is to declare a generic Array type.

    var genericList: Array<number> = [5, 4, 3, 2, 1];

### 3.2 Tuple

Tuple types allow expressing an array with a fixed number of elements whose types are known. Types and orders matter.

    let tup: [string, number];
    tup = ["string", 123];

### 3.3 Interface

"In TypeScript, interfaces fill the role of naming these types, and are a powerful way of defining contracts within your code as well as contracts with code outside of your project."

src: https://www.typescriptlang.org/docs/handbook/interfaces.html

An Interface can be considered as the minimum requirements of the values and their types (or their structure as a whole). Personally, it seems like this can be considered as an "object" that fulfills the required structure in terms of values (of same names) and structure (of same types).

This is called <b>"duck typing"</b> or <b>"structural subtyping"</b>.

For example, explicit structure can be declared without using interface:

    function displayName(personObject: { name: string }) {
        console.log("[Defined Structure] Displaying Name: " + personObject.name);
    }
    let obj = { age: 24, name: "curtis", do: "programming" };
    displayName(obj);

This can be improved with interface, which provides more functionalities and flexibility:

    interface PersonWithName {
        name: string;
    }

    function displayPersonName(personObject: PersonWithName) {
        console.log("[Interface] Displaying Name: " + personObject.name);
    }
    displayPersonName(obj);

The same object used in both methods will work in both methods, as it has meet the required structure declared in both functions. So, this is only a minimum requirement of structure.

An interface can have <b>optional properties with a ? mark</b> and the <b>read only</b> properties as follows:

    interface AdvancedPerson {
        // readonly, cannot be modified
        readonly name: string;

        // optional
        habit?: string;

        // optional
        age?: string;
    }

### 3.4 Class

Classes contains implementation inside, which are used as a template for creating objects like other languages. This is very similar to Java, excpet that it's optional to define the return type.

    class Person {
        name: string;
        age: number;

        constructor(name: string, age: number) {
            this.name = name;
            this.age = age;
        }

        getName(): string {
            return this.name;
        }

        // it's optional to claim the return type
        getAge() {
            return this.age;
        }
    }
    // use new keyword
    let me: Person = new Person("curtis", 24);

### 3.5 Function

Function can be <b>named</b> or <b>anonymous</b>.

    // named function
    function sum(a: number, b: number): number {
        return a + b;
    }
    console.log(sum(1, 1));

    // anonymous function:
    let mulytiply = function(a: number, b: number) {
        return a * b;
    };
    console.log(mulytiply(2, 5));

### 3.6 Enum

TypeScript supports <b>string-based</b> and <b>numeric-based</b> Enums. By default, elements' indices starts from 0. Though string and number can be mixed in Enum in TypeScript, it's just bad.

Without explicitly specifying string and number for the elements in Enum, their values are their <b>indicides</b>.

    // index-based enum
    enum Color {
        REG, // 0
        GREEN, // 1
        BLUE // 2
    }
    let myColor = Color.REG;

    // string-based Enum
    enum Class {
        GOOD = "A",
        INTERMEDIATE = "B",
        PASS = "C"
    }

    // number-based Enum
    enum Grade {
        GOOD = "70%",
        INTERMEDIATE = "60%",
        PASS = "50%"
    }

## 4. Generic

The way Generic works is quite similar to the one in Java, using "\<T>" notation for type checking.

    // T is a generic type
    function get<T>(stuff: T): T {
        return stuff;
    }
    let str: string = get<string>("A String");
    let num: number = get<number>(123456);

## 5. Decorators

Decorators are just like Annotation, which use <b>"@"</b> symbol. Tho, it is now experimental.

**_ [Demo 2] _**

## 6. Difference between Null and Undefined

**Null** is an assignment value, while **Undefined** is not an assignment value, it means that a variable is not yet assigned a value tho it's declared.

**Null** is an object, that is assigned to represent that such a variable is not pointing to any object nor having any value.

While performing primitive operations, **null** is coverted to **_0_**, and **undefined** is coverted to **_NaN_**.

## 7. TypeScript Variables

We can declare variables using keyword **var**. In ES6, we can define variables using **let** and **const** keyword, while they differ in scope and usage. In TypeScript, it is always recommended to define variables using let keyword, as it provides type safety.

    var [identifier]: [type] = value;

    let [identifier]: [type] = value;

Variables declared using **var** has a **Function Scope**.

Variables declared using **let** has a **Block Scope**, that they are scoped to the nearest enclosing block. Thus, it can be smaller than function scope. E.g., within if statement.

### 7.1 Re-declaration and Shadowing

With **var**, variables with same name can be re-declared many times, while we will only get one. This will not throw exceptions, and is actually valid, though it may cause bugs.

    For example:
        function do(a){
            var a;
            var a;

            if(...){
                var a;
            }
        }

With **let**, variables with same names cannot be declared.

**Shadowing** is the act of introducing a new variable with the same name in a more nested scope. One may take advantage of it and the different scopes, while it may cause confusion as well as bugs.

### 7.2 Hoisting (of Declaration)

In **hoisting**, variables and function declarations are moved to the top of their enclosing scope before code execution. Only declaration is hoisted not initialisations, however, this is for **var only, not let**.

    E.g.,

    // this is hoisted
    var hoistedVar;

    ........

    // this is not
    hoistedVar = 123456;

### 7.3 const Declaration

**const** keyword is for declaration of constant variables that cannot be changed later.

    e.g.,

    const APPLE = 1;

## 8. TypeScript Operators

**==** checks the values of two operands.

**===** checks both the type and the values.

**!=** not equal in terms of value.

**!==** not equal in terms of value and type.

### 8.1 Bitwise

**&** bitwise and

**|** bitwise or

**^** bitwise xor

**~** bitwise not

**>>** bitwise right shift

**<<** bitwise left shift

**>>>** bitwise right shift with zero appended

**expre ? true : false** same as in Java

### 8.2 Type Operators

**in** check whether one property is in the object

**delete** delete a specific property from object

**typeof** return type of operand

**instanceof** whether one is of a specific type

## 9. Type Assertion

Type assertion refers to the machanism that tells the compiler about the type of a variable. There are two ways of type assertion.

1. Angular Bracket<>

   e.g.,
   let name = <string> "curtis";

2. as keyword

   e.g.,
   let name = "curtis" as string;

**Type Assertion For Object:**

    e.g.,
    interface Buddy {
        name: string;
        age: number;
        habit: string;
    }

    let myBuddy = <Buddy>{};
    myBuddy.name = "MyBuddy";
    myBuddy.age = 24;
    myBuddy.habit = "Do nothing";
