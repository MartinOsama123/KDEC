package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.AlbumImg;
import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.repository.AlbumImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping("/api")
public class AlbumImgController {
    public static final String PARENT = System.getProperty("user.dir") + "/img/";
    @Autowired
    private AlbumImgRepository albumImgRepository;
    @GetMapping("/albums")
    public ResponseEntity<List<AlbumImg>> getAllSongs() {
        return  ResponseEntity.ok().body(albumImgRepository.findAll());
    }
    @PostMapping("/upload/img/{album}")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("image") List<MultipartFile> multipartFileList,@PathVariable("album") String album) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : multipartFileList) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(PARENT, fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            fileNames.add(fileName);
        }
        albumImgRepository.save(new AlbumImg(album,fileNames.get(0)));
        return ResponseEntity.ok().body(fileNames);
    }
    @DeleteMapping("/album/delete")
    public void deleteAlbum(@RequestBody String albumImg) throws IOException {
    deleteFile(albumImgRepository.findById(albumImg).get().getImgPath());
       albumImgRepository.deleteById(albumImg);
    }
    public void deleteFile(String fileName) throws IOException {
        Path fileToDeletePath = Paths.get(PARENT + fileName);
        Files.delete(fileToDeletePath);
    }
}
