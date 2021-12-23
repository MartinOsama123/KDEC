package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.repository.SongInfoRepository;
import com.genesiscreations.kdec.resource.FileResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
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
    @GetMapping("/songs/all")
    public ResponseEntity<List<SongInfo>> getAllSongs() {
        return new ResponseEntity<>(songInfoRepository.findAll(), HttpStatus.OK);

    }

    @GetMapping("/songs/{album}")
    public ResponseEntity<List<SongInfo>> getAllSongsByAlbum(@PathVariable("album") String album) {
        return  ResponseEntity.ok().body(songInfoRepository.findAllByAlbumIgnoreCase(album));
    }
    @GetMapping("/songs")
    public ResponseEntity<List<SongInfo>> getSongBy(@RequestParam("search") String search,  @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size) {
      //  Pageable currentPage = PageRequest.of(page, size);

        return  ResponseEntity.ok().body(songInfoRepository.findAllByAuthorOrAlbumOrSongIgnoreCase(search));
    }
    @PostMapping("/songs/create")
    public  ResponseEntity<SongInfo> createSong(@RequestBody SongInfo songInfo){
        songInfo.getSongName().replace(".mp3","");
        return  ResponseEntity.ok().body(songInfoRepository.save(songInfo));
    }
    @PostMapping("/songs/{name}")
    public  ResponseEntity<SongInfo> viewSong(@PathVariable("name") String name){
        SongInfo songInfo = songInfoRepository.findBySongInfo(name);
        songInfo.incrementView();
        return  ResponseEntity.ok().body(songInfoRepository.save(songInfo));
    }
    @DeleteMapping("/songs/delete")
    public void deleteSong(@RequestBody SongInfo songName) throws IOException {
       // fileResource.deleteFile(songName.getSongName()+".mp3");
        songInfoRepository.delete(songName);
    }
}
