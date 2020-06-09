import { Component, OnInit } from '@angular/core';
import { User } from '../classes/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  private currentUser :User;
  constructor() { }

  ngOnInit(): void {
    this.currentUser = this.getCurrentuser();
  }

    public getCurrentuser(){
        return JSON.parse(window.localStorage.getItem('currentUser'));
    }

    public logout(){
      window.localStorage.removeItem('currentUser');
    }
}
