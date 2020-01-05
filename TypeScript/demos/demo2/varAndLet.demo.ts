// var is of function scope
// let is of block scope

function doSomething() {
  var globalVar = "globalVar - I am inside the function";
  var innerVar = "innerVar - I am declared before if statement";
  console.log(globalVar);
  console.log(innerVar);

  if (true) {
    var globalVar = "globalVar - I am inside the if statement";
    let innerVar = "innerVar - I am on my own within if statement";
    console.log(innerVar);
  }
  var innerVar = "innerVar - I am declared after if statement";
  console.log(globalVar);
  console.log(innerVar);
}

doSomething();

function say() {
  let str = "Outside let";

  if (true) {
    // have a scope within if statement
    let str = "inside let";
  }
  console.log(str);
}

say();
