package ua.jool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.jool.domain.FeedbackDTO;
import ua.jool.entity.FeedbackEntity;
import ua.jool.entity.GameEntity;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {

    List<FeedbackEntity> findAllByGame_IdOrderByIdDesc(Long id);

}
