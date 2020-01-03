// Any Type
let anything: any = "I am a string";
anything = 5;
anything = 19.532323;
anything = "becomes back to a str";

// x and y parameters can be of any type, e.g., string or number
function processingData(x: any, y: any): any {
  return x + y;
}

let strA: string = "I am a ";
let strB: string = "string";
// concatenation
console.log(processingData(strA, strB));

let numA: number = 123;
let numB: number = 456;
// sum
console.log(processingData(numA, numB));
