package ua.jool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.jool.domain.FeedbackDTO;
import ua.jool.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.create(feedbackDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getFeedbackForGame(@RequestParam Long id) {
        List<FeedbackDTO> feedbacks = feedbackService.findByGame(id);
        return new ResponseEntity<List<FeedbackDTO>>(feedbacks, HttpStatus.OK);
    }
}
