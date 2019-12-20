package com.example.architecture.impl.user.repository.interfaces;

import com.example.architecture.impl.user.repository.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findById(String id);

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByEmail(String email);

}
