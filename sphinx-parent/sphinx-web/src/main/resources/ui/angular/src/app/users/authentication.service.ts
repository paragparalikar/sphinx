import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParamsOptions } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from 'src/app/users/user.model';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  private url = 'http://localhost:8080';
  private userSubject: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null);
  public user: Observable<User | null> = this.userSubject.asObservable();

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    const userJson = sessionStorage.getItem('user');
    if (userJson) {
      this.userSubject.next(JSON.parse(userJson));
    }
  }

  public get userValue(): User | null {
    return this.userSubject.value;
  }

  login(username: string, password: string): Observable<User> {
    return this.http.post<any>(`${this.url}/login`, { username, password }, 
    {observe: 'response'})
      .pipe(map(response => {
        const user = response.body;
        const jwt = response.headers.get('Authorization');
        sessionStorage.setItem('user', JSON.stringify(user));
        sessionStorage.setItem('token', jwt!);
        this.userSubject.next(user);
        return user;
      }));
  }

  logout() {
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('token');
    this.userSubject.next(null);
    this.router.navigate(['/login']);
  }
}