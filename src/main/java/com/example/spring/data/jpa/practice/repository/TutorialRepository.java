package com.example.spring.data.jpa.practice.repository;

import com.example.spring.data.jpa.practice.model.Tutorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

  List<Tutorial> findByPublished(boolean published);

  List<Tutorial> findByTitleContaining(String title);
}
