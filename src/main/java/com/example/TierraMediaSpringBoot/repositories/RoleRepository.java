package com.example.TierraMediaSpringBoot.repositories;

import com.example.TierraMediaSpringBoot.models.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository <RoleEntity, Long> {
}
