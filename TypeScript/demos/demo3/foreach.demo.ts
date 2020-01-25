// callback function passed into forEach
function displayArrayElements(val, i, arr) {
  console.log("Value:", val, "Index:", i, "arr[i]", arr[i]);
}

let arr: number[] = [1, 3, 5, 7, 9];
arr.forEach(displayArrayElements);
