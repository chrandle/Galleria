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
     this.editForm = this.formbuilder.group({});
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

  onSubmit(){
    
  }

}
