import { Injectable } from '@angular/core';
import { GenericMethod } from './../generic/generic-method';
import { Contact } from './contact';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ContactService implements GenericMethod<Contact>{

  private END_POINT = 'http://localhost:8080/api/contacts';

  constructor(private http: HttpClient) { }

  post(data: Contact): void {
    throw new Error("Method not implemented.");
  }
  put(data: Contact): void {
    throw new Error("Method not implemented.");
  }
  getAll(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.END_POINT);
  }
  getById(id?: number): Observable<Contact> {
    throw new Error("Method not implemented.");
  }
  deleteById(id?: number): void {
    throw new Error("Method not implemented.");
  }
  delete(data: Contact): void {
    throw new Error("Method not implemented.");
  }

}
