package com.example.spring.data.jpa.practice.service;

import java.util.List;
import com.example.spring.data.jpa.practice.model.Tutorial;
import com.example.spring.data.jpa.practice.repository.TutorialRepository;
import java.util.Optional;
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

  public Optional<Tutorial> getAllTutorialsById(Long id) {
    return tutorialRepository.findById(id);
  }

  public boolean createTutorial(String title, String description, boolean published) {

    try {
      Tutorial _tutorial = tutorialRepository.save(new Tutorial(title, description, published));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean deleteTutorial(Long id) {
    try {
      tutorialRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public List<Tutorial> getTutorialsByPublished(boolean published) {
    return tutorialRepository.findByPublished(true);
  }
}
