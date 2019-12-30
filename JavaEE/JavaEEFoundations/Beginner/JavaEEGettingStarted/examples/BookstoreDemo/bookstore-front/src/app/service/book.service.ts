import { Injectable } from "@angular/core";
// import { HttpClient } from "@angular/common/http";

// @Injectable mark this class as a Service so that it can be injected, this class must also be specified in the tsconfig.json in provider section.
@Injectable()
export class BookService {
  // constructor(private http: HttpClient) {
  // inject http
  constructor() {}
  public countBooks(): number {
    // for example, get method to invoke RESTapi
    // this.http.get("/books/coun");

    // dummy  data, this is where the RESTApi called.
    return 2;
  }
}
