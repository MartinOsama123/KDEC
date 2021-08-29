package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.NotificationInfo;
import com.genesiscreations.kdec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationInfo,Integer> {
    @Query("select u from User u where u.uid = :uid")
    User getUser(@Param("uid") String uid);
    @Query("select n from NotificationInfo n where n.user.uid = :uid")
    List<NotificationInfo> getNotifications(@Param("uid") String uid);
}
