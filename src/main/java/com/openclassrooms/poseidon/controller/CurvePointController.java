package com.openclassrooms.poseidon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openclassrooms.poseidon.model.CurvePoint;
import com.openclassrooms.poseidon.service.CurvePointService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;

@Slf4j
@Controller
public class CurvePointController {
	@Autowired
	CurvePointService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		List<CurvePoint> curvePoints = curvePointService.getCurvePointsList();
		log.info("CurvePoint list= " + curvePoints);
		model.addAttribute("curvePoints", curvePoints);
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePoint curvePoint) {
		log.info("Displaying form");

		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			log.error("There are errors in the form" + result.getAllErrors());
			model.addAttribute("curvePoint", curvePoint);
		} else {
			curvePointService.validateCurvePoint(curvePoint);
			log.debug("Adding curvePoint=" + curvePoint + " to the database");
			redirectAttributes.addFlashAttribute("success", "curvePoint successfully added");
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";

	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		log.info("showUpdateForm: " + id);
		CurvePoint curvePoint = curvePointService.getCurvePointById(id);
		if (curvePoint == null) {
			log.error("Invalid curvePoint id=" + id);
			redirectAttributes.addFlashAttribute("error", "Invalid curvePoint ID");
			return "redirect:/curvePoint/list";
		}
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		log.info("updateCurvePoint: " + id);
		if (result.hasErrors()) {
			log.error("There are errors in the form=" + result.getAllErrors());
			model.addAttribute("curvePoint", curvePoint);
		} else {
			curvePointService.updateCurvePoint(id, curvePoint);
			log.debug("Updating curvePoint=" + curvePoint);
			redirectAttributes.addFlashAttribute("success", "Curve Point successfully updated");
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/update";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		CurvePoint curvePoint = curvePointService.getCurvePointById(id);

		if (curvePoint != null) {
			curvePointService.deleteCurvePoint(id);
			log.info("Deleting curvePoint=" + curvePoint);
			redirectAttributes.addFlashAttribute("success", "Curve Point successfully deleted");
		} else {
			log.error("Invalid curvePoint id=" + id);
			redirectAttributes.addFlashAttribute("error", "Invalid curvePoint ID");
		}
		return "redirect:/curvePoint/list";
	}
}
