package com.assignment.dbclm;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.dbclm.entities.NACEDao;

public interface TestH2NACERepository extends JpaRepository<NACEDao, Long> {
    public Optional<NACEDao> findByOrder(Long order);
}
