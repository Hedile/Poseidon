package com.openclassrooms.poseidon.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.poseidon.model.BidList;
import com.openclassrooms.poseidon.repository.BidListRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private BidListRepository bidListRepository;

	@Test
	@WithMockUser(username = "user", authorities = { "USER", "ADMIN" })
	public void bidListControllerTest() throws Exception {
		BidList bid = new BidList("Account", "Type", 10d);
		BidList bidToDelete= new BidList("AccountDelete", "TypeDelete", 10d);
		bidListRepository.save(bid);
		bidListRepository.save(bidToDelete);
		int id = bid.getId();
		int idToDelete = bidToDelete.getId();
		 // FindAll
		mockMvc.perform(get("/bidList/list"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("bidList/list"));
		

		// Get add form
		mockMvc.perform(get("/bidList/add"))
				.andExpect(status().isOk());

		// Add
		mockMvc.perform(post("/bidList/validate")
				.param("account", "NewAccountTest")
				.param("type", "NewTypeTest")
				.param("bidQuantity", "20.0")
				.accept(MediaType.ALL))
			.andExpect(redirectedUrl("/bidList/list"))
			.andExpect(status().isFound())
			.andReturn();

		// Get update form
		mockMvc.perform(get("/bidList/update/{id}", id)
				.accept(MediaType.ALL))
				.andExpect(status().isOk());

		// Update
		mockMvc.perform(post("/bidList/update/{id}", id)
				.param("account", "accountUpdateTest")
				.param("type", "typeUpdateTest")
				.param("bidQuantity", "22.0")
				// .with(csrf())
				.accept(MediaType.ALL))
		.andExpect(redirectedUrl("/bidList/list"))
		.andExpect(status().isFound())
		.andReturn();

		// Delete
		mockMvc.perform(get("/bidList/delete/{id}", idToDelete))
				.andExpect(redirectedUrl("/bidList/list"))
				.andExpect(status().isFound())
				.andReturn();
	}

}
