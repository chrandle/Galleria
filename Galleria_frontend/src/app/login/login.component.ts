import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { first } from "rxjs/operators";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = true;
  returnUrl: string;

  constructor(
    private formbuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    /*
        TODO:
          Authentication
          Alerts
     */
  ) {
      //authentication call<?>
   }

  ngOnInit(): void {
    this.loginForm = this.formbuilder.group({
      username: ['',Validators.required],
      password: ['',Validators.required],
      confirm: ['',Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['returnUrl']||'/';
  }

  get f() { return this.loginForm.controls; }

  onSubmit(){
    this.submitted = true;

    if (this.loginForm.invalid){
      return;
    }

    this.loading = true;

    
  }

}
