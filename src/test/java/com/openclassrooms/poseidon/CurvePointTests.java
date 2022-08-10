package com.openclassrooms.poseidon;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.poseidon.model.CurvePoint;
import com.openclassrooms.poseidon.repository.CurvePointRepository;
import com.openclassrooms.poseidon.service.CurvePointService;

import java.util.List;
import java.util.Optional;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;
	@Autowired
	private CurvePointService curvePointService;
	@AfterEach
	public void tearDown() {
		
		curvePointRepository.deleteAll();
	}
	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

		// Save
		curvePoint = curvePointRepository.save(curvePoint);
		assertNotNull(curvePoint.getId());
		assertTrue(curvePoint.getCurveId() == 10);

		// Update
		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		assertTrue(curvePoint.getCurveId() == 20);

		// Find
		List<CurvePoint> listResult = curvePointRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertFalse(curvePointList.isPresent());
	}
	@Test
	public void deleteCurvePointTest() {
		CurvePoint curvePoint = new CurvePoint(2,  3, 10);
		curvePoint = curvePointRepository.save(curvePoint);
		// Delete
		Integer id = curvePoint.getId();
		assertDoesNotThrow(() ->curvePointService.deleteCurvePoint(id));
		Optional<CurvePoint> curvePointId = curvePointRepository.findById(id);
		assertFalse(curvePointId.isPresent());


	}

	@Test
	public void updateCurvePointTest() {
		//given
		CurvePoint curvePoint = new CurvePoint(2, 3, 10);
		curvePoint = curvePointRepository.save(curvePoint);
		Integer id = curvePoint.getId();

		CurvePoint updateCurve = new CurvePoint(3, 4, 20);

		//when
		assertDoesNotThrow(() ->curvePointService.updateCurvePoint(id, updateCurve));

		//then
		assertEquals(updateCurve.getTerm(),4);
	}

	@Test
	public void validateCurvePointTest() {
		//given
		CurvePoint addCurvePointForm = new CurvePoint(1, 1, 22);

		//when
		assertDoesNotThrow(() -> curvePointService.validateCurvePoint(addCurvePointForm));

		//then
		CurvePoint curvePoint = curvePointRepository.findCurvePointByCurveIdAndTermAndValue(1, 1, 22);
		Optional<CurvePoint> curve = curvePointRepository.findById(curvePoint.getId());
		assertTrue(curve.isPresent());
	}

}
