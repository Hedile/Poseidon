package com.openclassrooms.poseidon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.poseidon.model.RuleName;

@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
	RuleName findRuleNameById(int id);
	 RuleName findIdByNameAndDescription(String name, String description);
}
