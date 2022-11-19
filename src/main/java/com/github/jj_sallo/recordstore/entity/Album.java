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
    public String name;

    @Column
    public String artists;

    @Column
    public Date releaseDate;

    @Column
    public String coverArt;

    @Column
    public String songList;
}
