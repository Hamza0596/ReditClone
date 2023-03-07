import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Loginrequestpayload } from 'src/app/Model/Loginrequestpayload';
import { LoginResponse } from 'src/app/Model/LoginResponse';
import { AuthServiceService } from 'src/app/Services/auth-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authServiceService:AuthServiceService,private activatedRoute: ActivatedRoute,private route :Router) { }
  loginForm!:FormGroup;
  loginrequestpayload!:Loginrequestpayload;
  loginResponse!: LoginResponse;
  isError:boolean=false;

  ngOnInit(): void {
    this.loginForm=new FormGroup({
      userName:new FormControl('',Validators.required),
      password:new FormControl('',[Validators.required])
    })



    this.activatedRoute.queryParams.subscribe(params=>{

      if(params.registered !==undefined && params.registered=='true'){
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
          title: ' Signup Successful !'
        })
        
      }
     
    })
  }

  login(){
    this.authServiceService.login(this.loginForm.value).subscribe(data=>{
      this.isError=false;
      this.route.navigateByUrl('');
      console.log(data);
      this.authServiceService.sendMessage(true);
    
    },(error)=>{

     this.isError=true;

    }
    )}

}
