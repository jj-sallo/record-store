package com.github.jj_sallo.recordstore.repository;

import com.github.jj_sallo.recordstore.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
