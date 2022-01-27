package com.example.spring.data.jpa.practice.service;

import com.example.spring.data.jpa.practice.model.dto.TutorialResourse;
import java.util.List;
import com.example.spring.data.jpa.practice.model.dao.Tutorial;
import com.example.spring.data.jpa.practice.repository.TutorialRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TutorialService {

  private TutorialRepository tutorialRepository;

  @Autowired
  public TutorialService(TutorialRepository tutorialRepository) {
    this.tutorialRepository = tutorialRepository;
  }

  public List<TutorialResourse> getAllTutorials() {
    List<TutorialResourse> tutorialResourseList = tutorialRepository.findAll().stream()
        .map(e -> new TutorialResourse(e)).collect(Collectors.toList());
    return tutorialResourseList;
  }

  public List<TutorialResourse> getAllTutorialsByTitle(String title) {
    List<TutorialResourse> tutorialResourseList = tutorialRepository.findByTitleContaining(title)
        .stream().map(e -> new TutorialResourse(e)).collect(Collectors.toList());
    return tutorialResourseList;
  }

  // Todo Optional dao to Optional dto
  public Optional<Tutorial> getAllTutorialsById(Long id) {
    return tutorialRepository.findById(id);
  }

  public boolean createTutorial(String title, String description, boolean published) {
    try {
      tutorialRepository.save(new Tutorial(title, description, published));
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

  public List<TutorialResourse> getTutorialsByPublished(boolean published) {
    List<TutorialResourse> tutorialResourseList = tutorialRepository.findByPublished(published)
        .stream().map(e -> new TutorialResourse(e)).collect(Collectors.toList());
    return tutorialResourseList;
  }
}
