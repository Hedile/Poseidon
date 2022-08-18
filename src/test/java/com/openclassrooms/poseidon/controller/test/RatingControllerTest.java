package com.openclassrooms.poseidon.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.poseidon.model.Rating;
import com.openclassrooms.poseidon.repository.BidListRepository;
import com.openclassrooms.poseidon.repository.RatingRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private RatingRepository ratingRepository;
	
	@Test
	@WithMockUser(username = "user", authorities = { "USER", "ADMIN" })
	public void ratingControllerTest() throws Exception {
		
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
		ratingRepository.save(rating);
		int id = rating.getId();
		 // FindAll
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk());

        // Get add form
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk());

        // Add
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "new moody")
                        .param("sendPRating", "new sendP")
                        .param("fitchRating", "fitchRating")
                        .param("orderNumber", "100")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound())
                .andReturn();

        // Get update form
        mockMvc.perform(get("/rating/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());

        // Update
        mockMvc.perform(post("/rating/update/{id}", id)
                        .param("moodysRating", "update moody")
                        .param("sendPRating", "update sendP")
                        .param("fitchRating", "fitchRating")
                        .param("orderNumber", "100")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound())
                .andReturn();

        // Delete
        mockMvc.perform(get("/rating/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/rating/list"))
                .andExpect(status().isFound())
                .andReturn();

	}
}
