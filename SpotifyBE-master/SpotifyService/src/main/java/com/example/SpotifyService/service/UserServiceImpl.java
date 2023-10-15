package com.example.SpotifyService.service;

import com.example.SpotifyService.domain.Song;
import com.example.SpotifyService.domain.User;
import com.example.SpotifyService.exception.SongAlredyFoundException;
import com.example.SpotifyService.exception.SongNotFoundException;
import com.example.SpotifyService.exception.UserAlreadyFoundException;
import com.example.SpotifyService.exception.UserNotFoundException;
import com.example.SpotifyService.proxy.UserProxy;
import com.example.SpotifyService.repository.SongRepository;
import com.example.SpotifyService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private UserProxy userProxy;
    public UserServiceImpl(UserRepository userRepository,SongRepository songRepository,UserProxy userProxy){
        this.userProxy=userProxy;
        this.userRepository=userRepository;
        this.songRepository=songRepository;
    }
    @Override
    public User addUser(User user) throws UserAlreadyFoundException {
        if(userRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyFoundException();
        }
        User user1 = userRepository.save(user);
        if(!(user1.getEmail().isEmpty())){
            ResponseEntity responseEntity = userProxy.saveUser(user);
            System.out.println(responseEntity.getBody());
        }
        return user1;

    }

    @Override
    public Song addSong(Song song)throws SongAlredyFoundException {
        if(songRepository.findById(song.getSongId()).isPresent()){
           throw new SongAlredyFoundException();
        }
        return songRepository.save(song);
    }

//    @Override
//    public User addTrackFromUser(Song song, String email) throws UserNotFoundException {
//        if(userRepository.findById(email).isEmpty()){
//            throw new UserNotFoundException();
//        }
//        User user=userRepository.findByEmail(email);
//        if(user.getSongList()==null){
//            user.setSongList(Arrays.asList(song));
//        }else {
//            List<Song> songs=user.getSongList();
//            songs.add(song);
//            user.setSongList(songs);
//        }
//        return userRepository.save(user);
//    }

    @Override
    public List<Song> getAllSongOfUser(String email) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(email).get().getSongList();
    }

    @Override
    public User deleteTrackFromUser(String email, int songId) throws UserNotFoundException, SongNotFoundException {
        boolean result=false;
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user=userRepository.findById(email).get();
        List<Song> songs=user.getSongList();
        result=songs.removeIf(x->x.getSongId()==songId);
        if(!result){
            throw new SongNotFoundException();
        }
        user.setSongList(songs);
        return userRepository.save(user);
    }

    @Override
    public User saveUserSong(Song song, String email) throws UserNotFoundException {
        if (userRepository.findById(email).isEmpty()) {
            throw new  UserNotFoundException();
        }
        // user is authorized and getting customer object from customer id
        User userObj = userRepository.findById(email).get();
        //null describes 0 song
        if (userObj == null) {
            userObj.setSongList(Arrays.asList(song));
        } else {
            //create a list of song and adding track object
            List<Song> songList = userObj.getSongList();
            songList.add(song);
            userObj.setSongList(songList);
        }
        return userRepository.save(userObj);
    }

    @Override
    public List<Song> viewAllSong() {
        return songRepository.findAll();
    }

    @Override
    public boolean deleteTrackForUser(String email,int song) {
        User user = userRepository.findById(email).get();
        List<Song> tracks = user.getSongList();
        for(int i=0;i< tracks.size();i++){
            if(tracks.get(i).getSongId()==song){
                tracks.remove(tracks.get(i));

            }user.setSongList(tracks);
            userRepository.save(user);
        }
        return true;
    }


}
