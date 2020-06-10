import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  // POST user to DB
  public newUser(user: User) {

    return this.http.post(
      'http://localhost:9090/user/register',
      user,
      {responseType:'text' as 'json'}
      );
  }

  // GET all Users
  public getAllUsers() {
    return this.http.get('http://localhost:9090/user/all');
  }

  // GET a single User by ID;
  public getUserByID(id: number) {
    return this.http.get('http://localhost:9090/user/' + id);
  }

  // DELETE a single User by ID;
  // TODO:  require either admin status or JWT of deleted user
  public deleteUser(id: number) {
    return this.http.delete('http://localhost:9090/user/delete/' + id);
  }



}
