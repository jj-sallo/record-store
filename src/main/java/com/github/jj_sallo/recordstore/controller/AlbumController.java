package com.github.jj_sallo.recordstore.controller;

import com.github.javafaker.Faker;
import com.github.jj_sallo.recordstore.entity.Album;
import com.github.jj_sallo.recordstore.repository.AlbumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/albums")
public class AlbumController {
    @Autowired
    AlbumRepository albumRepository;

    @GetMapping(value = "/{id}")
    ResponseEntity<Album> getAlbum(@PathVariable long id) {
        Optional<Album> albumData = albumRepository.findById(id);
        if(albumData.isPresent()) {
            return new ResponseEntity<>(albumData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ResponseBody
    ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/generate")
    ResponseEntity<List<Album>> generateAlbums() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            Album album = new Album();
            album.setName(faker.book().title());
            album.setArtists(faker.artist().name());
            album.setCoverArt(faker.file().fileName("albums/coverart", null, ".jpeg", null));
            album.setSongList(faker.file().fileName("albums/songs", null, ".flac", null));
            album.setReleaseDate(faker.date().past(1, TimeUnit.SECONDS));
            albumRepository.save(album);
        }
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.CREATED);
    }

    @PostMapping(headers = "Accept=application/json")
    ResponseEntity<Album> newUser(@RequestBody Album album) {
        try {
            albumRepository.save(album);
            return new ResponseEntity<>(album, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    ResponseEntity<Album> updateUser(@RequestBody Album album, @PathVariable(value = "id") long id) {
        Optional<Album> albumData = albumRepository.findById(id);
        if (albumData.isPresent()) {
            try {
                Album foundAlbum = albumData.get();
                foundAlbum.setName(album.getName());
                foundAlbum.setArtists(album.getArtists());
                foundAlbum.setCoverArt(album.getCoverArt());
                foundAlbum.setSongList(album.getSongList());
                foundAlbum.setReleaseDate(album.getReleaseDate());
                albumRepository.save(foundAlbum);
                return new ResponseEntity<>(foundAlbum, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}