import {Component, OnInit} from '@angular/core';
import {BookService} from 'app/services/api/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styles: []
})
export class BookListComponent implements OnInit {
  // dummy data
  private numOfBooks: number;
  private books = [
    {
      id: '1',
      title: 'Java Programming',
      description: 'Book about java programming',
      imageURL: ''
    },
    {
      id: '2',
      title: 'JavaEE Programming',
      description: 'Book about java EE programming',
      imageURL: ''
    }
  ];

  // bookService is injected
  // constructor(private bookService: BookService) {}
  constructor() {
  }

  ngOnInit() {
    this.numOfBooks = 3;
    // this.bookService.countBooks().subscribe(numOfBooks => this.numOfBooks = numOfBooks);
  }
}
