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
            album.name = faker.book().title();
            album.artists = faker.artist().name();
            album.coverArt = faker.file().fileName("albums/coverart", null, ".jpeg", null);
            album.songList = faker.file().fileName("albums/songs", null, ".flac", null);
            album.releaseDate = faker.date().past(1, TimeUnit.SECONDS);
            albumRepository.save(album);
        }
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/create")
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
                foundAlbum.name = album.name;
                foundAlbum.artists = album.artists;
                foundAlbum.coverArt = album.coverArt;
                foundAlbum.songList = album.songList;
                foundAlbum.releaseDate = album.releaseDate;
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