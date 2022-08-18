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

import com.openclassrooms.poseidon.model.Trade;
import com.openclassrooms.poseidon.repository.TradeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TradeRepository tradeRepository;

	@Test
	@WithMockUser(username = "user", authorities = { "USER", "ADMIN" })
	public void tradeControllerTest() throws Exception {
		Trade trade = new Trade("Trade_Account", "Type",10);
		 tradeRepository.save(trade);
		 int id = trade.getId();
		 
		  // FindAll
	        mockMvc.perform(get("/trade/list"))
	                .andExpect(status().isOk());

	        // Get add form
	       mockMvc.perform(get("/trade/add"))
	                .andExpect(status().isOk());

	        // Add
	        mockMvc.perform(post("/trade/validate")
	                        .param("account", "new acc")
	                        .param("type", "t")
	                        .param("buyQuantity", "10.0")
	                        .accept(MediaType.ALL))
	                .andExpect(redirectedUrl("/trade/list"))
	                .andExpect(status().isFound())
	                .andReturn();

	        // Get update form
	        mockMvc.perform(get("/trade/update/{id}", id)
	                        .accept(MediaType.ALL))
	                .andExpect(status().isOk());

	        // Update
	        mockMvc.perform(post("/trade/update/{id}", id)
	                        .param("account", "update acc")
	                        .param("type", "type")
	                        .param("buyQuantity", "20.0")
	                        .accept(MediaType.ALL))
	                .andExpect(redirectedUrl("/trade/list"))
	                .andExpect(status().isFound())
	                .andReturn();

	        // Delete

	       mockMvc.perform(get("/trade/delete/{id}", id))
	                .andDo(print())
	                .andExpect(redirectedUrl("/trade/list"))
	                .andExpect(status().isFound())
	                .andReturn();
		
	}
}
