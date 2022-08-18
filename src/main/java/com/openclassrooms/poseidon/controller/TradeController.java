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

import com.openclassrooms.poseidon.model.Trade;
import com.openclassrooms.poseidon.service.TradeService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;
@Slf4j
@Controller
public class TradeController {
	 @Autowired
	    TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	List<Trade> trades = tradeService.getTradeList();
    	  log.info("home: show trade/list");
          model.addAttribute("trades", trades);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
    	 log.info("addUser: show trade/add");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    	 if(result.hasErrors()) {
             log.error("There ere errors in the form=" + result.getAllErrors());
             model.addAttribute("trade", trade);
         } else {
            
             log.debug("Creating new Trade="+ trade);
             tradeService.validateTrade(trade);
             redirectAttributes.addFlashAttribute("success", "Trade successfully added");
             return "redirect:/trade/list";
         }

         return "trade/add";
     }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    	 Trade trade = tradeService.getTradeById(id);
         log.info("Getting trade="+ trade);

         if(trade!=null) {
             model.addAttribute("trade", trade);
             return "trade/update";
         } else {
             log.error("Invalid trade id=" + id);
             redirectAttributes.addFlashAttribute("error", "Invalid trade ID");
             return "redirect:/trade/list";
         }
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
    	 if(result.hasErrors()) {
             log.error("There ere errors in the form=" + result.getAllErrors());
             model.addAttribute("trade", trade);
         } else {
        	 tradeService.updateTrade(id, trade);
             log.debug("Updating trade=" + trade);
             redirectAttributes.addFlashAttribute("success", "Trade successfully updated");
             return "redirect:/trade/list";
         }

         return "trade/update";
     }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    	  Trade trade = tradeService.getTradeById(id);

          if(trade!=null) {
              tradeService.deleteTrade(id);
              log.info("Deleting trade=" + trade);
              redirectAttributes.addFlashAttribute("success", "Trade successfully deleted");
          } else {
              log.error("Invalid trade id=" + id);
              redirectAttributes.addFlashAttribute("error", "Invalid trade ID");
          }

          return "redirect:/trade/list";
      }
}
