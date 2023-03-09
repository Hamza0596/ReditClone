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
  selectedFile!: File;
 
  imagePreview: any;

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
      file:new FormControl('')
    });
  }

  createPost(){
    const formData = new FormData();
    formData.append('posteName', this.postForm.get('posteName')?.value);
    formData.append('url', this.postForm.get('url')?.value);
    formData.append('description', this.postForm.get('description')?.value);
    formData.append('suberedditId', this.postForm.get('subereddit')?.value);
    formData.append('userName', this.storage.get('username'));
    formData.append('file', this.selectedFile);

    this.postService.addPost(formData).subscribe(data=>{
      console.log(data)
      
      this.postForm.reset()
      this.router.navigateByUrl('')
    });
    console.log(formData);
    
  }

  onFileSelected(any: any) {
    this.selectedFile = any.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result as string
    };
    reader.readAsDataURL(this.selectedFile);
  }

}
