package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.NotificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationInfo,Integer> {
}
