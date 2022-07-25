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

import com.openclassrooms.poseidon.exception.NegativeNumberException;
import com.openclassrooms.poseidon.model.Rating;
import com.openclassrooms.poseidon.service.RatingService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;
@Slf4j
@Controller
public class RatingController {
	 @Autowired
	    RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
    	List<Rating> ratings = ratingService.getRatingList();
        log.info("Getting ratings list="+ ratings);
        model.addAttribute("ratings", ratings);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
    	 if(result.hasErrors()) {
    		 log.error("There ere errors in the form=" + result.getAllErrors());
             model.addAttribute("rating", rating);
    	 } else {
             ratingService.validateRating(rating);
             log.debug("Creating new rating="+ rating);
             redirectAttributes.addFlashAttribute("success", "Rating successfully added");
             return "redirect:/rating/list";
    	 }
             return "rating/add";
    	    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    	 log.info("showUpdateForm: Rating  " + id);
         Rating rating = ratingService.getRatingById(id);
         if (rating != null){
        	 model.addAttribute("rating", rating);
             return "rating/update";
         } else {
        	 log.error("Invalid rating id=" + id);
             redirectAttributes.addFlashAttribute("error", "Invalid rating ID");
             return "redirect:/rating/list";
     }
    }
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    	  log.info("updateRating: " + id);
          if(result.hasErrors()){
        	  log.error("There ere errors in the form=" + result.getAllErrors());
              model.addAttribute("rating", rating);
          } else {
              ratingService.updateRating(id, rating);
              log.info("Updating rating="+ rating);
              redirectAttributes.addFlashAttribute("success", "Rating successfully updated");
              return "redirect:/rating/list";
          }
          return "rating/update";
    
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
    	 log.info("deleteRating:" + id);
         ratingService.deleteRating(id);
         redirectAttributes.addFlashAttribute("success", "Rating successfully deleted");
        return "redirect:/rating/list";
    }
}
