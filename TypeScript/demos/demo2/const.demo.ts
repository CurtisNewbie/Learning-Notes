// be careful, this is transpile to be var (it shouldn't)
const APPLE = "apple";
APPLE = "this causes error";
console.log(APPLE);
