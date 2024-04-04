package com.apigraphql.fullannotaion.controller;


import com.apigraphql.fullannotaion.entites.Tutorial;
import com.apigraphql.fullannotaion.service.TutorialService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.util.Optional;


//extend type Query {
//    findAllTutorials: [Tutorial]!
//            countTutorials: Integer!
//}
//
//extend type Mutation {
//    createTutorial(title: String!, description: String, author: ID!): Tutorial!
//            updateTutorial(id: ID!, title: String, description: String): Tutorial!
//            deleteTutorial(id: ID!): Boolean
//}


@Controller
public class TutorialController implements GraphQLQueryResolver {

    @Autowired
    private TutorialService tutorialService;

    @QueryMapping
    public Iterable<Tutorial> findAllTutorials() {
        return tutorialService.findAllTutorials();
    }

    @QueryMapping
    public Integer countTutorials(){
        return tutorialService.countTutorials();
    }

//    @MutationMapping
//    public Tutorial createTutorial(@Argument String title,@Argument Integer authorId,@Argument String description) throws Exception {
//        System.out.println("Vales passed to controller : " + title + authorId + description);
//        return tutorialService.createTutorial(title,authorId, description);
//    }

    @Secured("ROLE_USER")
    @MutationMapping
    public Tutorial createTutorial(@Argument Integer authorId, @Argument String title, @Argument String description){
        System.out.println("Vales passed to controller : " + title + authorId + description);
        return tutorialService.createTutorial(authorId, title, description);
    }

    @MutationMapping
    public Tutorial updateTutorial(@Argument Integer id,@Argument String title,@Argument String description) {
        System.out.println("Vales passed to controller : " + id + title + description);
        return tutorialService.updateTutorial(id, title, description);
    }

    @MutationMapping
    public Boolean deleteTutorial(@Argument Integer id) {
        return  tutorialService.deleteTutorial(id);
        // Consider returning a specific response object instead of just boolean
    }
}
