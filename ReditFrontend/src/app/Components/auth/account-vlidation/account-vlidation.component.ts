import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthServiceService } from 'src/app/Services/auth-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-account-vlidation',
  templateUrl: './account-vlidation.component.html',
  styleUrls: ['./account-vlidation.component.css']
})
export class AccountVlidationComponent implements OnInit {
  token!:string;

  constructor(private activatedRouter: ActivatedRoute , private authService :AuthServiceService , private router : Router) { }

  ngOnInit(): void {
    this.router.navigateByUrl("/login");
  this.token= this.activatedRouter.snapshot.params.token;
  this.authService.validatAccount(this.token).subscribe(data=>{
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 1500,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })

    Toast.fire({
      icon: 'success',
      title: ' your account has been successfully validated !'
    })


  },(error)=>{
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 1500,
      timerProgressBar: true,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    })

    Toast.fire({
      icon: 'error',
      title: ' noooo !'
    })
  }
  )
  }

  
  

}
