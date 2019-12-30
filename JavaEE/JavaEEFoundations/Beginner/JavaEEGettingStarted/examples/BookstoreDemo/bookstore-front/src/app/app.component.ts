import { Component } from "@angular/core";

//  specify a graphical component
@Component({
  //  name of the component
  selector: "app-root",
  // html/template of this component
  templateUrl: "./app.component.html",
  // inline style css (external css is declared in angular.json)
  styles: []
})
export class AppComponent {
  // title property can be extracted in the template
  title = "bookstore-front";
}
