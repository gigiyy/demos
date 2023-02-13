package com.example.fcb.play.number;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotherRepository extends JpaRepository<AnotherEntity, Long> {
}
