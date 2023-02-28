import { Component, Input, OnInit } from '@angular/core';
import { faArrowUp,faArrowDown,faComments } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-vote-button',
  templateUrl: './vote-button.component.html',
  styleUrls: ['./vote-button.component.css']
})
export class VoteButtonComponent implements OnInit {

  @Input() post: any;


  constructor() { }
  faArrowUp = faArrowUp;
  faArrowDown = faArrowDown;
  faComments=faComments;
  upvoteColor!: string;
  downvoteColor!: string;
  ngOnInit(): void {
  }

}
