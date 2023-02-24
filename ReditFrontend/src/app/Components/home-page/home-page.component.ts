import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/Services/post.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private postService :PostService) { }

  ngOnInit(): void {
    this.postService.getAllPosts().subscribe(data=>{
      console.log(data)
    })
  }



}
