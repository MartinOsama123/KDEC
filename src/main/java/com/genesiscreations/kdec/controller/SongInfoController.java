package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.SessionInfo;
import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.repository.SessionInfoRepository;
import com.genesiscreations.kdec.repository.SongInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SongInfoController {
    @Autowired
    private SongInfoRepository songInfoRepository;

    @GetMapping("/songs")
    public ResponseEntity<List<SongInfo>> getAllSongs() {
        return  ResponseEntity.ok().body(songInfoRepository.findAll());
    }
    @GetMapping("/songs/{album}")
    public ResponseEntity<List<SongInfo>> getAllSongsByAlbum(@PathVariable("album") String album) {
        return  ResponseEntity.ok().body(songInfoRepository.findAllByAlbum(album));
    }
    @PostMapping("/songs/create")
    public  ResponseEntity<SongInfo> createSong(@RequestBody SongInfo songInfo){
        return  ResponseEntity.ok().body(songInfoRepository.save(songInfo));
    }
    @DeleteMapping("/songs/delete")
    public void deleteSong(@RequestBody int songName){

         songInfoRepository.deleteById(songName);
    }
}
