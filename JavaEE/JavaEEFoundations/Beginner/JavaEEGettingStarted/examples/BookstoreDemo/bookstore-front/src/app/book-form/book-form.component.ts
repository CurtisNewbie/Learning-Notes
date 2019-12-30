import { Component, OnInit } from "@angular/core";
@Component({
  selector: "app-book-form",
  templateUrl: "./book-form.component.html",
  styles: []
})
export class BookFormComponent implements OnInit {
  // remember to import FormsModule in app.module.ts for the ngModel to work
  book = {
    title: "",
    description: "",
    imageURL: "",
    unitCost: "",
    isbn: "",
    numOfPages: "",
    language: ""
  };

  constructor() {}

  ngOnInit() {}
}
