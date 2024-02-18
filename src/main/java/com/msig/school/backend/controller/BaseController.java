package com.msig.school.backend.controller;

import com.msig.school.backend.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseController<Model, Entity, PrimaryKey> {
    private final BaseService<Model, Entity, PrimaryKey> service;

    @GetMapping(path = "/paginate", produces = "application/json")
    public @ResponseBody Page<Model> getPageable(Pageable pageable, Model model) {
        return service.getPageable(pageable, model);
    }

    @GetMapping(path = "/", produces = "application/json")
    public @ResponseBody List<Model> getList(Model model) {
        return service.getList(model);
    }

    @GetMapping(path = "/detail/{id}", produces = "application/json")
    public @ResponseBody Model get(@PathVariable("id") PrimaryKey id) {
        return service.getById(id);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public @ResponseBody Model create(@RequestBody Model modelDto) {
        return service.create(modelDto);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody Model update(@PathVariable("id") PrimaryKey id, Model modelDto) {
        return service.updateById(id, modelDto);
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody Boolean delete(@PathVariable("id") PrimaryKey id) {
        return service.deleteById(id);
    }
}
