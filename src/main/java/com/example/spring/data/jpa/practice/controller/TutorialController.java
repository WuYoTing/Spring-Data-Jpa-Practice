package com.example.spring.data.jpa.practice.controller;

import com.example.spring.data.jpa.practice.model.Tutorial;
import com.example.spring.data.jpa.practice.service.TutorialService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/")
public class TutorialController {

  private TutorialService tutorialService;

  @Autowired
  public TutorialController(TutorialService tutorialService) {
    this.tutorialService = tutorialService;
  }


  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(
      @RequestParam(required = false) String title) {
    log.info("GET /tutorials");
    List<Tutorial> tutorials = new ArrayList<>();
    try {
      if (title == null) {
        tutorials = tutorialService.getAllTutorials();

      } else {
        tutorials = tutorialService.getAllTutorialsByTitle(title);
      }

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(tutorials, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    log.info("GET /tutorials/{}", id);
    Optional<Tutorial> tutorialData = tutorialService.getAllTutorialsById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // TODO do not use tutorial(dao) use POJO or DTO
  @RequestMapping(path = "/tutorials", method = RequestMethod.POST)
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    log.info("POST /tutorials");
    if (tutorialService.createTutorial(tutorial.getTitle(), tutorial.getDescription(), false)) {
      return new ResponseEntity(true, HttpStatus.CREATED);
    } else {
      return new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<HttpStatus> deleteTutorials(@PathVariable("id") Long id) {
    log.info("DELETE /tutorials/{}",id);
    if (tutorialService.deleteTutorial(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> getTutorialsByPublished() {
    log.info("GET /tutorials/published");
    List<Tutorial> tutorials = tutorialService.getTutorialsByPublished(true);
    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }
}

