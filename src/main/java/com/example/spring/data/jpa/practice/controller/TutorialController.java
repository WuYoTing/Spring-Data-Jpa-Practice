package com.example.spring.data.jpa.practice.controller;

import com.example.spring.data.jpa.practice.model.Tutorial;
import com.example.spring.data.jpa.practice.service.TutorialService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    try {
      List<Tutorial> tutorials = new ArrayList<Tutorial>();

      //Todo Refactor To Service Layer
      if (title == null) {
        // tutorialRepository.findAll().forEach(tutorials::add);
      } else {
        // tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
      }

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
