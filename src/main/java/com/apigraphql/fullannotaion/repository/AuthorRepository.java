package com.apigraphql.fullannotaion.repository;


import com.apigraphql.fullannotaion.entites.Author;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
