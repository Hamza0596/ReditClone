import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import { throwError } from 'rxjs';
import { CommentService } from 'src/app/Services/comment.service';
import { PostService } from 'src/app/Services/post.service';

@Component({
  selector: 'app-view-post',
  templateUrl: './view-post.component.html',
  styleUrls: ['./view-post.component.css']
})
export class ViewPostComponent implements OnInit {
  comments!: any;
  commentForm!:FormGroup;


  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService,private postService : PostService, private activateRoute: ActivatedRoute, private commentService: CommentService) { }
  postId!: number;
  post!:any;

  ngOnInit(): void {

    this.commentForm=new FormGroup({
      text: new FormControl(''),
    
    })



    this.postId=this.activateRoute.snapshot.params.id;
    this.postService.getPostById( this.postId).subscribe(data=>{
     this.post=data;
     this.getCommentsForPost();

    }, error => {
      throwError(error);
    });


  }



  public getCommentsForPost() {
    this.commentService.getAllCommentsForPost(this.postId).subscribe(data => {
      this.comments = data;
    }, error => {
      throwError(error);
    });
  }


  public postComment(){

  const comment={
    text : this.commentForm.get('text')?.value,
    user:{userName:this.storage.get('username')},
    post:{postId:this.postId}
  }

    this.commentService.postComment(comment).subscribe(data => {
      this.commentForm.reset()
      this.getCommentsForPost();
    }, error => {
      throwError(error);
    })

  }

}
