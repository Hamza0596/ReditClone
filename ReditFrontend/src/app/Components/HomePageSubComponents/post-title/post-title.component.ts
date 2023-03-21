import { Component, Inject, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faArrowUp,faArrowDown,faComments } from '@fortawesome/free-solid-svg-icons';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
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
  actualPageNumber:number=0;
  pageNumber!:number;
  indexTable: number[] = new Array(0);
  posts$: Array<any> = [];
  filterValue!:string;
  connectedUserName!:String;

  @Input()
  posts!: any[];
  


  constructor(private postService :PostService,private router : Router , @Inject(LOCAL_STORAGE) private storage: StorageService) { }

  ngOnInit(): void {
    this.connectedUserName=this.storage.get('username')

    this.postService.getAllPosts(0).subscribe(data=>{
      console.log(data)
      this.posts$=data.content;
      this.pageNumber=data.totalPages;
      console.log(this.pageNumber);
      this.indexTable.length=this.pageNumber;
      

    })
    
  }


  goToPostDetails(id:number){
    this.router.navigate([`/viewPost`, id])
  }

  getPostsByPage( page:number){
    this.postService.getAllPosts(page).subscribe(data=>{
      this.posts$=data.content;
      this.actualPageNumber=data.pageable.pageNumber;
    })

  }

  scrollToTop() {
    document.documentElement.scrollTop = 0;
    
  }

  searchPosts( page:number){
   if(this.filterValue){
    this.postService.searchPosts(this.filterValue,page).subscribe(data=>{
      this.posts$=data.content;
    })
   }
   else{
    this.getPostsByPage(0);
   }
   

  }
  deletPost(id:number){
    this.postService.deletePostById(id).subscribe(data=>{
      this.getPostsByPage(0);

    })
  }


}
