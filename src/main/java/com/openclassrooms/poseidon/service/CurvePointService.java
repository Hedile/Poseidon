package com.openclassrooms.poseidon.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.poseidon.model.CurvePoint;
import com.openclassrooms.poseidon.repository.CurvePointRepository;

@Service
public class CurvePointService {
	 @Autowired
	    CurvePointRepository curvePointRepository;
	 
	 public CurvePoint getCurvePointById(Integer id) {
	        return curvePointRepository.findCurvePointById(id);
	    }
	  public List<CurvePoint> getCurvePointsList() {
	        return curvePointRepository.findAll();
	    }
	
	    public void validateCurvePoint(CurvePoint curvePoint)  {

	        CurvePoint addCurvePoint = new CurvePoint();
	        addCurvePoint.setCurveId(curvePoint.getCurveId());
	        addCurvePoint.setTerm(curvePoint.getTerm());
	        addCurvePoint.setValue(curvePoint.getValue());
	        addCurvePoint.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
	        curvePointRepository.save(addCurvePoint);
	    }


	    public void updateCurvePoint(Integer id, CurvePoint curvePoint)  {
	       
	        CurvePoint curvePointInDb = curvePointRepository.findCurvePointById(id);
	        curvePointInDb.setCurveId(curvePoint.getCurveId());
	        curvePointInDb.setTerm(curvePoint.getTerm());
	        curvePointInDb.setValue(curvePoint.getValue());
	        curvePointRepository.save(curvePointInDb);
	    }

	   
	    public void deleteCurvePoint(Integer id) {
	        curvePointRepository.deleteById(id);
	    }
}
