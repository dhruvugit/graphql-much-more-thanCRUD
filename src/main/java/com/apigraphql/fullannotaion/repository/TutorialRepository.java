package com.apigraphql.fullannotaion.repository;


import com.apigraphql.fullannotaion.entites.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

}