import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { PlaylistComponent } from '../playlist/playlist.component';
import { AuthenticationService } from '../services/authentication.service';
import jwt_decode from 'jwt-decode';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  router: any;

  constructor(private activatedRoute: Router,private fb:FormBuilder,private _snackBar:MatSnackBar,private auth:AuthenticationService) { }

  data:any;
  holdData:any;
  holdEmail:any;


  ngOnInit(): void {
  }
  loginForm = this.fb.group({
    email: ['',Validators.required],
    password: ['',Validators.required]
   });

   get email() { return this.loginForm.get("email") }
   get password() { return this.loginForm.get("password") }


   responseData:any;

    login(){
      this.auth.login(this.loginForm.value).subscribe(
        response=>{
          console.log(response);//checking the getting response
          this.responseData=response;//initialising responseData
          console.log("token : "+this.responseData.token);//checking if we are getting token
          this.holdData=jwt_decode(this.responseData.token);

          this.holdEmail=this.holdData.sub;
          console.log(this.holdEmail)
          //storing token in browser storage
          localStorage.setItem('jwtkey',this.responseData.token);//using key and value pair
          localStorage.setItem('email',this.holdEmail);//storing user id that just registed

       if(this.auth.isUserLogedIn==true){
            this.activatedRoute.navigateByUrl("/dashboard")
          }
        },
        error=>{
          alert("Login Failed ");
        }

      );
      this._snackBar.open('you login sucessfully', 'success', {​
        duration: 5000,​
         panelClass: ['mat-toolbar', 'mat-primary']​
       })
    }
    }









