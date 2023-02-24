import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpRequestPayload } from 'src/app/Model/SignUpRequestPayload';
import { AuthServiceService } from 'src/app/Services/auth-service.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
signUpForm!: FormGroup;
signUpRequestPayload !:SignUpRequestPayload;
  constructor(private authServiceService :AuthServiceService, private  router : Router ) { }

  ngOnInit(): void {

    this.signUpForm=new FormGroup({
      userName: new FormControl('',[Validators.required] ),
      email: new FormControl('',[Validators.required,Validators.email]),
      password: new FormControl('',[Validators.required])
    })
    }

signup(){
  
  this.signUpRequestPayload=this.signUpForm.value
  this.authServiceService.Signup(this.signUpRequestPayload).subscribe(data=>{
   console.log(data);
  this.router.navigate(['/login'],{ queryParams: { registered: 'true' } })

  }, (error) => {
      if(error.status==409){
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
          title: ' email already exists  !'
        })
      }
          
    

  });

  
}

  }


