// array

let arr: string[] = ["we", "are", "string", "elements"];
console.log("let arr: string[], ", arr);

let arrTwo: Array<string> = ["we", "are", "string", "elements"];
console.log("let arrTwo: Array<string>, ", arrTwo);

let firstElement: string = arr[0];
let secondElement: string = arrTwo[1];
console.log("First Element: ", firstElement, "Second Element: ", secondElement);

let twoDimenArr: string[][] = [
  ["first", "row", "elements"],
  ["second", "row", "elements"]
];
console.log("Two Dimensional-Array: ", twoDimenArr);

let elem: string = twoDimenArr[1][0];
console.log("First element in second array: ", elem);

let numArr: string[] = new Array("One", "Two", "Three", "Four");
console.log("Using new Array(), ", numArr);

// array traversal
for (let i = 0; i < numArr.length; i++) {
  console.log(numArr[i]);
}
console.log();

for (let i in numArr) {
  console.log(numArr[i]);
}

// Spread Operator
let array1: number[] = [1, 2, 3];
let array2: number[] = [4, 5, 6];
let mergedArr: number[] = [...array1, ...array2, 7, 8, 9];
console.log("Merged Array: ", mergedArr, "\n");

// other methods
// concat()
console.log(array1.concat(array2));

// copy elements and overwrite elements starting from the targeted index
// copyWithin()
let array3: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9];
console.log(array3.copyWithin(0, 6, 9));

// every()
function isNotZero(element: number, index: number, array: number[]) {
  return element > 0;
}
console.log(array3.every(isNotZero));

// fill()
let array4: string[] = ["1", "1", "1", "1"];
array4.fill("*", 0, 6);
console.log(array4);

// indexOf()
console.log(array3.indexOf(7, 2));

// includes()
console.log(array3.includes(9));

// join()
console.log(array3.join("-"));

// lastIndexOf()
console.log(array3.lastIndexOf(5));

// pop()
let fruits: string[] = ["Apple", "Banana", "Orange"];
console.log("Poped: " + fruits.pop());
console.log(fruits);

// push()
fruits.push("Pineapple", "SomethingElse");
console.log(fruits);

// reverse()
fruits.reverse();
console.log(fruits);

// shift()
console.log("Shifted: ", fruits.shift());
console.log(fruits);

// slice()
console.log(fruits.slice(0, 2));

// sort()
fruits.sort();
console.log(fruits);

// splice()
fruits.splice(0, 0, "BeforeApple");
console.log(fruits);
fruits.splice(3, 1); //delete one element at 3
console.log(fruits);
fruits.splice(0, 1, "First");
console.log(fruits);

// toString()
console.log(fruits.toString());

// unshift()
fruits.unshift("Piggy", "Doggy");
console.log(fruits);
