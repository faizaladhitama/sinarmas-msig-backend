package com.msig.school.backend.mapper;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.UserDto;

import java.util.List;

public interface BaseMapper<Model, Entity> {
    Entity toEntity(Model model);
    Model toModel(Entity entity);
    List<Entity> toEntities(List<Model> models);
    List<Model> toModels(List<Entity> entities);
}
