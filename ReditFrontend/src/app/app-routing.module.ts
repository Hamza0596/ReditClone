import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/auth/login/login.component';
import { SignupComponent } from './Components/auth/signup/signup.component';
import { UserProfileComponent } from './Components/auth/user-profile/user-profile.component';
import { CreatePostComponent } from './Components/create-post/create-post.component';
import { CreateSubredditComponent } from './Components/create-subreddit/create-subreddit.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { ListsubredditsComponent } from './Components/listsubreddits/listsubreddits.component';
import { ViewPostComponent } from './Components/view-post/view-post.component';
import { AuthGuard } from './Helpers/auth.guard';

const routes: Routes = [
  {path:'',component:HomePageComponent},
  {path:'sign-up',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:"creat_post",component:CreatePostComponent, canActivate: [AuthGuard]},
  {path:"creat_subreddit",component:CreateSubredditComponent, canActivate: [AuthGuard]},
  {path:"listSubreddits",component:ListsubredditsComponent, canActivate: [AuthGuard]},
  {path:"viewPost/:id",component:ViewPostComponent, canActivate: [AuthGuard]},
  {path:"userProfile",component:UserProfileComponent, canActivate: [AuthGuard]}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
