package com.github.jj_sallo.recordstore.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="albums")
public class Album {
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_albums", nullable = false, unique = true)
    public Long id;
    @Column
    private String name;
    @Column
    private String artists;
    @Column
    private Date releaseDate;
    @Column
    private String coverArt;
    @Column
    private String songList;
    public void setName(String name) {
        this.name = name;
    }
    public void setArtists(String artists) {
        this.artists = artists;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }
    public void setSongList(String songList) {
        this.songList = songList;
    }
    public String getName() {
        return name;
    }
    public String getArtists() {
        return artists;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public String getCoverArt() {
        return coverArt;
    }
    public String getSongList() {
        return songList;
    }
}
