// T is a generic type
function get<T>(stuff: T): T {
  return stuff;
}

console.log(get<string>("A String"));
console.log(get<number>(123456));
