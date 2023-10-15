package com.example.SpotifyService.service;

import com.example.SpotifyService.domain.Song;
import com.example.SpotifyService.domain.User;
import com.example.SpotifyService.exception.SongAlredyFoundException;
import com.example.SpotifyService.exception.SongNotFoundException;
import com.example.SpotifyService.exception.UserAlreadyFoundException;
import com.example.SpotifyService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User addUser(User user)throws UserAlreadyFoundException;

    public Song addSong(Song song) throws SongAlredyFoundException;
  //  public User addTrackFromUser(Song song, String email) throws UserNotFoundException;
    List<Song> getAllSongOfUser(String email) throws UserNotFoundException;
    public User deleteTrackFromUser(String email,int songId) throws UserNotFoundException, SongNotFoundException;

    public User saveUserSong(Song song, String email) throws UserNotFoundException;

    List<Song> viewAllSong();

    public boolean deleteTrackForUser(String email,int song);
}
