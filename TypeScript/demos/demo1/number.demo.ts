//  integer
let a: number = 12;
// float
let b: number = 123.23123;
// hexadecimal
let c: number = 0x3123;
// octal (base-8 number system)
let d: number = 0o351;
// binary
let e: number = 0b1011;

console.log(a);
console.log(b);
console.log(c);
console.log(d);
console.log(e);

console.log(a, "in exponential form", a.toExponential());
console.log(b, "fixed 2 decimal points", b.toFixed(2));
let num: number = 123456;
console.log(num, "local string", num.toLocaleString());
console.log(b, b.toPrecision(4));
console.log(b, b.toString(), typeof b.toString());

console.log(Number.MAX_VALUE);
console.log(Number.MIN_VALUE);
console.log(Number.POSITIVE_INFINITY);
console.log(Number.NEGATIVE_INFINITY);
console.log(Number.NaN);
