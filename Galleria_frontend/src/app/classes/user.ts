export class User {
	username: string;
	password: string;
	email: string;

	constructor(un: string, pw: string, em: string){
		this.username = un;
		this.password = pw;
		this.email = em;
	}
}
