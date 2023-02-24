import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/auth/login/login.component';
import { SignupComponent } from './Components/auth/signup/signup.component';
import { HomePageComponent } from './Components/home-page/home-page.component';

const routes: Routes = [

  {path:'sign-up',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
