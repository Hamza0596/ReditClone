import { Component, Inject, OnInit } from '@angular/core';
import { LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(@Inject(LOCAL_STORAGE) private storage: StorageService) { }

  ngOnInit(): void {
    
  }

}
