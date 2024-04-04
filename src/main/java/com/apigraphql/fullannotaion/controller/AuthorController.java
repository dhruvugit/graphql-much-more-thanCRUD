package com.apigraphql.fullannotaion.controller;

import com.apigraphql.fullannotaion.entites.Author;
import com.apigraphql.fullannotaion.service.AuthorService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import jakarta.annotation.PostConstruct;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Controller
public class AuthorController implements GraphQLQueryResolver {

    private FluxSink<Author> authorStream;
    private ConnectableFlux<Author> authorPublisher;

    @PostConstruct
    public void init() {

        Flux<Author> publisher = Flux.create(emitter -> {
            authorStream = emitter;
        });
        authorPublisher = publisher.publish();
        authorPublisher.connect();
    }


    @Autowired
    private AuthorService authorService;

    @Secured("ROLE_USER")
    @QueryMapping
    public Iterable<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    @QueryMapping
    public Integer countAuthor(@Argument Integer id) {
        return authorService.countAuthors();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public Author createAuthor(@Argument String name,@Argument Integer age){

        Author author = authorService.createAuthor(name, age);
        authorStream.next(author); // Emit the newly created author
        return author;
    }


    @SubscriptionMapping
    public Publisher<Author> notifyAuthorRegister() {
        return authorPublisher;
    }



//    @Secured("ROLE_USER")
//    @PreAuthorize("hasRole('ADMIN')")



//    type Subscription {
//        notifyAuthorRegister: Author
//    }


}