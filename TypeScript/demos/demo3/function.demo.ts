// named function
function display(str: string) {
  console.log(str);
}

// anonymoust function
let disp = function(str: string) {
  console.log(str);
};

// optional param
function say(str: string, greeting?: string) {
  if (greeting === undefined) {
    greeting = "Hi";
  }
  console.log(greeting, str);
}

// default param
function saySomething(str: string, greeting: string = "Yo") {
  console.log(greeting, str);
}

// rest parameter / varargs
function sayALot(greeting: string = "Say", ...things: string[]) {
  let temp: string = greeting;
  for (let s of things) {
    temp += " " + s;
  }
  console.log(temp);
}

display("Display Something");
disp("Disp something");
say("Curtis");
saySomething("Curtis");
saySomething("Curtis", "Mer");
sayALot("Whast'up", "Curtis", "Sharon", "And", "Everybody");
