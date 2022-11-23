package com.github.jj_sallo.recordstore.controller;

import com.github.jj_sallo.recordstore.entity.Album;
import com.github.jj_sallo.recordstore.repository.AlbumRepository;
import com.github.jj_sallo.recordstore.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "/api/albums")
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
        List<Album> albumList = AlbumService.generateAlbums(5);
        albumRepository.saveAll(albumList);
        return new ResponseEntity<>(albumRepository.findAll(), HttpStatus.CREATED);
    }

    @PostMapping(headers = "Accept=application/json")
    ResponseEntity<Album> newAlbum(@RequestBody Album album) {
        try {
            albumRepository.save(album);
            return new ResponseEntity<>(album, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    ResponseEntity<Album> updateAlbum(@RequestBody Album album, @PathVariable(value = "id") long id) {
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