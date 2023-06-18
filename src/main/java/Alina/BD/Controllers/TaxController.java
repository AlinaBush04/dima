package Alina.BD.Controllers;

import Alina.BD.Models.Tax;
import Alina.BD.Repositories.TaxRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("tax")
public class TaxController {
    private final TaxRepository taxRepository;
    public TaxController(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Tax> taxes = taxRepository.findAll();
        model.addAttribute("taxes", taxes);
        return "tax";
    }
    @PostMapping("add")
    public String add(@RequestParam Integer tax_deduction) {
        Tax tax = new Tax(tax_deduction);
        taxRepository.save(tax);
        return "redirect:/tax";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Tax tax  = taxRepository.findById(id).orElseThrow();
        taxRepository.delete(tax);
        return "redirect:/tax";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Tax tax  = taxRepository.findById(id).orElseThrow();
        model.addAttribute("tax", tax);
        return "tax-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam Integer tax_deduction) {
        Tax tax = taxRepository.findById(id).orElseThrow();
        tax.setTax_deduction(tax_deduction);
        taxRepository.save(tax);
        return "redirect:/tax";
    }
}
