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
    @Query( value = "SELECT s FROM SongInfo s where lower(s.author) like lower(concat('%', :query,'%')) OR lower(s.albumName) like lower(concat('%', :query,'%')) OR lower(s.songName) like lower(concat('%', :query,'%'))")
    public List<SongInfo> findAllByAuthorOrAlbumOrSongIgnoreCase(String query);

}
