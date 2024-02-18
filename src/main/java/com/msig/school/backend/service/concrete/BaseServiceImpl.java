package com.msig.school.backend.service.concrete;

import com.msig.school.backend.mapper.BaseMapper;
import com.msig.school.backend.service.BaseService;
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

@Service
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
        Entity entity = baseMapper.toEntity(model);
        return baseMapper.toModel(repository.save(entity));
    }
}
