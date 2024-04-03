package com.apigraphql.fullannotaion.service;

import com.apigraphql.fullannotaion.entites.Author;
import com.apigraphql.fullannotaion.entites.Tutorial;
import com.apigraphql.fullannotaion.repository.AuthorRepository;
import com.apigraphql.fullannotaion.repository.TutorialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;


    public Tutorial createTutorial(Integer authorId, String title, String description) {
        System.out.println("Vales passed to service : " + title + authorId + description);
        Tutorial tutorial = new Tutorial();
        System.out.println("Current tutorial " + tutorial);
        tutorial.setAuthor(new Author(authorId));
        tutorial.setTitle(title);
        tutorial.setDescription(description);
        System.out.println("Current tutorial " + tutorial);

        tutorialRepository.save(tutorial);
        System.out.println("Current tutorial " + tutorial);

        return tutorial;
    }

//    public Tutorial createTutorial(String title, Integer authorId, String description) {
//        System.out.println("Values passed to service: title=" + title + ", authorId=" + authorId + ", description=" + description);
//
//        // Fetch existing author if authorId refers to an existing one
//
//        Author author = authorRepository.findById(authorId).orElseThrow(null);
//
//        Tutorial tutorial = new Tutorial();
//        tutorial.setAuthor(author);
//        tutorial.setTitle(title);
//        tutorial.setDescription(description);
//
//        // Save both Tutorial and Author if author is new
//        if (author.getId() == null) {
//            authorRepository.save(author);
//        }
//
//        Tutorial savedTutorial = tutorialRepository.save(tutorial);
//        return savedTutorial;
//    }





    public Iterable<Tutorial> findAllTutorials() {
        return tutorialRepository.findAll();
    }

    public Integer countTutorials() {
        return (int) tutorialRepository.count();
    }


    public boolean deleteTutorial(Integer id) {
        tutorialRepository.deleteById(id);
        return true;
    }

    public Tutorial updateTutorial(Integer id, String title, String description) throws EntityNotFoundException {
        Optional<Tutorial> optTutorial = tutorialRepository.findById(id);

        if (optTutorial.isPresent()) {
            Tutorial tutorial = optTutorial.get();

            if (title != null)
                tutorial.setTitle(title);
            if (description != null)
                tutorial.setDescription(description);

            tutorialRepository.save(tutorial);
            return tutorial;
        }

        throw new EntityNotFoundException("Not found Tutorial to update!");
    }
}