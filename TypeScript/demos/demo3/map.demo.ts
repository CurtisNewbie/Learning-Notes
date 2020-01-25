// create a map
let map = new Map<string, string>();
map.set("Curtis", "learning TS");
map.set("Sharon", "learning Maya");
console.log(map);

// error
// map.set("Tom", 3);

// list all keys
console.log(map.keys());

// list all values
console.log(map.values());

// get by key
console.log("key: Curtis", map.get("Curtis"));
console.log("key: Sharon", map.get("Sharon"));

// delete by key
console.log("Delete key: Curtis", map.delete("Curtis"));

// size (not a function)
console.log("Size:", map.size);

// clear all
map.clear();
console.log("Claer all", map);
