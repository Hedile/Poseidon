package com.openclassrooms.poseidon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.poseidon.exception.NegativeNumberException;
import com.openclassrooms.poseidon.model.Rating;
import com.openclassrooms.poseidon.repository.RatingRepository;
@Service
public class RatingService {
	@Autowired
	private  RatingRepository ratingRepository;

  
    public RatingService() {
		super();
		
	}


	public RatingService(RatingRepository ratingRepository) {
		super();
		this.ratingRepository = ratingRepository;
	}


	public void validateRating(Rating rating){
       
        Rating addRating = new Rating();
        addRating.setMoodysRating(rating.getMoodysRating());
        addRating.setSandPRating(rating.getSandPRating());
        addRating.setFitchRating(rating.getFitchRating());
        addRating.setOrderNumber(rating.getOrderNumber());
        ratingRepository.save(addRating);

    }

   
    public void updateRating(Integer id, Rating rating)  {
       
        rating.setId(id);
        ratingRepository.save(rating);

    }
    public List<Rating> getRatingList() {
        return ratingRepository.findAll();
    }

    public Rating getRatingById(Integer id) {
        return ratingRepository.findRatingById(id);
    }
   
    public void deleteRating(Integer id) {
       ratingRepository.deleteById(id);
    }
}
