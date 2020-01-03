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

let me: Person = new Person("curtis", 24);
console.log("name " + me.getName());
console.log("Age " + me.getAge());
