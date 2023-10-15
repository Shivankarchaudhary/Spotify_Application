package com.example.SpotifyService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    private int songId;
    private String songName;
    private String artistName;
    private String genre;
    private String imageurl;
    private String songurl;



}
