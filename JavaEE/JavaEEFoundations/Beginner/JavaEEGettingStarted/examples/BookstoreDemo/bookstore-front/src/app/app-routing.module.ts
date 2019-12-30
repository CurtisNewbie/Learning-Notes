import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { BookListComponent } from "./book-list/book-list.component";
import { BookFormComponent } from "./book-form/book-form.component";
import { BookDetailComponent } from "./book-detail/book-detail.component";

// add paths for routing
const routes: Routes = [
  { path: "book-list", component: BookListComponent },
  { path: "book-form", component: BookFormComponent },
  { path: "book-detail/:bookId", component: BookDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
