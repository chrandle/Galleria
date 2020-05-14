import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { first } from "rxjs/operators";
import { UserService } from '../services/user.service';
import { AlertService } from '../services/alert.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
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
    this.loginForm = this.formbuilder.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;

    this.alertService.clear();

    /*
      TODO:, password validity
    */
    if (this.loginForm.invalid) {
      return;
    }


    this.loading = true;
  } 
}