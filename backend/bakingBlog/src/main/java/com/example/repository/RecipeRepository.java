package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}