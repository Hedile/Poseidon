package com.openclassrooms.poseidon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.poseidon.model.Rating;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
	Rating findRatingById(int id);
    
}
