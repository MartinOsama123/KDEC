package com.genesiscreations.kdec.repository;

import com.genesiscreations.kdec.model.SessionInfo;
import com.genesiscreations.kdec.model.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongInfoRepository  extends JpaRepository<SongInfo, Integer> {
    @Query( value = "SELECT * FROM song_info  where song_info.album_name = :albumName",nativeQuery = true)
    public List<SongInfo> findAllByAlbum(String albumName);
    @Query(value = "SELECT DISTINCT album_name FROM song_info",nativeQuery = true)
    public List<String> getAlbums();
}
