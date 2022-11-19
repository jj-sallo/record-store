package com.github.jj_sallo.recordstore.repository;

import com.github.jj_sallo.recordstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Repository of record
}
