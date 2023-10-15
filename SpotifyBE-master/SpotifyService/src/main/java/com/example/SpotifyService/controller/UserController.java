package com.example.SpotifyService.controller;

import com.example.SpotifyService.domain.Song;
import com.example.SpotifyService.domain.User;
import com.example.SpotifyService.exception.SongAlredyFoundException;
import com.example.SpotifyService.exception.SongNotFoundException;
import com.example.SpotifyService.exception.UserAlreadyFoundException;
import com.example.SpotifyService.exception.UserNotFoundException;
import com.example.SpotifyService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/userMusic/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyFoundException {
        ResponseEntity responseEntity=null;
        try{
           user.setSongList(new ArrayList<>());
            responseEntity=new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyFoundException e){
            throw new UserAlreadyFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/registerSong")
    public ResponseEntity<?> addSong(@RequestBody Song song) throws SongAlredyFoundException {
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity<>(userService.addSong(song), HttpStatus.CREATED);
        }catch (SongAlredyFoundException e){
            throw new SongAlredyFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

//    @PostMapping("/songAdd/{email}")
//    public ResponseEntity<?> addTrackFromUser( @RequestBody Song song,@PathVariable String email)throws UserNotFoundException {
//        ResponseEntity responseEntity=null;
//        try{
//            responseEntity=new ResponseEntity<>(userService.addTrackFromUser(song, email),HttpStatus.OK);
//        }catch (UserNotFoundException e){
//            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }

    @GetMapping("/song/{email}")
    public ResponseEntity<?>getSongForUser(@PathVariable String email)throws UserNotFoundException{
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity<>(userService.getAllSongOfUser(email),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @DeleteMapping("/song/delete1/{songId}/{email}")
    public ResponseEntity<?> deleteSongFromUser(@PathVariable(value = "songId") int songId,@PathVariable(value = "email") String email)throws UserNotFoundException, SongNotFoundException {
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity<>(userService.deleteTrackFromUser(email,songId),HttpStatus.OK);
        }catch (SongNotFoundException e){
            throw new SongNotFoundException();
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/add/{email}")
    public ResponseEntity<?> saveSongOfUser(@RequestBody Song song, @PathVariable String email) throws UserNotFoundException{
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity<>(userService.saveUserSong(song,email),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/songList")
    public ResponseEntity<?>getAllSong(){
        List<Song> songList=userService.viewAllSong();
//        songList.add(new Song(1,"song name","song name","song name"));
        return new ResponseEntity<>(songList,HttpStatus.OK);
    }

    @DeleteMapping("/song/delete/{email}/{trackName}")
    public ResponseEntity<?> deleteTrackForUser(@PathVariable String email,@PathVariable int trackName){
        return  new ResponseEntity<>( userService.deleteTrackForUser(email,trackName),HttpStatus.OK);
    }

}
