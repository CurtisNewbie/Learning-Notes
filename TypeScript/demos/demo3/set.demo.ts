// set
let set = new Set<string>();
set.add("sharon");

// this is not added, as it's a duplicate
set.add("sharon");

set.add("curtis");
set.add("newbie");
console.log(set);

// this doesn't work because typescript messing it up
console.log("----------");
for (const v of set) {
  console.log("set: ", v);
}
console.log("----------");

console.log("Size:", set.size);
console.log('Delete "curtis"', set.delete("curtis"), set);
console.log('Has "curtis"', set.delete("curits"));
set.clear();
console.log("clear set", set);
