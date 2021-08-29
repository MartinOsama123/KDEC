package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.SessionInfo;
import com.genesiscreations.kdec.model.SongInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongInfoRepository  extends JpaRepository<SongInfo, Integer> {
    @Query( value = "SELECT * FROM song_info  where song_info.album_name = :albumName",nativeQuery = true)
    public List<SongInfo> findAllByAlbumIgnoreCase(String albumName);
    @Query( value = "SELECT s.albumName FROM SongInfo s where lower(s.albumName) like lower(concat('%', :albumName,'%'))")
    public List<String> findAllByAlbumNameIgnoreCase(String albumName);
    @Query( value = "SELECT s.songName FROM SongInfo s where lower(s.songName)like lower(concat('%', :songName,'%'))")
    public List<String> findAllBySongIgnoreCase(String songName);
    @Query( value = "SELECT s.author FROM SongInfo s where lower(s.author) like lower(concat('%', :author,'%'))")
    public List<String> findAllByAuthorIgnoreCase(String author);

}
