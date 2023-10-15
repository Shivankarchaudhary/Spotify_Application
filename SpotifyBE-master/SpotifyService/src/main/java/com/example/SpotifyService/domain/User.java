package com.example.SpotifyService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String fullName;
    @Id
    private String email;
    private String password;
    private long phoneNumber;
    private String address;
    private List<Song> songList;


}
