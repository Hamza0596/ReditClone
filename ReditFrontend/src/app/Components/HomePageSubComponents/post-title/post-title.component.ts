import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  @Input()
  posts!: any[];

  posts$: Array<any> = [];


  constructor(private postService :PostService,private router : Router) { }

  ngOnInit(): void {

    this.postService.getAllPosts().subscribe(data=>{
      console.log(data)
      this.posts$=data;

    })
  }


  goToPostDetails(id:number){
    this.router.navigate([`/viewPost`, id])
  }

}
