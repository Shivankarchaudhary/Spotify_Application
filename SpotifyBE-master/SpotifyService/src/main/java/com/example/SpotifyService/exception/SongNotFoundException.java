package com.example.SpotifyService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "song not found")
public class SongNotFoundException extends Exception{
}
