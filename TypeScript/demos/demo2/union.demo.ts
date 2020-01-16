// Union

let value: string | number;
value = "Curtis";
if (typeof value === "string") {
  console.log("value is a string");
}

value = 123;
if (typeof value === "number") {
  console.log("value is a number");
}

let arr: number[] | string[];
arr = [1, 2, 3, 4];
console.log("number[] type");
display(arr);

arr = ["apple", "orange", "banana"];
console.log("string[] type");
display(arr);

function display(arr: number[] | string[]) {
  for (let i in arr) {
    console.log(arr[i]);
  }
}
