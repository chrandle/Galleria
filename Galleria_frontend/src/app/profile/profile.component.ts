import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { first } from "rxjs/operators";
import { UserService } from '../services/user.service';
import { AlertService } from '../services/alert.service';
import { AuthenticationService } from "../services/authentication.service";
import { User } from '../classes/user';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private currentUser: User = null;
  private editing: boolean = false;
  editForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formbuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private uService: UserService,
    private alertService: AlertService,
    public authService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentuser();
     this.editForm = this.formbuilder.group({
       username:this.currentUser.username,
       email:this.currentUser.email,
       password:""
     });
  }

  getUser(): User {
    return this.currentUser;
  }

  getEditing() {
    return this.editing;
  }

  enterEditing(): void{
    this.editing = !(this.editing);
  }

  get f() { return this.editForm.controls; }

  //needs validation
  onSubmit() {
    this.uService.updateUser(new User(
      this.editForm.get('username').value,
      this.editForm.get('password').value,
      this.editForm.get('email').value
    ), this.currentUser.userid)
    .subscribe (
        (data) => {
          this.alertService.success('User updated', true);
          this.currentUser.username = this.editForm.get('username').value;
          this.currentUser.email = this.editForm.get('email').value;
          this.editing = false;
      },
      error => {
          this.alertService.success(error);
          this.loading = false;
      });
  }

}
