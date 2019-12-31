import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookListComponent } from './book-list/book-list.component';
import { MenuComponent } from './menu/menu.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { BookFormComponent } from './book-form/book-form.component';
import { BookService } from './service/book.service';

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    MenuComponent,
    BookDetailComponent,
    BookFormComponent
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule {}
