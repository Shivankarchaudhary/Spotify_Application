package com.example.SpotifyService.repository;

import com.example.SpotifyService.domain.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song,Integer> {
}
