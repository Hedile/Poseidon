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

import javax.validation.Valid;


@Controller
public class BidListController {
  
	@Autowired
    BidListService bidListService;
	
    

	@RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
    	Iterable<BidList> bidLists= bidListService.getBidLists();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
    	  bidListService.validate(bid);
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes) {
        // TODO: get Bid by Id and to model then show to the form
    	  BidList bidList = bidListService.getBidListById(id);
          if (bidList == null){
              redirectAttributes.addAttribute("id_not_found", true);
              return  "redirect:/bidList/list";
          }
          model.addAttribute("bidList", bidList );
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	  bidListService.deleteBid(id);
        return "redirect:/bidList/list";
    }
}
