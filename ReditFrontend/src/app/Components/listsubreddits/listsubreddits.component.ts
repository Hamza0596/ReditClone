import { Component, OnInit } from '@angular/core';
import { throwError } from 'rxjs';
import { SubredditService } from 'src/app/Services/subreddit.service';

@Component({
  selector: 'app-listsubreddits',
  templateUrl: './listsubreddits.component.html',
  styleUrls: ['./listsubreddits.component.css']
})
export class ListsubredditsComponent implements OnInit {

  constructor(private subredditService :SubredditService) { }
  subreddits!: Array<any>;

  ngOnInit(): void {

    this.subredditService.getAllSubreddits().subscribe(data => {
      this.subreddits = data;
    }, error => {
      throwError(error);
    })
  }

}
