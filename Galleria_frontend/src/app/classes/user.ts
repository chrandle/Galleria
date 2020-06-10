export class User {
userid: number;
username: string;
password: string;
email: string;
token: string;

 constructor(un: string, pw: string = null, em: string = null, tk: string = null, uid: number = null){
  this.username = un;
  this.password = pw;
  this.email = em;
  this.token = tk;
  this.userid = uid;
 }

}
