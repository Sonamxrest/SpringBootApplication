package com.xrest.spring.ServiceImpl;

import com.xrest.spring.Repository.BaseRepository;
import com.xrest.spring.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BaseServiceImpl<T,PK> implements BaseService<T,PK> {
    @Autowired
    private BaseRepository<T,PK> baseRepository;

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public T findOne(PK id) {
        return baseRepository.findById(id).get();
    }

    @Override
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    public Page<T> findAllPageable(Object t, Pageable pageable) {
        return null;
    }

    @Override
    public List<T> saveAll(List<T> list) {
        return baseRepository.saveAll(list);
    }
}
