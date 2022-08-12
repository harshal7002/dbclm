package com.assignment.dbclm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.dbclm.entities.NACEDao;

@Repository
public interface NACERepository extends JpaRepository<NACEDao, Long> {
    public Optional<NACEDao> findByOrder(Long order);
}
