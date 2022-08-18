package com.openclassrooms.poseidon;




import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.openclassrooms.poseidon.model.Rating;
import com.openclassrooms.poseidon.repository.RatingRepository;
import com.openclassrooms.poseidon.service.RatingService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private RatingService ratingService;
	@AfterEach
	public void tearDown() {
		
		ratingRepository.deleteAll();
	}
	@Test
	public void ratingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		rating = ratingRepository.save(rating);
		assertNotNull(rating.getId());
		assertTrue(rating.getOrderNumber() == 10);

		// Update
		rating.setOrderNumber(20);
		rating = ratingRepository.save(rating);
		assertTrue(rating.getOrderNumber() == 20);

		// Find
		List<Rating> listResult = ratingRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rating.getId();
		ratingRepository.delete(rating);
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertFalse(ratingList.isPresent());
	}
	@Test
	public void deleteRatingTest() {
		Rating rating = new Rating("moodys","sand","fitch",10);
		rating = ratingRepository.save(rating);
		// Delete
		Integer id = rating.getId();
		ratingService.deleteRating(id);
		Optional<Rating> ratingId = ratingRepository.findById(id);
		assertFalse(ratingId.isPresent());
	}

	@Test
	public void updateRatingTest() {
		//given
		Rating rating = new Rating("moodys","sand","fitch",10);
		rating = ratingRepository.save(rating);
		Integer id = rating.getId();

		Rating ratingToUpdate = new Rating("moodysTest","sandTest","fitchTest",20);

		//when
		assertDoesNotThrow(() -> ratingService.updateRating(id, ratingToUpdate));
		Rating updateRating=ratingService.getRatingById(id);
		//then
		assertEquals(updateRating.getMoodysRating(),"moodysTest");
	}

	@Test
	public void validateRatingTest() {
		//given
		Rating addRating = new Rating("moodysNew","sandNew","fitchNew",20);

		//when
		assertDoesNotThrow(() -> ratingService.validateRating(addRating));

		//then
		Rating ratingId = ratingRepository.findIdByMoodysRatingAndOrderNumber("moodysNew", 20);
		Optional<Rating> rating = ratingRepository.findById(ratingId.getId());
		assertTrue(rating.isPresent());
	}

}
