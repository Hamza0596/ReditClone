import { Component, OnInit } from '@angular/core';
import { faArrowUp,faArrowDown,faComments } from '@fortawesome/free-solid-svg-icons';
import { PostService } from 'src/app/Services/post.service';


@Component({
  selector: 'app-post-title',
  templateUrl: './post-title.component.html',
  styleUrls: ['./post-title.component.css']
})
export class PostTitleComponent implements OnInit {

  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  faComments=faComments;
  upvoteColor!: string;
  downvoteColor!: string;

  posts$: Array<any> = [];


  constructor(private postService :PostService) { }

  ngOnInit(): void {

    this.postService.getAllPosts().subscribe(data=>{
      console.log(data)
      this.posts$=data;

    })
  }

}
