export class User {
	username: string;
	password: string;
	email: string;
	token: string;

	constructor(un: string, pw: string = null, em: string = null, tk: string = null){
		this.username = un;
		this.password = pw;
		this.email = em;
		this.token = tk;
	};




}
