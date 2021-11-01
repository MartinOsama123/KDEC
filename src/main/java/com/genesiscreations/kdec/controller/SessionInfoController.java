package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.SessionInfo;
import com.genesiscreations.kdec.repository.SessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;

@RestController
@RequestMapping("/api")
public class SessionInfoController {
    public static final String PARENT = System.getProperty("user.dir") + "/img/";
    @Autowired
    private SessionInfoRepository sessionInfoRepository;

    @GetMapping("/channels")
    public ResponseEntity<List<SessionInfo>> getAllChannels() throws IOException {
      /*   final String BASE_URL = "https://api.agora.io";
        final String USER = "d0f040453156454ca8cf3fbbd6c67f2e:cc9d0dfbed654b9a942d80243aabc546";
        final String APP_ID = "343e0fece606410eb65bc1b9a877b65e";
        URL url = new URL(BASE_URL+"/dev/v1/channel/"+APP_ID);
        String temp = Base64.getEncoder()
                .encodeToString(USER.getBytes(StandardCharsets.UTF_8.toString()));
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Authorization", "Basic "+temp);
        Scanner scanner = new Scanner(http.getInputStream());
        String response = scanner.useDelimiter("\\Z").next();
        scanner.close();
        http.disconnect();
        Gson g = new Gson();
        AgoraChannel channel = g.fromJson(response, AgoraChannel.class);
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            for(SessionInfo s1 : sessionInfoRepository.findAll()){
                boolean found = false;
                for(AgoraChannel.Channel s : channel.getData().getChannels()){
                if(s.getChannelName().equals(s1.getChannelName())){ found = true;
                    System.out.println(s1.getChannelName());
                }
            }
                if(!found){ sessionInfoRepository.delete(s1);}
        }*/
        return ResponseEntity.ok().body(sessionInfoRepository.findAll());
    }
    @PostMapping("/channels/create")
    public SessionInfo createSession(@RequestBody SessionInfo sessionInfo){
        return sessionInfoRepository.save(sessionInfo);
    }
    @DeleteMapping("/channels/delete")
    public ResponseEntity<String> deleteSession(@RequestBody SessionInfo sessionInfo){
         sessionInfoRepository.delete(sessionInfo);
         return ResponseEntity.ok().build();
    }
    @PostMapping("/channels/set")
    public List<SessionInfo> setAll(@RequestBody List<SessionInfo> sessionInfo){
        sessionInfoRepository.deleteAll();
         return sessionInfoRepository.saveAll(sessionInfo);
    }
    public void deleteFile(String fileName)  {
        Path fileToDeletePath = Paths.get(PARENT + fileName);
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
