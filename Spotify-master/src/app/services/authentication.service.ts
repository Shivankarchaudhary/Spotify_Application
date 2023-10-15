import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient:HttpClient) { }

  url="http://localhost:8084/userMusic/user/register";
  
  url1="http://localhost:9702/user/v1/login"


  isUserRegistered:boolean=false;
  isUserLogedIn:boolean=false;

  register(userData:any){
    this.isUserRegistered=true;
    return this.httpClient.post<any>(this.url, userData);
  }

  login(userData:any){
    this.isUserLogedIn=true;
    return this.httpClient.post<any>(this.url1, userData);
  }



}
