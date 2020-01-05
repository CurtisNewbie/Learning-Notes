class Male {
  name: string;
  gender: Gender;
  age: number;

  constructor(n: string, a: number) {
    this.name = n;
    this.age = a;
    this.gender = Gender.MALE;
  }
}

enum Gender {
  MALE,
  FEMALE
}

let obj = new Male("Curtis", 24);

// in opeartor to check whether one property is in an object
console.log("name" in obj); // true

// delete operator to delete a property
delete obj.gender;
console.log(obj);

// instanceof
console.log("obj is a Male class", obj instanceof Male);

// check typeof
console.log("obj is a type of", typeof obj);
