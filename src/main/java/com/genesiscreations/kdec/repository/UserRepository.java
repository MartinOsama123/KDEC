package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.AlbumImg;
import com.genesiscreations.kdec.model.NotificationInfo;
import com.genesiscreations.kdec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
