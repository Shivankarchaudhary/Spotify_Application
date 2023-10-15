import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Song } from '../model/song';
import { SongService } from '../services/song.service';


@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {

  constructor(private httpClient:HttpClient,private service:SongService,private _snackBar:MatSnackBar) { }

  song:Array<Song>=[]
  ngOnInit(): void {
    this.fetchPlayList();
  }


  player(songName: any) {
  }

  getAllSongs(id: any) {

  this.service.getAllSongs().subscribe(data => {this.song=data;})
  }

  addToPlaylist(id: any) {
    // if(localStorage.getItem("email")=="chaudharyshivankar1999@gmail.com"){
    //   console.log("dddd");
    //   alert("you already added this song in playlist")
    // }
    //   else
    //  alert(this.song[id-1].songId + ": song is being added to playlist");
      {
        this.service.addSongIntoUserPlaylist(id).subscribe()
       console.log("song Added");
      alert("song added");
      //this.fetchPlayList();

    }
    }


  playList: Array<Song> = [];

  fetchPlayList() {
    // this.service.fetchPlayList(localStorage.getItem('email')).subscribe(Song =>
    //   this.playList.length = 0;
    //   for(let track of this.song){
    //     this.playList.push(track);
    //   }
    // )
      this.service.fetchPlayList().subscribe(data =>{
      console.log(data);
      this.song=data})
      console.log("array data" + this.playList);
      for(let track of this.song){
            this.playList.push(track);
         }
  }

  deletesong(sid:any){

    this.service.deleteSongPlaylist(sid).subscribe()
      console.log("song is deleted");
      alert("song is deleted");
      window.location.reload();

  }

  Play(){
    console.log("your song started playing")
    this._snackBar.open('SONG is playing', 'success', {​
      duration: 5000,​
       panelClass: ['mat-toolbar', 'mat-primary']​
     })
  }



}
