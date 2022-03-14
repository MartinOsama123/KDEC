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
import java.util.Locale;

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
    @GetMapping("/albums/{name}")
    public ResponseEntity<AlbumImg> getAlbumInfo(@PathVariable ("name") String name) {
        return  albumImgRepository.findById(name).map(albumImg -> ResponseEntity.ok().body(albumImg)).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/albums/category/{name}")
    public ResponseEntity<List<AlbumImg>> getCategoryAlbums(@PathVariable ("name") String name) {
        return   ResponseEntity.ok().body(albumImgRepository.findByCategoryName(name));
    }
    @PostMapping("/update/category/{name}")
    public ResponseEntity<List<AlbumImg>> sortCategory(@PathVariable ("name") String name,@RequestBody List<AlbumImg> albumImg) {
       List<AlbumImg> temp = albumImgRepository.findByCategoryName(name);
       for(int i = 0;i<temp.size();i++){
           albumImgRepository.deleteById(temp.get(i).getAlbumName());
       }
        return ResponseEntity.ok().body(albumImgRepository.saveAll(albumImg));
    }
    @PostMapping("/upload/img")
    public ResponseEntity<AlbumImg> uploadFiles(@RequestBody AlbumImg albumImg) {
        return ResponseEntity.ok().body(albumImgRepository.save(albumImg));
    }
    @PostMapping("/upload/img/session/{album}")
    public ResponseEntity<List<String>> sessionPhoto(@RequestParam("image") List<MultipartFile> multipartFileList,@PathVariable("album") String album) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : multipartFileList) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            fileName = fileName.toLowerCase().replaceAll(fileName,album);
            Path fileStorage = get(PARENT, fileName).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            fileNames.add(fileName);
        }
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
