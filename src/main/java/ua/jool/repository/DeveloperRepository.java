package ua.jool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.jool.entity.DeveloperEntity;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
}
