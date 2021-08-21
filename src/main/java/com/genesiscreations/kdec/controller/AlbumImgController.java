package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.AlbumImg;
import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.repository.AlbumImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
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
    @GetMapping("img/{filename}")
    public ResponseEntity<Resource> downloadFile( @PathVariable("filename") String filename) throws IOException {

        Path filePath = get(PARENT).toAbsolutePath().normalize().resolve(filename);
        System.out.println(filePath);
        if (!Files.exists(filePath)) throw new FileNotFoundException(filename + "was not found");
        Resource resource = new UrlResource(filePath.toUri());
        System.out.println(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath))).headers(httpHeaders).body(resource);
    }
    public void deleteFile(String fileName) throws IOException {
        Path fileToDeletePath = Paths.get(PARENT + fileName);
        Files.delete(fileToDeletePath);
    }
}
