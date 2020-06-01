import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule }  from '@angular/platform-browser/animations';
import {MatGridListModule} from '@angular/material/grid-list';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';
import { ThumbnailsComponent } from './thumbnails/thumbnails.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { CarouselComponent } from './carousel/carousel.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RoutingModule } from './routing.module';
import { ImagedetailsComponent } from './imagedetails/imagedetails.component';
import { RegistrationComponent } from './registration/registration.component';
import { UserService } from "./services/user.service";
import { AlertComponent } from './alert/alert.component';

@NgModule({
  declarations: [
    AppComponent,
    ThumbnailsComponent,
    LoginComponent,
    ProfileComponent,
    CarouselComponent,
    NavbarComponent,
    ImagedetailsComponent,
    RegistrationComponent,
    AlertComponent,
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatGridListModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
