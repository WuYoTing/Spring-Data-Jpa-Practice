package com.example.spring.data.jpa.practice.controller;

import com.example.spring.data.jpa.practice.model.dao.Tutorial;
import com.example.spring.data.jpa.practice.model.dto.TutorialResourse;
import com.example.spring.data.jpa.practice.service.TutorialService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tutorials")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TutorialController {

	private TutorialService tutorialService;


	@GetMapping("/")
	public ResponseEntity<List<TutorialResourse>> getAllTutorials(
		@RequestParam(required = false) String title) {
		log.info("GET /tutorials");
		List<TutorialResourse> tutorials = new ArrayList<>();
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

	@GetMapping("/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		log.info("GET /tutorials/{}", id);
		TutorialResourse tutorialResourse = tutorialService.getAllTutorialsById(id);
		return new ResponseEntity(tutorialResourse, HttpStatus.OK);
	}

	// TODO do not use tutorial(dao) use POJO or DTO
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ResponseEntity<TutorialResourse> createTutorial(@RequestBody TutorialResourse tutorial) {
		log.info("POST /tutorials");
		if (tutorialService.createTutorial(tutorial.getTitle(), tutorial.getDescription(), false)) {
			return new ResponseEntity(true, HttpStatus.CREATED);
		} else {
			return new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTutorials(@PathVariable("id") Long id) {
		log.info("DELETE /tutorials/{}", id);
		if (tutorialService.deleteTutorial(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/published")
	public ResponseEntity<List<TutorialResourse>> getTutorialsByPublished() {
		log.info("GET /tutorials/published");
		List<TutorialResourse> tutorials = tutorialService.getTutorialsByPublished(true);
		if (tutorials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tutorials, HttpStatus.OK);
	}
}

