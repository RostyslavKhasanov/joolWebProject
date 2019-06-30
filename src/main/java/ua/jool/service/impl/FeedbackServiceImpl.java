package ua.jool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jool.domain.FeedbackDTO;
import ua.jool.entity.FeedbackEntity;
import ua.jool.repository.FeedbackRepository;
import ua.jool.service.FeedbackService;
import ua.jool.utils.ObjectMapperUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void create(FeedbackDTO feedbackDTO) {
        feedbackDTO.setDate(LocalDate.now());
        FeedbackEntity feedbackEntity = modelMapper.map(feedbackDTO, FeedbackEntity.class);
        feedbackRepository.save(feedbackEntity);
    }

    @Override
    public List<FeedbackDTO> findByGame(Long id) {
        List<FeedbackEntity> feedbackEntities = feedbackRepository.findAllByGame_IdOrderByIdDesc(id);
        List<FeedbackDTO> feedbackDTOS = modelMapper.mapAll(feedbackEntities, FeedbackDTO.class);
        return feedbackDTOS;
    }
}
