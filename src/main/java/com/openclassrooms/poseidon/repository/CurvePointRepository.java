package com.openclassrooms.poseidon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.poseidon.model.CurvePoint;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {
	 CurvePoint findCurvePointById(int id);

	   
	
}
