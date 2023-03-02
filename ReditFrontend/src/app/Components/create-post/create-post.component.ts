import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import { PostService } from 'src/app/Services/post.service';
import { SubredditService } from 'src/app/Services/subreddit.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  postForm!:FormGroup;
  subredditsList!:any;

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService, private subredditService :SubredditService, private postService:PostService, private router :Router ) { }

  ngOnInit(): void {
    this.subredditService.getAllSubreddits().subscribe(data=>{
    this.subredditsList=data;
    })

    this.postForm=new FormGroup({
      posteName: new FormControl('',Validators.required),
      url: new FormControl('',Validators.required),
      description:new FormControl('',Validators.required),
      subereddit:new FormControl('',Validators.required),
      user:new FormControl({userName:this.storage.get('username')},Validators.required),
    });
  }

  createPost(){

  const subredditValue = this.postForm.get('subereddit')?.value;
  const userValue = this.postForm.get('user')?.value;
  const valueToSubmit = {
    posteName: this.postForm.get('posteName')?.value,
    url: this.postForm.get('url')?.value,
    description: this.postForm.get('description')?.value,
    subereddit: { id: subredditValue },
    user: userValue
  }
    
    this.postService.addPost(valueToSubmit).subscribe(data=>{
      console.log(data)
      
      this.postForm.reset()
      this.router.navigateByUrl('')
    });
    console.log(valueToSubmit);
    
  }

}
