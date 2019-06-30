package ua.jool.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.jool.entity.GameEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    GameEntity findByName(String name);

    List<GameEntity> findByDeveloper_Name(String developerName);

    List<GameEntity> findByNameLike(String name);

    List<GameEntity> findByCategories_Name(String name);

    List<GameEntity> findByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo);
}
