import { Observable } from 'rxjs';

export interface GenericMethod<T> {
  post(data: T): void;
  put(data: T): void;
  getAll(): Observable<T[]>;
  getById(id?: number): Observable<T>;
  deleteById(id?: number): void;
  delete(data: T): void;
}
