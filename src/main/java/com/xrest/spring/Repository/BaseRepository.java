package com.xrest.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T,PK> extends JpaRepository<T,PK> {
}
