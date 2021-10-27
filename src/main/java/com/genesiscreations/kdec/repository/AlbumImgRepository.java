package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.AlbumImg;
import com.genesiscreations.kdec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumImgRepository extends JpaRepository<AlbumImg,String> {
    @Query("select u from AlbumImg u where u.categoryName = :categoryName")
    List<AlbumImg> findByCategoryName(@Param("categoryName") String categoryName);
}
