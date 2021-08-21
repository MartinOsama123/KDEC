package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
