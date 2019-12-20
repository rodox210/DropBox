package com.example.architecture.impl.user.repository.interfaces;

import com.example.architecture.impl.user.repository.SharingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SharingRepository extends MongoRepository<SharingEntity, String> {
    Optional<SharingEntity> findById(String sourceId);

    List<SharingEntity> findBySourceId(String sourceId);

    List<SharingEntity> findByFileName(String fileName);

    List<SharingEntity> findByRecipientId(String recipientId);

    Optional<SharingEntity> findDBById(String id);
}
