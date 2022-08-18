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

import com.openclassrooms.poseidon.model.CurvePoint;
import com.openclassrooms.poseidon.repository.CurvePointRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CurvePointRepository curvePointRepository;
	@Test
	@WithMockUser(username = "user", authorities = { "USER", "ADMIN" })
	public void curvePointControllerTest() throws Exception {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePointRepository.save(curvePoint);
		int id = curvePoint.getId();
		// FindAll
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk());

        // Get add form
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk());

        // Add
      mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "999")
                        .param("term", "22.0")
                        .param("value", "33.0")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound())
                .andReturn();

        // Get update form
        mockMvc.perform(get("/curvePoint/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());


        // Update
        mockMvc.perform(post("/curvePoint/update/{id}", id)
                        .param("curveId", "888")
                        .param("term", "33.0")
                        .param("value", "44.0")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound())
                .andReturn();

        // Delete
        mockMvc.perform(get("/curvePoint/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/curvePoint/list"))
                .andExpect(status().isFound())
                .andReturn();
 
	}
}
