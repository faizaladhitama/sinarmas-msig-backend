package com.msig.school.backend.controller;

import com.msig.school.backend.model.ResponseDto;
import com.msig.school.backend.service.BaseService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public abstract class BaseController<Model, Entity, PrimaryKey> {
    private final BaseService<Model, Entity, PrimaryKey> service;

    @GetMapping(path = "/paginate", produces = "application/json")
    @RateLimiter(name = "baseController")
    @CircuitBreaker(name = "baseController")
    public @ResponseBody ResponseDto<Page<Model>> getPageable(Pageable pageable, Model model) {
        return ResponseDto.of(service.getPageable(pageable, model), HttpStatus.OK, "Get list by pagination and sorting success");
    }

    @GetMapping(path = "/", produces = "application/json")
    @RateLimiter(name = "baseController")
    @CircuitBreaker(name = "baseController")
    public @ResponseBody ResponseDto<List<Model>> getList(Model model) {
        return ResponseDto.of(service.getList(model), HttpStatus.OK, "Get list success");
    }

    @GetMapping(path = "/detail/{id}", produces = "application/json")
    @RateLimiter(name = "baseController")
    @CircuitBreaker(name = "baseController")
    public @ResponseBody ResponseDto<Model> get(@PathVariable("id") PrimaryKey id) {
        return ResponseDto.of(service.getById(id), HttpStatus.OK, "Get by id success");
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    @RateLimiter(name = "baseController")
    @CircuitBreaker(name = "baseController")
    public @ResponseBody ResponseDto<Model> create(@RequestBody Model modelDto) {
        return ResponseDto.of(service.create(modelDto), HttpStatus.CREATED, "Create success");
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @RateLimiter(name = "baseController")
    @CircuitBreaker(name = "baseController")
    public @ResponseBody ResponseDto<Model> update(@PathVariable("id") PrimaryKey id, Model modelDto) {
        return ResponseDto.of(service.updateById(id, modelDto), HttpStatus.OK, "Update by id success");
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @RateLimiter(name = "baseController")
    @CircuitBreaker(name = "baseController")
    public @ResponseBody ResponseDto<Boolean> delete(@PathVariable("id") PrimaryKey id) {
        return ResponseDto.of(service.deleteById(id), HttpStatus.OK, "Delete by id success");
    }
}
