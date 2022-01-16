package com.example.spring.data.jpa.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tutorials")
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Getter
  @Setter
  @Column(name = "title")
  private String title;

  @Getter
  @Setter
  @Column(name = "description")
  private String description;

  @Getter
  @Setter
  @Column(name = "published")
  private boolean published;

}
