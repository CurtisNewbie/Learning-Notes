// index-based enum
enum Color {
  REG,
  GREEN,
  BLUE
}
let myColor = Color.REG;
console.log("Color: " + myColor);

// string-based Enum
enum Class {
  GOOD = "A",
  INTERMEDIATE = "B",
  PASS = "C"
}
let myClass = Class.GOOD;
console.log("Class: " + myClass);

// number-based Enum
enum Grade {
  GOOD = "70%",
  INTERMEDIATE = "60%",
  PASS = "50%"
}
let myGrade = Grade.GOOD;
console.log("Grade: " + myGrade);
