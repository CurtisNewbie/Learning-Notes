// type assertion
let myName = <string>"curtis";
console.log(typeof myName);

let me = "curtis" as string;
console.log(typeof me);

// for object

interface Buddy {
  name: string;
  age: number;
  habit: string;
}
let myBuddy = <Buddy>{};
myBuddy.name = "MyBuddy";
myBuddy.age = 24;
myBuddy.habit = "Do nothing";
console.log(myBuddy);
