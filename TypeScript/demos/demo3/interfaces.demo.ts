interface Contract {
  name: string;
  age: number;
}

class ContractImpl implements Contract {
  name: string;
  age: number;

  constructor(n: string, a: number) {
    this.name = n;
    this.age = a;
  }
}

// using interface directly or constructor of the class
let a: Contract = {
  name: "curtis",
  age: 24
};
let b: Contract = new ContractImpl("Curtis", 24);

console.log(a, b);

interface EmployeeContract extends Contract {
  employeeId: string;
}

let employee: EmployeeContract = {
  name: "curtis",
  age: 24,
  employeeId: "123-4556-6"
};

console.log(employee);
