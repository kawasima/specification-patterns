package net.unit8.example.trust.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends  JpaRepository<UserJpaEntity, String>, JpaSpecificationExecutor<UserJpaEntity> {
}
