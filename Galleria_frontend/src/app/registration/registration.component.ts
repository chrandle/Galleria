import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { first } from "rxjs/operators";
import { UserService } from '../services/user.service';
import { User } from '../classes/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  loading = false;
  submitted = false;
  message: any;
  constructor(
    private formbuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private uService: UserService
    /*
        TODO:
          Authentication
          Alerts
     */
  ) {

   }

  ngOnInit(): void {
    this.registrationForm = this.formbuilder.group({
      username: ['',Validators.required],
      email: ['',Validators.required],
      password: ['',Validators.required]
      // confirm: ['',Validators.required]
    });
  }

  get f() { return this.registrationForm.controls; }

  onSubmit() {
    this.submitted = true;

    /*
      TODO: add messaging, password validity
    */
    if (this.registrationForm.invalid){
      return;
    }

    // console.log(new User(
    //   this.registrationForm.get('username').value,
    //   this.registrationForm.get('password').value,
    //   this.registrationForm.get('email').value)
    // );
    // this.loading = true;
    let resp = this.uService.newUser(new User(
      this.registrationForm.get('username').value,
      this.registrationForm.get('password').value,
      this.registrationForm.get('email').value
    ));
    resp.subscribe((data)=>this.message=data);
    console.log((this.message));

  }
}
