package com.openclassrooms.poseidon;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.poseidon.model.RuleName;
import com.openclassrooms.poseidon.repository.RuleNameRepository;
import com.openclassrooms.poseidon.service.RuleNameService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;
	@Autowired
	private RuleNameService ruleNameService;
	@AfterEach
	public void tearDown() {
		
		ruleNameRepository.deleteAll();
	}
	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameRepository.save(rule);
		assertNotNull(rule.getId());
		assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameRepository.save(rule);
		assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		assertFalse(ruleList.isPresent());
	}
	@Test
	public void deleteRuleNameTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = ruleNameRepository.save(rule);
		// Delete
		Integer id = rule.getId();
		ruleNameService.deleteRuleName(id);
		Optional<RuleName> ruleName = ruleNameRepository.findById(id);
		assertFalse(ruleName.isPresent());
	}
	@Test
	public void updateRuleTest() {
		//given
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = ruleNameRepository.save(rule);
		Integer id = rule.getId();

		RuleName ruleName = new RuleName("Test", "test", "test", "test", "test", "test");

		//when
		ruleNameService.updateRuleName(id, ruleName);
		RuleName updateRuleName=ruleNameService.getRuleNameById(id);
		//then
		assertEquals(updateRuleName.getName(),"Test");
	}

	@Test
	public void validateRuleTest() {
		//given
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		//when
		ruleNameService.validateRuleName(rule);

		//then
		RuleName ruleNameId = ruleNameRepository.findIdByNameAndDescription("Rule Name", "Description");
		Optional<RuleName> ruleName = ruleNameRepository.findById(ruleNameId.getId());
		assertTrue(ruleName.isPresent());
	}
}
