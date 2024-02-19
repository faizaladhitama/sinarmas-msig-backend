package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.mapper.BaseMapper;
import com.msig.school.backend.model.UserDto;
import com.msig.school.backend.service.BaseService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class BaseServiceImpl<Model, Entity, PrimaryKey> implements BaseService<Model, Entity, PrimaryKey> {
    private final JpaRepository<Entity, PrimaryKey> repository;
    private final BaseMapper<Model, Entity> baseMapper;

    public BaseServiceImpl(JpaRepository<Entity, PrimaryKey> repository, BaseMapper<Model, Entity> baseMapper) {
        this.repository = repository;
        this.baseMapper = baseMapper;
    }

    @Override
    public Page<Model> getPageable(Pageable pageable,Model model) {
        Example<Entity> example = Example.of(baseMapper.toEntity(model));
        Page<Entity> pages = repository.findAll(example, pageable);
        return pages.map(baseMapper::toModel);
    }

    @Override
    public List<Model> getList(Model model) {
        Example<Entity> example = Example.of(baseMapper.toEntity(model));
        List<Entity> entities = repository.findAll(example);
        return baseMapper.toModels(entities);
    }

    @Override
    public Model getById(PrimaryKey id) {
        Optional<Entity> optionalEntityp = repository.findById(id);
        return baseMapper.toModel(optionalEntityp.orElse(null));
    }

    @Transactional
    @Override
    public Model updateById(PrimaryKey id, Model model) {
        Entity entity = baseMapper.toEntity(this.getById(id));
        if (Objects.isNull(entity)) {
            return null;
        }
        entity = baseMapper.toEntity(model);
        return baseMapper.toModel(repository.save(entity));
    }

    @Transactional
    @Override
    public Boolean deleteById(PrimaryKey id) {
        Entity getEntity = baseMapper.toEntity(this.getById(id));
        if (Objects.isNull(getEntity)) {
            return Boolean.FALSE;
        }
        repository.deleteById(id);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Model create(Model model) {
        Example<Entity> example = getExample(model);
        if(!Objects.isNull(example)){
            Entity entityFound = uniqueValidation(example);
            if(!Objects.isNull(entityFound)){
                throw new EntityExistsException("Entity exists");
            }
        }

        Entity entity = baseMapper.toEntity(model);
        return baseMapper.toModel(repository.save(entity));
    }

    @Override
    public Entity uniqueValidation(Example<Entity> example) {
        Optional<Entity> entity = repository.findOne(example);
        return entity.orElse(null);
    }

    @Override
    public Entity getObjForExample(Model user){
        return null;
    }

    @Override
    public Example<Entity> getExample(Model model){
        Entity entity = getObjForExample(model);
        if(Objects.isNull(entity)){
            return null;
        }
        return Example.of(getObjForExample(model));
    }
}
