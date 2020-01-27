// normal anonymous function
let disp1 = function(str: string) {
  console.log(str);
};

// arrow function
let disp2 = (str: string) => {
  console.log(str);
};

// arrow function without param
let disp3 = () => {
  console.log("there is nothing to display");
};

// arrow function with return type declared
let disp4 = (): string => {
  return "there is nothing to display";
};

disp1("disp1");
disp2("disp2");
disp3();
console.log(disp4());
