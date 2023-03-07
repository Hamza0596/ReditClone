import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import { CommentService } from 'src/app/Services/comment.service';
import { PostService } from 'src/app/Services/post.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  name: string=this.storage.get('username');
  posts!: any[];
  comments!: any[];
  postLength!: number;
  commentLength!: number;

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService,private activatedRoute: ActivatedRoute, private postService: PostService,
    private commentService: CommentService) { }

  ngOnInit(): void {
    this.postService.getAllPostsByUser(this.name).subscribe(data => {
      this.posts = data;
      this.postLength = data.length;
    });

    this.commentService.getAllCommentsByUser(this.name).subscribe(data => {
      this.comments = data;
      this.commentLength = data.length;
    });

  }

  

}
