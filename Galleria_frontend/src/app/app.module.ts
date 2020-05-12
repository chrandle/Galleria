import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule }  from '@angular/platform-browser/animations';
import {MatGridListModule} from '@angular/material/grid-list';

import { AppComponent } from './app.component';
import { ThumbnailsComponent } from './thumbnails/thumbnails.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { CarouselComponent } from './carousel/carousel.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RoutingModule } from './routing.module';
import { ImagedetailsComponent } from './imagedetails/imagedetails.component';


@NgModule({
  declarations: [
    AppComponent,
    ThumbnailsComponent,
    LoginComponent,
    ProfileComponent,
    CarouselComponent,
    NavbarComponent,
    ImagedetailsComponent,
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    BrowserAnimationsModule,
    MatGridListModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
