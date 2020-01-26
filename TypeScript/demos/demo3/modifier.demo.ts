class Car {
  readonly type: string;
  private name: string;
  public color: string;

  constructor(t: string, n: string, c: string) {
    this.type = t;
    this.name = n;
    this.color = c;
  }

  getName(): string {
    return this.name;
  }
}

// create a Car object
let car: Car = new Car("Car", "Curtis's car", "Red");

// public property
console.log(car.color);

// readonly property
console.log(car.type);

// private
console.log(car.getName());

// this causes error
// car.type = "flight";
