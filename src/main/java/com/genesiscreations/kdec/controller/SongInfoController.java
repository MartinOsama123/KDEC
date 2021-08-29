package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.SessionInfo;
import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.model.SongSearch;
import com.genesiscreations.kdec.repository.AlbumImgRepository;
import com.genesiscreations.kdec.repository.SessionInfoRepository;
import com.genesiscreations.kdec.repository.SongInfoRepository;
import com.genesiscreations.kdec.resource.FileResource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<SongSearch> getSongBy(@RequestParam("search") String search,  @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size) {
      //  Pageable currentPage = PageRequest.of(page, size);
        List<String> albums = songInfoRepository.findAllByAlbumNameIgnoreCase(search );
        List<String> songs = songInfoRepository.findAllBySongIgnoreCase(search);
        List<String> authors = songInfoRepository.findAllByAuthorIgnoreCase(search);

        return  ResponseEntity.ok().body(new SongSearch(albums,songs,authors));
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
