package Alina.BD.Controllers;

import Alina.BD.Models.Client;
import Alina.BD.Models.Commit;
import Alina.BD.Models.Deal;
import Alina.BD.Models.Employee;
import Alina.BD.Repositories.ClientRepository;
import Alina.BD.Repositories.CommitRepository;
import Alina.BD.Repositories.DealRepository;
import Alina.BD.Repositories.EmployeeRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("deal")
public class DealController {
    private final DealRepository dealRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final CommitRepository commitRepository;
    public DealController(DealRepository dealRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository, CommitRepository commitRepository) {
        this.dealRepository = dealRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.commitRepository = commitRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Deal> deals = dealRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("deals", deals);
        model.addAttribute("employees", employees);
        return "deal";
    }
    @PostMapping("add")
    public String add(@RequestParam String name,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                      @RequestParam Integer profit) {
        Deal deal = new Deal(name, date, profit);
        dealRepository.save(deal);
        return "redirect:/deal";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Deal deal  = dealRepository.findById(id).orElseThrow();
        dealRepository.delete(deal);
        return "redirect:/deal";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Deal deal  = dealRepository.findById(id).orElseThrow();
        model.addAttribute("deal", deal);
        return "deal-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name,
                       @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                       @RequestParam Integer profit) {
        Deal deal  = dealRepository.findById(id).orElseThrow();
        deal.setName(name);
        deal.setDate(date);
        deal.setProfit(profit);
        dealRepository.save(deal);
        return "redirect:/deal";
    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false)Employee employee,
                         @RequestParam(required = false) Client client,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate minDate,
                         @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate maxDate,
                         Model model) {
        Iterable<Deal> deals = dealRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Client> clients = clientRepository.findAll();

        if(employee != null) {
            Iterable<Commit> commits = commitRepository.findByEmployee(employee);
            deals = new ArrayList<>();
            for (Commit commit : commits) {
                Deal deal = dealRepository.findById(commit.getDeal().getId()).orElseThrow();
                ((ArrayList<Deal>) deals).add(deal);
            }
        }
        if(client != null) {
            Iterable<Commit> commits = commitRepository.findByClient(client);
            deals = new ArrayList<>();
            for (Commit commit : commits) {
                Deal deal = dealRepository.findById(commit.getDeal().getId()).orElseThrow();
                ((ArrayList<Deal>) deals).add(deal);
            }
        }

        if(minDate != null && maxDate != null) {
            deals = dealRepository.findByDateBetween(minDate, maxDate);
        }


        model.addAttribute("deals", deals);
        model.addAttribute("employees", employees);
        model.addAttribute("clients", clients);
        return "deal";
    }
}
