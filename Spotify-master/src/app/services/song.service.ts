import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import {Song} from "../model/song";

@Injectable({
  providedIn: 'root'
})
export class SongService {

  constructor(private httpClient :HttpClient) { }

  songUrl="http://localhost:9003/userMusic/user/songList"
  regUrl="http://localhost:8084/userMusic/user";

  getAllSongs(){
    return this.httpClient.get<Song[]>(this.songUrl);
  }

  // registerSameUserInSpotify(userObj:any){
  //   return this.httpClient.post(this.regUrl+"/register", userObj)
  // }

  addSongIntoUserPlaylist(userSongObj:any){
   console.log("well done")
    console.log(localStorage.getItem('jwtkey'))
    console.log(localStorage.getItem('email'))

    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer ' +localStorage.getItem('jwtkey')
     });
    console.log(userSongObj)
     let requestToken={ headers : httpHeaders }
    return this.httpClient.post(this.regUrl+"/add/"+localStorage.getItem('email') , userSongObj,requestToken);
  }

  // addSongToPlaylist(){
  //   return this.httpClient.get(this.regUrl+"/add/"+localStorage.getItem('email'));
  // }

  fetchPlayList(){
    console.log("email")
    console.log(localStorage.getItem('email'))
   return this.httpClient.get<Song[]>(this.regUrl+"/song/"+localStorage.getItem('email'));
  };

  deleteSongPlaylist(songid:any){
    console.log("email")
    console.log(localStorage.getItem('email'))
    return this.httpClient.delete<Song[]>(this.regUrl+"/song/delete/"+localStorage.getItem('email')+"/"+songid);
  }

}
