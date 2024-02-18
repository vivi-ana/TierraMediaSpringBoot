package com.example.TierraMediaSpringBoot.repositories;

import com.example.TierraMediaSpringBoot.models.UserCredentialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends CrudRepository<UserCredentialEntity, Long> {
    Optional<UserCredentialEntity> findById(Long id);
}
