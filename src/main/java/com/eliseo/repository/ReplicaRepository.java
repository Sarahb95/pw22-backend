package com.eliseo.repository;

import com.eliseo.model.Replica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReplicaRepository extends JpaRepository<Replica, Long> {
    List<Replica> findBySpettacoloId(Long spettacoloId);
    List<Replica> findByDataOraAfterOrderByDataOraAsc(LocalDateTime now);
}
