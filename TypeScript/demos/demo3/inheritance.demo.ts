class Vehicle {
  protected type: string = "Vehicle";

  say() {
    return "I am " + this.type;
  }
}

class Flight extends Vehicle {
  constructor() {
    // same as in java
    super();
    this.type = "Flight";
  }
}

let veh = new Vehicle();
let fli = new Flight();
console.log(veh.say());
console.log(fli.say());
