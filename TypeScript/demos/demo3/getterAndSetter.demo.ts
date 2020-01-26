class Person {
  private name: string;

  constructor(n: string) {
    this.name = n;
  }

  get nameVal(): string {
    return this.name;
  }

  set nameVal(n: string) {
    if (n.length > 0) this.name = n;
  }
}

let p: Person = new Person("curtis");

// using getter
console.log(p.nameVal);

// using setter
p.nameVal = "Sharon";
console.log(p.nameVal);
