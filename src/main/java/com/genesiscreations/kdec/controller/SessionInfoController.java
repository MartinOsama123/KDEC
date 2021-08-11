package com.genesiscreations.kdec.controller;

import com.genesiscreations.kdec.model.SessionInfo;
import com.genesiscreations.kdec.repository.SessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SessionInfoController {
    @Autowired
    private SessionInfoRepository sessionInfoRepository;
    SessionInfoController(SessionInfoRepository sessionInfoRepository){
        this.sessionInfoRepository = sessionInfoRepository;
    }
    @GetMapping("/channels")
    public ResponseEntity<List<SessionInfo>> getAllChannels(){
        return ResponseEntity.ok().body(sessionInfoRepository.findAll());
    }
    @PostMapping("/channels/create")
    public SessionInfo createSession(@RequestBody SessionInfo sessionInfo){
        return sessionInfoRepository.save(sessionInfo);
    }
}
