import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Components/auth/login/login.component';
import { SignupComponent } from './Components/auth/signup/signup.component';
import { CreatePostComponent } from './Components/create-post/create-post.component';
import { CreateSubredditComponent } from './Components/create-subreddit/create-subreddit.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { ListsubredditsComponent } from './Components/listsubreddits/listsubreddits.component';
import { ViewPostComponent } from './Components/view-post/view-post.component';

const routes: Routes = [
  {path:'',component:HomePageComponent},
  {path:'sign-up',component:SignupComponent},
  {path:'login',component:LoginComponent},
  {path:"creat_post",component:CreatePostComponent},
  {path:"creat_subreddit",component:CreateSubredditComponent},
  {path:"listSubreddits",component:ListsubredditsComponent},
  {path:"viewPost/:id",component:ViewPostComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
