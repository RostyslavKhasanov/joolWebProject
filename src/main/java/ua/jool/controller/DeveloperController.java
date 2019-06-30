package ua.jool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.jool.domain.DeveloperDTO;
import ua.jool.entity.DeveloperEntity;
import ua.jool.service.DeveloperService;

import java.util.List;

@RestController
@RequestMapping("developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeveloperDTO developerDTO) {
        developerService.create(developerDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllDevelopers() {
        List<DeveloperDTO> developers = developerService.findAll();
        return new ResponseEntity<List<DeveloperDTO>>(developers, HttpStatus.OK);
    }

    @DeleteMapping("{developerId}")
    public ResponseEntity<?> deleteGame(
            @PathVariable("developerId") Long id
    ) {
        developerService.deleteDeveloperById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
