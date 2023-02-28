import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Components/header/header.component';
import { SignupComponent } from './Components/auth/signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './Components/auth/login/login.component';
import { HomePageComponent } from './Components/home-page/home-page.component';
import { TokenInterceptor } from './Helpers/TokenInterceptor';
import { FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { PostTitleComponent } from './Components/HomePageSubComponents/post-title/post-title.component';
import { VoteButtonComponent } from './Components/HomePageSubComponents/vote-button/vote-button.component';
import { SideBarComponent } from './Components/HomePageSubComponents/side-bar/side-bar.component';
import { SubredditSideBarComponent } from './Components/HomePageSubComponents/subreddit-side-bar/subreddit-side-bar.component'





@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomePageComponent,
    PostTitleComponent,
    VoteButtonComponent,
    SideBarComponent,
    SubredditSideBarComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FontAwesomeModule,
   
  




    
    
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
