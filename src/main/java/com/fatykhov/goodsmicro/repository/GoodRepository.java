package com.fatykhov.goodsmicro.repository;

import com.fatykhov.goodsmicro.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
    Optional<Good> findByNameAndType(String name, String type);
}
