package com.msig.school.backend.service;

import com.msig.school.backend.model.UserDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<Model, Entity, PrimaryKey> {
    Page<Model> getPageable(Pageable pageable,Model model);

    List<Model> getList(Model model);
    Model getById(PrimaryKey id);

    Model updateById(PrimaryKey id, Model model);

    Boolean deleteById(PrimaryKey id);

    Model create(Model model);
}
