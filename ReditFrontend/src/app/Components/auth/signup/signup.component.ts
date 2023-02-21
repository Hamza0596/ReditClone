import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SignUpRequestPayload } from 'src/app/Model/SignUpRequestPayload';
import { AuthServiceService } from 'src/app/Services/auth-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
signUpForm!: FormGroup;
signUpRequestPayload !:SignUpRequestPayload;
  constructor(private authServiceService :AuthServiceService) { }

  ngOnInit(): void {

    this.signUpForm=new FormGroup({
      userName: new FormControl('',[Validators.required] ),
      email: new FormControl('',[Validators.required,Validators.email]),
      password: new FormControl('',[Validators.required])
    })
    }

signup(){
  
  this.signUpRequestPayload=this.signUpForm.value
  console.log(this.signUpRequestPayload);

  this.authServiceService.Signup(this.signUpRequestPayload).subscribe();

  
}

  }


