package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.SessionInfo;
import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.repository.AlbumImgRepository;
import com.genesiscreations.kdec.repository.SessionInfoRepository;
import com.genesiscreations.kdec.repository.SongInfoRepository;
import com.genesiscreations.kdec.resource.FileResource;
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
    @Autowired
    private FileResource fileResource;
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
    public void deleteSong(@RequestBody SongInfo songName) throws IOException {
        fileResource.deleteFile(songName.getSongName());
        songInfoRepository.delete(songName);
    }
}
