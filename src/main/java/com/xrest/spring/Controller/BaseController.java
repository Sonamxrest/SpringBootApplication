package com.xrest.spring.Controller;

import com.xrest.spring.Service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BaseController<T,PK> {
    private BaseService<T, PK> baseService;

    public BaseController(BaseService<T, PK> baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody T entity) {
        final T saved = baseService.save(entity);
        if (null == saved) {
            throw new RuntimeException();
        }
        return new ResponseEntity<T>(saved, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@RequestParam("id") PK id) {
        return new ResponseEntity<T>(baseService.findOne(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> findOne() {
        return new ResponseEntity<List<T>>(baseService.findAll(), HttpStatus.OK);
    }
}
