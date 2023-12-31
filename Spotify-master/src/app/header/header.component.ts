import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private auth:AuthenticationService,private activatedRoute: Router) { }

  ngOnInit(): void {
  }
  logout()
  {
    localStorage.setItem("email","")
    if(this.auth.isUserLogedIn==false){
      this.activatedRoute.navigateByUrl("/login")
    }

  }

}
