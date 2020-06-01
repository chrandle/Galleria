import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject,Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  private currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currenntUser')));
    this.currentUser = this.currentUserSubject.asObservable();
   }

   public get currentUserValue(): User {
      return this.currentUserSubject.value;
   }

   login(username: string, password: string) {
     // TODO: modify to use webpackconfig to store api?
     return this.http.post<any>('http://localhost:9090/login', { username, password})
     .pipe(map(user => {
       localStorage.setItem('currentUser', JSON.stringify(user));
       this.currentUserSubject.next(user);
       return user;
     }));
   }

   logout() {
     localStorage.removeItem('currentUser');
     this.currentUserSubject.next(null);
   }
}
