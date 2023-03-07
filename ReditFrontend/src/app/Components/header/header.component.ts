import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import { AuthServiceService } from 'src/app/Services/auth-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService,private authService: AuthServiceService, private router: Router) { }



  isLoggedIn !: boolean;
  isLoggedInFirstCheck:boolean=false;
  username!: string;


  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();
    this.authService.reciveMessage().subscribe(data=>{
      this.isLoggedInFirstCheck=data;

    });
  }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username);
  }

  logout(){
    this.authService.logout();
    this.authService.sendMessage(false);
  }

}
