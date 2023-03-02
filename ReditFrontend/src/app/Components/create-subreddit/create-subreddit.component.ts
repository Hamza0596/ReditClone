import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import { SubredditService } from 'src/app/Services/subreddit.service';

@Component({
  selector: 'app-create-subreddit',
  templateUrl: './create-subreddit.component.html',
  styleUrls: ['./create-subreddit.component.css']
})
export class CreateSubredditComponent implements OnInit {

  constructor(private subredditService :SubredditService,@Inject(LOCAL_STORAGE) private storage: StorageService,private  route:Router) { }

subredditForm!:FormGroup;

  ngOnInit(): void {
    this.subredditForm=new FormGroup({
      name: new FormControl('',Validators.required),
      description:new FormControl('',Validators.required),
      user:new FormControl({
        userName:this.storage.get('username')
      })
    });
  }

  createSubreddit(){

    this.subredditService.createSubreddit(this.subredditForm.value).subscribe(data=>{
      console.log(data);
      this.subredditForm.reset()
      this.route.navigateByUrl('/listSubreddits');

      
    },error=>{
      if(error.status=403){
        this.route.navigateByUrl("/login")
      }
    })
    
  }

  discard(){
    this.subredditForm.reset()
  
  }

}
