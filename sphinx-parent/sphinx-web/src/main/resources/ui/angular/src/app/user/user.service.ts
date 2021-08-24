import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = 'http://localhost:8080/users';
  private options = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private httpClient: HttpClient) { }

  findSuggestions(query: string): Observable<User[]> {
    return this.httpClient.get<User[]>(`${this.url}?q=${query}`);
  }

}
