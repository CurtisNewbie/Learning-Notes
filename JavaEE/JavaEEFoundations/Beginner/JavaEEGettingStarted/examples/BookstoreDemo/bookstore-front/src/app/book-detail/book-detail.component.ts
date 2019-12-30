import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-book-detail",
  templateUrl: "./book-detail.component.html",
  styles: []
})
export class BookDetailComponent implements OnInit {
  // dummy data
  private book = {
    id: "1",
    title: "Dummy Title",
    description: "Dummy Description",
    imageURL: "",
    unitCost: "1233",
    isbn: "123-123123-123",
    numOfPages: "123",
    language: "English"
  };

  // router comes from Injection
  constructor(private router: Router) {}

  ngOnInit() {}

  // method for invoking RESTapi
  delete() {
    console.log("delete() invoked");
    this.router.navigate(["/book-list"]);
  }
}
