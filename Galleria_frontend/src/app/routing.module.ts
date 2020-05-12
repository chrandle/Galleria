import { NgModule } from '@angular/core';
import { RouterModule,Routes } from "@angular/router";
import { CarouselComponent} from "./carousel/carousel.component"
import { LoginComponent } from "./login/login.component";
import { ProfileComponent } from "./profile/profile.component";

const routes:Routes = [
  {path:'', component:CarouselComponent },
  {path:'home', component:CarouselComponent },
  {path:'carousel',component: CarouselComponent },
  {path:'profile',component: ProfileComponent },
  {path:'login', component: LoginComponent },
]


@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }
