let appleStr: string = "apple";

// neither single quote nor double quote
let embStr: string = `${appleStr} is a fruit`;
console.log(embStr);

// trim()
let strWithSpaces: string = " asdfasdfasdf    ";
strWithSpaces.trim();
// trim doesn't affect original variable
console.log(strWithSpaces);
console.log(strWithSpaces.trim());
console.log(strWithSpaces.trimLeft());
console.log(strWithSpaces.trimRight());

// charAt()
console.log(strWithSpaces.charAt(3));

// concat()
let constr: string = "abc";
console.log(constr.concat("def", "ghl"));

// endsWith()
console.log(constr.endsWith("bc"));

// includes()
console.log(constr.includes("ab"));

// indexOf()
console.log(constr.indexOf("c"));

// lastIndexOf()
console.log(constr.lastIndexOf("a"));

// match()
let result = constr.match(/./g);
console.log(constr, result);

// replace()
console.log(constr.replace("c", "ccc"), constr);

// search()
console.log(constr.search(/c/));

// slice()
console.log(constr.slice(0, 2));

// split()
console.log(constr.split(""));

// toUpperCase()
console.log(constr.toUpperCase());

// toLowerCase()
console.log(constr.toLowerCase());
