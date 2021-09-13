package com.genesiscreations.kdec.resource;

import com.genesiscreations.kdec.model.SongInfo;
import com.genesiscreations.kdec.repository.SongInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@RestController
@RequestMapping("/church")
public class FileResource {
    public static final String PARENT = System.getProperty("user.dir") + "/mp3/";
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFileList) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : multipartFileList) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            System.out.println("1");
            Path fileStorage = get(PARENT, fileName).toAbsolutePath().normalize();
            System.out.println("2");
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            System.out.println("3");
            fileNames.add(fileName);
        }
        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("mp3/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) throws IOException {
        filename = java.net.URLDecoder.decode(filename, StandardCharsets.UTF_8);
        System.out.println(filename);
        Path filePath = get(PARENT).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(filePath)) throw new FileNotFoundException(filename + "was not found");
        Resource resource = new UrlResource(filePath.toUri());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath))).headers(httpHeaders).body(resource);
    }

    public void deleteFile(String fileName) throws IOException {


    Path fileToDeletePath = Paths.get(PARENT + fileName);

    Files.delete(fileToDeletePath);
}
    @GetMapping("albums")
    public ResponseEntity<List<String>> getAlbums() throws IOException {
        Path filePath = get(PARENT).toAbsolutePath().normalize();
        Resource dir = new UrlResource(filePath.toUri());
        List<String> fileNames = new ArrayList<>();
        for (File file : Objects.requireNonNull(dir.getFile().listFiles())) {
            String name = file.getName();
            fileNames.add(name);
        }
        return ResponseEntity.ok().body(fileNames);
    }
    @GetMapping("{album}/songs")
    public ResponseEntity<List<String>> getSongs(@PathVariable("album") String album) throws IOException {
        Path filePath = get(PARENT).toAbsolutePath().normalize().resolve(album);
        Resource dir = new UrlResource(filePath.toUri());
        List<String> fileNames = new ArrayList<>();
        for (File file : Objects.requireNonNull(dir.getFile().listFiles())) {
            String name = file.getName();
            fileNames.add(name);
        }
        return ResponseEntity.ok().body(fileNames);
    }
}
