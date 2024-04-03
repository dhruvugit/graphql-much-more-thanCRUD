package com.apigraphql.fullannotaion.service;


import com.apigraphql.fullannotaion.entites.Author;
import com.apigraphql.fullannotaion.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author createAuthor(String name, Integer age) {
        Author author = new Author();
        author.setName(name);
        author.setAge(age);

        authorRepository.save(author);

        return author;
    }

    public Integer countAuthors(){
        return (int)authorRepository.count();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }


}

