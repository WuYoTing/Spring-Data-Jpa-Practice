package com.example.spring.data.jpa.practice.model.dto;


import com.example.spring.data.jpa.practice.model.dao.Tutorial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorialResourse {

  private String title;
  private String description;
  private boolean published;

  public TutorialResourse(Tutorial e) {
    this.title = e.getTitle();
    this.description = e.getDescription();
    this.published = e.isPublished();
  }
}
