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

import com.openclassrooms.poseidon.model.BidList;
import com.openclassrooms.poseidon.service.BidListService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;

@Slf4j
@Controller
public class BidListController {

	@Autowired
	BidListService bidListService;

	@RequestMapping("/bidList/list")
	public String home(Model model) {

		List<BidList> bidLists = bidListService.getBidLists();
		log.debug("Getting bidLists: " + bidLists);
		model.addAttribute("bidLists", bidLists);
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		log.info("display form");
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {

			log.error("There are errors in the form");
			model.addAttribute("bidList", bid);
		} else {
			bidListService.createNewBidList(bid);
			log.debug("adding bidList to dataBase: " + bid);
			redirectAttributes.addFlashAttribute("success", "BidList successfully added");
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

		BidList bidList = bidListService.getBidListById(id);
		log.info("Getting BidList=" + bidList);
		if (bidList == null) {
			log.error("Invalid bidList id=" + id);
			redirectAttributes.addFlashAttribute("error", "Invalid BidList ID");
			return "redirect:/bidList/list";
		}
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		log.info("updateBid: " + id);
		if (result.hasErrors()) {
			log.error("There are errors in the form");
			model.addAttribute("bidList", bidList);
		} else {
			bidListService.updateBid(id, bidList);
			log.debug("adding bidList to dataBase: " + bidList);
			redirectAttributes.addFlashAttribute("success", "BidList successfully added");
			return "redirect:/bidList/list";
		}

		return "bidList/add";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		BidList bidList = bidListService.getBidListById(id);

		if (bidList != null) {
			bidListService.deleteBidList(id);
			log.info("deleting bidList id=" + id);
			redirectAttributes.addFlashAttribute("success", "BidList successfully deleted");
		} else {
			log.error("Invalid bidList id=" + id);
			redirectAttributes.addFlashAttribute("error", "Invalid BidList ID");
		}
		return "redirect:/bidList/list";
	}
}
