package com.openclassrooms.poseidon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.poseidon.model.RuleName;
import com.openclassrooms.poseidon.repository.RuleNameRepository;

@Service
public class RuleNameService {
	@Autowired
	 private  RuleNameRepository ruleNameRepository;


		public void validateRuleName(RuleName ruleName){

	        RuleName addRuleName = new RuleName();
	        addRuleName.setName(ruleName.getName());
	        addRuleName.setDescription(ruleName.getDescription());
	        addRuleName.setJson(ruleName.getJson());
	        addRuleName.setTemplate(ruleName.getTemplate());
	        addRuleName.setSqlStr(ruleName.getSqlStr());
	        addRuleName.setSqlPart(ruleName.getSqlPart());
	        ruleNameRepository.save(addRuleName);
	    }

	
	    public void updateRuleName(Integer id, RuleName ruleName) {

	        ruleName.setId(id);
	        ruleNameRepository.save(ruleName);
	    }

	    public List<RuleName> getRuleNameList() {
	        return ruleNameRepository.findAll();
	    }

	    public RuleName getRuleNameById(Integer id) {
	        return ruleNameRepository.findRuleNameById(id);
	    }

	    public void deleteRuleName(Integer id) {
	        ruleNameRepository.deleteById(id);
	    }

}
