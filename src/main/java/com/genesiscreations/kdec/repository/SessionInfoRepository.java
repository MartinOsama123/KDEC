package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionInfoRepository extends JpaRepository<SessionInfo, String> {
}
