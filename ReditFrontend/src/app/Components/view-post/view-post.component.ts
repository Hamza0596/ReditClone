import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
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


  constructor(private postService : PostService, private activateRoute: ActivatedRoute, private commentService: CommentService) { }
  postId!: number;
  post!:any;

  ngOnInit(): void {
    this.postId=this.activateRoute.snapshot.params.id;
    this.postService.getPostById( this.postId).subscribe(data=>{
     this.post=data;
    }, error => {
      throwError(error);
    });

    this.getCommentsForPost();

  }



  public getCommentsForPost() {
    this.commentService.getAllCommentsForPost(this.postId).subscribe(data => {
      this.comments = data;
    }, error => {
      throwError(error);
    });
  }


  public postComment(){

  }

}
