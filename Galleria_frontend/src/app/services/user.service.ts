import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../classes/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   private currentUser: User = JSON.parse(localStorage.getItem('currentUser'));
  
  constructor(private http:HttpClient,) { }

  // POST user to DB
  public newUser(user: User) {

    return this.http.post(
      'http://localhost:9090/user/register',
      user,
      {responseType:'text' as 'json'}
      );
  }

  //POST update to user db
  public updateUser(user:User,id:number) {
      const httpHeaders = {'Authorization': this.currentUser.token.valueOf()}
      return this.http.post( 'http://localhost:9090/user/update/'+id, user,{ headers:httpHeaders, responseType: 'text'as 'json'});
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
