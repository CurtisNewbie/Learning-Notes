// explicitly specify a structure as a parameter
function displayName(personObject: { name: string }) {
  console.log("[Defined Structure] Displaying Name: " + personObject.name);
}

// in order to use this method, we should have a structure or an object at least containing a value called "name" and of type "string".
let obj = { age: 24, name: "curtis", do: "programming" };
displayName(obj);

// -------------------------------------------------------

// Interface is using the exact same concept

// -------------------------------------------------------
interface PersonWithName {
  name: string;
}

function displayPersonName(personObject: PersonWithName) {
  console.log("[Interface] Displaying Name: " + personObject.name);
}
// it has fulfilled the minimum requirement
displayPersonName(obj);

// interface can have optional properties with a ? mark and "readonly" properties as follows:
interface AdvancedPerson {
  // read only, like a constant
  readonly name: string;
  // optional
  habit?: string;
  // optional
  age?: string;
}
