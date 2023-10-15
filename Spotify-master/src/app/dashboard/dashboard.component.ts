import { Component, OnInit} from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Song } from '../model/song';
import { AuthenticationService } from '../services/authentication.service';
import { SongService } from '../services/song.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  //  songs?:Song[] = SONGS;
  song:Array<Song>=[]


  constructor(private service:SongService,private _snackBar:MatSnackBar,private auth: AuthenticationService) { }

  ngOnInit(): void {
    this.service.getAllSongs().subscribe(data => {this.song=data;})

  }

  // getAllSongs(){
  //   this.service.getAllSongs().subscribe(data => {
  //       this.song=data;
  //       // for(let track of song){
  //       //   this.song.push(track);
  //       // }
  //     }
  //   );
  // }
  addToPlaylist(id: any) {
    if(localStorage.getItem("email")==""){
      alert("you have to login")
    //  alert(this.song[id-1].songId + ": song is being added to playlist");
    }
    else
      {this.service.addSongIntoUserPlaylist(id).subscribe()
      console.log("song Added");

      //this.fetchPlayList();
      this._snackBar.open('Congrats, you have added the song in playlist , click on Playlist to watch playlist', 'success', {​
        duration: 10000,​
         panelClass: ['mat-toolbar', 'mat-primary']​
       })
  }
    }

 url:string="";

  Play(id:any,url1:string){
    // console.log(url);
    // let audio=new Audio();
    // audio.src="C://Users//DEL//OneDrive//Desktop//shiv//Spotify-master//src//assets"+url;
    // audio.load();
    // audio.play();
if(localStorage.getItem("email")==""){
  window.alert("you have to login")
}else
{
  this.url="http://127.0.0.1:8887/"+url1;
    console.log("your song started playing")
    console.log(id);
    this._snackBar.open('SONG is playing', 'success', {​
      duration: 10000,
       panelClass: ['mat-toolbar', 'mat-primary']       ​
     })
  }
}
}
