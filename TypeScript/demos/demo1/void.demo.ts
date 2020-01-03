// void datatype can only be assigned the value of null or undefined
let nothing: void = null;
console.log(nothing);

nothing = undefined;
console.log(nothing);

// this causes error
// nothing = 123;

function sayHello(): void {
  console.log("Yo What's up");
}

// this function doesn't return anything or it just returns void.
nothing = sayHello();
