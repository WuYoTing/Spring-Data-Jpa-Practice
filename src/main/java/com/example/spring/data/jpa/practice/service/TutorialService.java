package com.example.spring.data.jpa.practice.service;

import java.util.List;
import com.example.spring.data.jpa.practice.model.Tutorial;
import com.example.spring.data.jpa.practice.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorialService {

  private TutorialRepository tutorialRepository;

  @Autowired
  public TutorialService(TutorialRepository tutorialRepository) {
    this.tutorialRepository = tutorialRepository;
  }

  public List<Tutorial> getAllTutorials() {
    return tutorialRepository.findAll();
  }

  public List<Tutorial> getAllTutorialsByTitle(String title) {
    return tutorialRepository.findByTitleContaining(title);
  }
}
