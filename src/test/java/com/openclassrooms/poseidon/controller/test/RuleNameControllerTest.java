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

import com.openclassrooms.poseidon.model.RuleName;
import com.openclassrooms.poseidon.repository.RuleNameRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private RuleNameRepository ruleNameRepository;
	@Test
	@WithMockUser(username = "user", authorities = { "USER", "ADMIN" })
	public void ruleNameControllerTest() throws Exception {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		ruleNameRepository.save(rule);
		int id = rule.getId();
		// FindAll
        this.mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk());

        // Get add form
       mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk());

        // Add
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "Newname")
                        .param("description", "New desc")
                        .param("json", "Newjson")
                        .param("template", "Newtemp")
                        .param("sqlStr", "Newsql")
                        .param("sqlPart", "Newpart")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound())
                .andReturn();

        // Get update form
       mockMvc.perform(get("/ruleName/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());

        // Update
        mockMvc.perform(post("/ruleName/update/{id}", id)
                        .param("name", "update name")
                        .param("description", "update desc")
                        .param("json", "json")
                        .param("template", "temp")
                        .param("sqlStr", "sql")
                        .param("sqlPart", "part")
                        .accept(MediaType.ALL))
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound())
                .andReturn();

        // Delete
        mockMvc.perform(get("/ruleName/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/ruleName/list"))
                .andExpect(status().isFound())
                .andReturn();
   
	}
}
