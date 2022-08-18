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

import com.openclassrooms.poseidon.model.RuleName;
import com.openclassrooms.poseidon.service.RuleNameService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.validation.Valid;
@Slf4j
@Controller
public class RuleNameController {
	 @Autowired
	    RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
    	List<RuleName> ruleNames = ruleNameService.getRuleNameList();
        log.info("Getting rulNames list="+ ruleNames);
        model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
    	 log.info("Displaying form");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model,RedirectAttributes redirectAttributes) {
    	 if(result.hasErrors()) {
             log.error("There ere errors in the form=" + result.getAllErrors());
             model.addAttribute("ruleName", ruleName);
         } else {
        	 ruleNameService.validateRuleName(ruleName);
             log.debug("Creating new RuleName="+ ruleName);
             redirectAttributes.addFlashAttribute("success", "Rule successfully added");

             return "redirect:/ruleName/list";
         }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes) {
    	log.info("showUpdateForm: Rating  " + id);
        RuleName ruleName = ruleNameService.getRuleNameById(id);
        if (ruleName != null){
        	 model.addAttribute("ruleName", ruleName );
             return "ruleName/update";
        } else {
            log.error("Invalid ruleName id=" + id);
            redirectAttributes.addFlashAttribute("error", "Invalid ruleName ID");
            return "redirect:/ruleName/list";
        }
       
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
    	 log.info("updateRuleName: " + id);
         if(result.hasErrors()){
        	 log.error("There ere errors in the form=" + result.getAllErrors());
             model.addAttribute("ruleName", ruleName);
         } else {
         ruleNameService.updateRuleName(id, ruleName);
         log.debug("Updating RuleName=" + ruleName);
         redirectAttributes.addFlashAttribute("success", "Rule successfully updated");
         return "redirect:/ruleName/list";
     }
         return "ruleName/update";
    }
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes) {
    	 log.info("delete RuleName:" + id);
         ruleNameService.deleteRuleName(id);
         redirectAttributes.addFlashAttribute("success", "Rule successfully deleted");
         return "redirect:/ruleName/list";
     }
}
