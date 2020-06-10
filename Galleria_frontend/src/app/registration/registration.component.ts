import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { first } from 'rxjs/operators';
import { UserService } from '../services/user.service';
import { User } from '../classes/user';
import { AlertService } from '../services/alert.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formbuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private uService: UserService,
    private alertService: AlertService
    /*
        TODO:
          Authentication
          Alerts
     */
  ) {

   }

  ngOnInit(): void {
    this.registrationForm = this.formbuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      // password: ['', [Validators.required, Validators.minLength(8)]]
      password: ['', Validators.required],
    });
  }

  get f() { return this.registrationForm.controls; }

  onSubmit() {
    this.submitted = true;

    this.alertService.clear();

    /*
      TODO:, password validity
    */
    if (this.registrationForm.invalid){
      return;
    }

    // console.log(new User(
    //   this.registrationForm.get('username').value,
    //   this.registrationForm.get('password').value,
    //   this.registrationForm.get('email').value)
    // );
    this.loading = true;

    this.uService.newUser(new User(
      this.registrationForm.get('username').value,
      this.registrationForm.get('password').value,
      this.registrationForm.get('email').value
    ))
      .subscribe (
        (data) => {
          this.alertService.success('New User Registered', true);
          this.router.navigate(['home']);
      },
      error => {
          this.alertService.success(error);
          this.loading = false;
      });
  }
}
