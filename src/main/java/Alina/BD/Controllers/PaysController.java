package Alina.BD.Controllers;

import Alina.BD.Models.Employee;
import Alina.BD.Models.Pays;
import Alina.BD.Models.Tax;
import Alina.BD.Repositories.EmployeeRepository;
import Alina.BD.Repositories.PaysRepository;
import Alina.BD.Repositories.TaxRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("pays")
public class PaysController {
    private final PaysRepository paysRepository;
    private final TaxRepository taxRepository;
    private final EmployeeRepository employeeRepository;

    public PaysController(PaysRepository paysRepository, TaxRepository taxRepository, EmployeeRepository employeeRepository) {
        this.paysRepository = paysRepository;
        this.taxRepository = taxRepository;
        this.employeeRepository = employeeRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Pays> pays = paysRepository.findAll();
        Iterable<Tax> taxes = taxRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();

        model.addAttribute("pays", pays);
        model.addAttribute("taxes", taxes);
        model.addAttribute("employees", employees);
        return "pays";
    }
    @PostMapping("add")
    public String add(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                      @RequestParam Tax tax,
                      @RequestParam Employee employee) {
        Pays pays = new Pays(date, tax, employee);
        paysRepository.save(pays);
        return "redirect:/pays";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Pays pays  = paysRepository.findById(id).orElseThrow();
        paysRepository.delete(pays);
        return "redirect:/pays";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Pays pays = paysRepository.findById(id).orElseThrow();
        Iterable<Tax> taxes = taxRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();

        model.addAttribute("pays", pays);
        model.addAttribute("taxes", taxes);
        model.addAttribute("employees", employees);
        return "pays-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                       @RequestParam Tax tax,
                       @RequestParam Employee employee) {
        Pays pays = paysRepository.findById(id).orElseThrow();
        pays.setDate(date);
        pays.setTax(tax);
        pays.setEmployee(employee);
        paysRepository.save(pays);
        return "redirect:/pays";
    }
}
