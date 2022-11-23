package com.github.jj_sallo.recordstore.service;

import com.github.javafaker.Faker;
import com.github.jj_sallo.recordstore.entity.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlbumService {
    public static List<Album> generateAlbums(int n) {
        List<Album> albumList = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < n; i++) {
            Album album = new Album();
            album.setName(faker.book().title());
            album.setArtists(faker.artist().name());
            album.setCoverArt(faker.file().fileName(
                    "albums/coverart",
                    null,
                    ".jpeg",
                    null));
            album.setSongList(faker.file().fileName(
                    "albums/songs",
                    null,
                    ".flac",
                    null));
            album.setReleaseDate(faker.date().past(1, TimeUnit.SECONDS));
            albumList.add(album);
        }
        return albumList;
    }
}
