package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.NotificationInfo;
import com.genesiscreations.kdec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

}
