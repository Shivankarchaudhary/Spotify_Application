import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators,AbstractControl} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Song } from '../model/song';
import { AuthenticationService } from '../services/authentication.service';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private route: Router,private fb:FormBuilder,private _snackBar:MatSnackBar,private auth:AuthenticationService) { }

  songs:Song[]=[];
  data:any;

  ngOnInit(): void {
  }
  registrationForm = this.fb.group({
    fullName: ['',Validators.required],
    email: ['',[Validators.required,Validators.email]],
    password: ['',[Validators.required,Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    confirmPassword: ['',[Validators.required,Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    phoneNumber: ['',[Validators.required,Validators.pattern(/^[789]\d{9,9}$/)]],
    address: ['',Validators.required]

  });
  get fullName(){ return this.registrationForm.get("fullName")}
  get email() { return this.registrationForm.get("email") }
  get password() { return this.registrationForm.get("password") }
  get confirmPassword() { return this.registrationForm.get("confirmPassword") }
  get phoneNumber() { return this.registrationForm.get("phoneNumber") }
  get address(){return this.registrationForm.get("address")}

  mustMatchValidator(fg: AbstractControl) {
    const passwordValue = fg.get("password")?.value;
    const confirmPasswordValue = fg.get("confirmPassword")?.value;
    if (!passwordValue || !confirmPasswordValue) {
      return null;
    }
    if (passwordValue != confirmPasswordValue) {
        return { mustMatch: false}
    }
    return null;
  }



  onSubmit(){
    // this.noteService.getNote().subscribe({
    //   next:data=>{
    //     this.notes=data
    //   }
    // })

    console.log(this.registrationForm.value);
    this.auth.register(this.registrationForm.value).subscribe({
      next:responce=>{
        this.data=responce
        console.log(responce);
        //alert("registered successfully")
        this.registrationForm.reset();
        if (this.auth.isUserRegistered == true) {
          this.route.navigateByUrl("/login")
        }
      }
    })

    this._snackBar.open('Congrats, you have submitted the form!!', 'success', {​
      duration: 5000,​
       panelClass: ['mat-toolbar', 'mat-primary']​
     })
  }

}
