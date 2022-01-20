package com.example.spring.data.jpa.practice.controller;

import com.example.spring.data.jpa.practice.model.Tutorial;
import com.example.spring.data.jpa.practice.service.TutorialService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TutorialController {

  // Todo Add Logger
  private TutorialService tutorialService;

  @Autowired
  public TutorialController(TutorialService tutorialService) {
    this.tutorialService = tutorialService;
  }


  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(
      @RequestParam(required = false) String title) {
    try {
      List<Tutorial> tutorials = new ArrayList<Tutorial>();

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
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    Optional<Tutorial> tutorialData = tutorialService.getAllTutorialsById(id);

    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    if (tutorialService.createTutorial(tutorial.getTitle(), tutorial.getDescription(), false)) {
      return new ResponseEntity(true, HttpStatus.CREATED);
    } else {
      return new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
