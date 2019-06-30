package ua.jool.service;

import ua.jool.domain.FeedbackDTO;
import ua.jool.entity.FeedbackEntity;

import java.util.List;

public interface FeedbackService {

    void create(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> findByGame(Long id);
}
