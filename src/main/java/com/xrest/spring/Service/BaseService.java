package com.xrest.spring.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

public interface BaseService<T, PK> {

    List<T> findAll();


    default List<T> findAll(Object search) {
        return Collections.emptyList();
    }


    T findOne(PK id);


    T save(T t);


    Page<T> findAllPageable(Object t, Pageable pageable);


    List<T> saveAll(List<T> list);
}
